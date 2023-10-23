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
    fun get_rooms() = httpHandler(Request(Method.GET, "/rooms"))

    /**
     * Create a new room
     * Response:
     * 	application/json
     * 		201 Room created successfully
     */
    fun post_rooms() = httpHandler(Request(Method.POST, "/rooms"))

    /**
     * Get a list of monsters
     * Response:
     * 	application/json
     * 		200 Successful response
     */
    fun get_monsters() = httpHandler(Request(Method.GET, "/monsters"))


    /**
     * Create a new monster
     * Response:
     * 	application/json
     * 		201 Monster created successfully
     */
    fun post_monsters() = httpHandler(Request(Method.POST, "/monsters"))

    /**
     * Create a relationship between a room and a monster
     * Response:
     * 	application/json
     * 		201 Relationship created successfully
     */
    fun post_roommonsters() = httpHandler(Request(Method.POST, "/room-monsters"))
}