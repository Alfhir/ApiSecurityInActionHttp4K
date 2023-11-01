import com.natpryce.hamkrest.and
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.containsSubstring
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status
import org.http4k.events.AutoMarshallingEvents
import org.http4k.hamkrest.hasBody
import org.http4k.hamkrest.hasStatus
import org.junit.jupiter.api.Test
import rooms.RoomsApi
import java.time.Clock

class OpenAPI {

    private val unsecuredApp = RoomsApi(
        clock = Clock.systemUTC(),
        events = AutoMarshallingEvents(Json),
        roomsPort = roomsFake()
    )

    @Test
    fun `can load open api doc`() {
        assertThat(
            unsecuredApp(Request(GET, "/api/v1/openapi3.json")),
            hasStatus(Status.OK).and(hasBody(containsSubstring(""""title":"Dungeon API Example"""")))
        )
    }
}

