package infra

import org.http4k.core.then
import org.http4k.events.Events
import org.http4k.events.HttpEvent
import org.http4k.filter.ResponseFilters.ReportHttpTransaction
import org.http4k.filter.ServerFilters
import org.http4k.routing.RoutingHttpHandler

fun IncomingHttp(events: Events, base: RoutingHttpHandler): RoutingHttpHandler =
    HandleError(events)
        .then(ServerFilters.RequestTracing())
        .then(ReportHttpTransaction {
            // Emit event
            events(HttpEvent.Incoming(it))
        })
        .then(base)

