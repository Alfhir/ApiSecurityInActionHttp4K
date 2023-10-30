package infra

import org.http4k.core.HttpHandler
import org.http4k.core.then
import org.http4k.events.Events
import org.http4k.events.HttpEvent
import org.http4k.filter.ClientFilters
import org.http4k.filter.ResponseFilters.ReportHttpTransaction


fun OutgoingHttp(events: Events, http: HttpHandler) =
    ClientFilters.RequestTracing()
        .then(ReportHttpTransaction { events(HttpEvent.Outgoing(it)) })
        .then(http)
