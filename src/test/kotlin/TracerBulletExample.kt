import com.natpryce.hamkrest.assertion.assertThat
import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.events.EventFilters.AddServiceName
import org.http4k.events.EventFilters.AddZipkinTraces
import org.http4k.events.Events
import org.http4k.events.HttpEvent.Incoming
import org.http4k.events.HttpEvent.Outgoing
import org.http4k.events.and
import org.http4k.events.then
import org.http4k.filter.ClientFilters
import org.http4k.filter.ClientFilters.ResetRequestTracing
import org.http4k.filter.ResponseFilters.ReportHttpTransaction
import org.http4k.filter.ServerFilters.RequestTracing
import org.http4k.hamkrest.hasStatus
import org.http4k.routing.bind
import org.http4k.routing.reverseProxy
import org.http4k.routing.routes
import org.http4k.tracing.Actor
import org.http4k.tracing.ActorResolver
import org.http4k.tracing.ActorType
import org.http4k.tracing.TraceRenderPersistence
import org.http4k.tracing.junit.TracerBulletEvents
import org.http4k.tracing.persistence.FileSystem
import org.http4k.tracing.renderer.PumlSequenceDiagram
import org.http4k.tracing.tracer.HttpTracer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension;
import java.io.File

fun TraceEvents(actorName: String) = AddZipkinTraces().then(AddServiceName(actorName))

fun ClientStack(events: Events) = ReportHttpTransaction { events(Outgoing(it)) }
    .then(ClientFilters.RequestTracing())

fun ServerStack(events: Events) = ReportHttpTransaction { events(Incoming(it)) }
    .then(RequestTracing())

class User(rawEvents: Events, rawHttp: HttpHandler) {
    private val events = TraceEvents("user").then(rawEvents)

    // As the user is the initiator of requests, we need to reset the tracing for each call.
    private val http = ResetRequestTracing().then(ClientStack(events)).then(rawHttp)

    fun initiateCall() = http(Request(GET, "http://internal1/int1"))
}

fun Internal1(rawEvents: Events, rawHttp: HttpHandler): HttpHandler {
    val events = TraceEvents("internal1").then(rawEvents).and(rawEvents)
    val http = ClientStack(events).then(rawHttp)

    return ServerStack(events)
        .then(
            routes("/int1" bind { _: Request ->
                val first = http(Request(GET, "http://external1/ext1"))
                when {
                    first.status.successful -> http(Request(GET, "http://internal2/int2"))
                    else -> first
                }
            })
        )
}

fun Internal2(rawEvents: Events, rawHttp: HttpHandler): HttpHandler {
    val events = TraceEvents("internal2").then(rawEvents).and(rawEvents)
    val http = ClientStack(events).then(rawHttp)

    return ServerStack(events)
        .then(
            routes("/int2" bind { _: Request ->
                http(Request(GET, "http://external2/ext2"))
            })
        )
}

fun FakeExternal1(): HttpHandler = { Response(OK) }
fun FakeExternal2(): HttpHandler = { Response(OK) }

private val actor = ActorResolver {
    Actor(name = it.metadata["service"].toString(), type = ActorType.System)
}

// Test will capture the traffic and render it to the console.
 class RenderingTest {
    @RegisterExtension
    // This events implementation will automatically capture the HTTP traffic.
    val events = TracerBulletEvents(
        listOf(HttpTracer(actor)), // A tracer to capture HTTP calls
        listOf(PumlSequenceDiagram), // Render the HTTP traffic as a PUML diagram
        TraceRenderPersistence.FileSystem(File(".")) // Store the result
    )

    @Test
    fun `render successful trace`() {
        // Compose our application(s) together.
        val internalApp = Internal1(
            events,
            reverseProxy(
                "external1" to FakeExternal1(),
                "internal2" to Internal2(events, FakeExternal2())
            )
        )

        // mMke a request to the composed stack.
        assertThat(User(events, internalApp).initiateCall(), hasStatus(OK))
    }

}