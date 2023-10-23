package endpoints

import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind

/**
 * Create a new monster
 * Response:
 * 	application/json
 * 		201 Monster created successfully
 */
fun Post_monsters(): RoutingHttpHandler = "/monsters" bind Method.POST to { Response(Status.OK) }
