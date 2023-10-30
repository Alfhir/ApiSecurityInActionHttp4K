package infra

import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.events.Event
import org.http4k.events.Events
import org.http4k.filter.ServerFilters

fun HandleError(events: Events) = ServerFilters.CatchAll {
    events(Event.Companion.Error("Uncaught throwable!", it))
    Response(Status.SERVICE_UNAVAILABLE)
}
