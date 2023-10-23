package endpoints

import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind

/**
 * Get a list of rooms
 * Response:
 * 	application/json
 * 		200 Successful response
 */
fun Get_rooms(): RoutingHttpHandler = "/rooms" bind Method.GET to { Response(Status.OK) }
