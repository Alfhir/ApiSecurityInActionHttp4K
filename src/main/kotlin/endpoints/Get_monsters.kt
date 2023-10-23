package endpoints

import org.http4k.core.Method
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind

/**
 * Get a list of monsters
 * Response:
 * 	application/json
 * 		200 Successful response
 */
fun Get_monsters(): RoutingHttpHandler = "/monsters" bind Method.GET to { Response(Status.OK) }
