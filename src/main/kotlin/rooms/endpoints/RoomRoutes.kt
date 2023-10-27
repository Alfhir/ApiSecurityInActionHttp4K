package rooms.endpoints

import monsters.Monster
import org.http4k.contract.ContractRoute
import org.http4k.contract.meta
import org.http4k.contract.openapi.OpenAPIJackson.auto
import org.http4k.contract.security.NoSecurity
import org.http4k.core.*
import org.http4k.core.ContentType.Companion.APPLICATION_JSON
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Status.Companion.OK
import org.http4k.lens.ContentNegotiation
import org.http4k.lens.string

data class Room(
    val dimensions: Triple<Int, Int, Int>,
    val sensoryImpression: String,
    val initialObservations: String,
    val findings: String,
    val gameMasterInformation: String,
    val monsters: List<Monster>
)

val roomLens = Body.auto<Room>(
    "The room data.", ContentNegotiation.Strict, APPLICATION_JSON
).toLens()

val fakeRoom = Room(
    dimensions = Triple(1, 1, 1),
    sensoryImpression = "You can sense a funny smell...",
    initialObservations = "In the back of the room there seems to be a heap of rotten organic materials...",
    findings = "50 pieces of gold.",
    gameMasterInformation = "Ursula is neutral-good.",
    monsters = listOf(Monster("Ursula", "Ancient shambling mound"))
)

// The spec defined in code, is the same one that is used to generate the API documentation
// and the same one used to validate incoming HTTP messages.
fun getAllRooms(): ContractRoute = "/rooms/" meta {
    description = "This is an unsecured route to get all rooms."
    summary = "Gets all rooms."
    security = NoSecurity
} bindContract GET to ::getRoomsHandler

fun getRoomsHandler(): HttpHandler = {
    val jsonResponse = """
    {
        "data": "Deep in the mines of moria..."        
    }
    """.trimIndent()
    val bodyLens = Body.string(APPLICATION_JSON).toLens()
    Response(OK).with(bodyLens of jsonResponse)
}

fun createRoom(): ContractRoute {

    val spec = "/rooms" meta {
        description = "This is an unsecured route to create a room."
        summary = "Creates a room."
        receiving(roomLens to fakeRoom)
        returning(OK, roomLens to fakeRoom)
    } bindContract POST

    val createRoomHandler: HttpHandler = { request: Request ->
        val received: Room = roomLens(request)
        Response(OK).with(roomLens of received)
    }

    return spec to createRoomHandler

}