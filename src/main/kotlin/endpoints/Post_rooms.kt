package endpoints

import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind

/**
 * Create a new room
 * Response:
 * 	application/json
 * 		201 Room created successfully
 */
fun Post_rooms(): RoutingHttpHandler = "/rooms" bind Method.POST to { Response(Status.OK) }
