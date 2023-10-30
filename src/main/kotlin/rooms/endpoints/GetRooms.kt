package rooms.endpoints

import fakeRoom
import org.http4k.contract.ContractRoute
import org.http4k.contract.Tag
import org.http4k.contract.meta
import org.http4k.core.*
import org.http4k.core.Method.GET
import rooms.ports.RoomRepo
import rooms.roomDTOLens

// The spec defined in code, is the same one that is used to generate the API documentation
// and the same one used to validate incoming HTTP messages.
fun GetRooms(roomRepo: RoomRepo): ContractRoute = "/rooms/" meta {
    description = "This is an unsecured route to get all rooms."
    summary = "Gets all rooms."
    tags += Tag("Room operations")
    produces += ContentType.APPLICATION_JSON
    returning(Status.OK, roomDTOLens to fakeRoom)
} bindContract GET to { req: Request -> getHandler(req, roomRepo) }

fun getHandler(it: Request, roomRepo: RoomRepo): Response =
    Response(Status.OK).with(roomDTOLens of roomRepo.rooms().first())

