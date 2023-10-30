package rooms.endpoints

import fakeRoom
import org.http4k.contract.ContractRoute
import org.http4k.contract.Tag
import org.http4k.contract.meta
import org.http4k.core.*
import rooms.ports.RoomRepo
import rooms.roomDTOLens


fun CreateRoom(roomRepo: RoomRepo): ContractRoute = "/rooms" meta {
        description = "This is an unsecured route to create a room."
        summary = "Creates a room."
        tags += Tag("Room operations")
        consumes += ContentType.APPLICATION_JSON
        receiving(roomDTOLens to fakeRoom)
        returning(Status.CREATED)
    } bindContract Method.POST to  { req: Request -> createHandler(req, roomRepo) }

    fun createHandler(request: Request, roomRepo: RoomRepo): Response {
        val returnedID = roomRepo.createRoom(
            roomDTOLens(request)
        )
        return Response(Status.CREATED).header("Location", "http://fake-url.com/fake-rooms-path/$returnedID")
    }

