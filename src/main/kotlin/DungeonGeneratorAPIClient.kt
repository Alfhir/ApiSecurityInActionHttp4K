import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request

class DungeonGeneratorAPIClient(
    private val httpHandler: HttpHandler
) {
    /**
     * Get a list of rooms
     * Response:
     * 	application/json
     * 		200 Successful response
     */
    fun get_rooms() {
        val httpReq = Request(Method.GET, "/rooms")
        val response = httpHandler(httpReq)

        response
    }

    /**
     * Create a new room
     * Response:
     * 	application/json
     * 		201 Room created successfully
     */
    fun post_rooms() {
        val httpReq = Request(Method.POST, "/rooms")
        val response = httpHandler(httpReq)

        response
    }

    /**
     * Get a list of monsters
     * Response:
     * 	application/json
     * 		200 Successful response
     */
    fun get_monsters() {
        val httpReq = Request(Method.GET, "/monsters")
        val response = httpHandler(httpReq)

        response
    }

    /**
     * Create a new monster
     * Response:
     * 	application/json
     * 		201 Monster created successfully
     */
    fun post_monsters() {
        val httpReq = Request(Method.POST, "/monsters")
        val response = httpHandler(httpReq)

        response
    }

    /**
     * Create a relationship between a room and a monster
     * Response:
     * 	application/json
     * 		201 Relationship created successfully
     */
    fun post_roommonsters() {
        val httpReq = Request(Method.POST, "/room-monsters")
        val response = httpHandler(httpReq)

        response
    }
}