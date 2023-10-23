package endpoints

import org.http4k.core.Method
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.RoutingHttpHandler
import org.http4k.routing.bind

/**
 * Create a relationship between a room and a monster
 * Response:
 * 	application/json
 * 		201 Relationship created successfully
 */
fun Post_roommonsters(): RoutingHttpHandler = "/room-monsters" bind Method.POST to { Response(Status.OK) }
