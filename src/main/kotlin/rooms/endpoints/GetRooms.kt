package rooms.endpoints

import fakeRoom
import org.http4k.contract.ContractRoute
import org.http4k.contract.Tag
import org.http4k.contract.meta
import org.http4k.core.*
import org.http4k.core.Method.GET
import org.http4k.lens.Header
import org.http4k.lens.string
import rooms.ports.RoomsPort
import rooms.roomDTOLens

fun GetRooms(roomsPort: RoomsPort): ContractRoute = "/rooms/" meta {
    description = "This is an unsecured route to get all rooms."
    summary = "Gets all rooms."
    headers += securityHeadersLens
    tags += Tag("Room operations")
    produces += ContentType.APPLICATION_JSON
    returning(Status.OK, roomDTOLens to fakeRoom)
} bindContract GET to { req: Request -> getHandler(req, roomsPort) }

fun getHandler(it: Request, roomsPort: RoomsPort): Response =
    Response(Status.OK).with(roomDTOLens of roomsPort.rooms().first())

val securityHeadersLens = Header.string().required("XSS-CONTENT-OPTIONS")