package rooms.endpoints

import fakeRoom
import org.http4k.contract.ContractRoute
import org.http4k.contract.Tag
import org.http4k.contract.meta
import org.http4k.core.*
import rooms.ports.RoomRepo
import rooms.roomDTOLens
import roomsFake


fun CreateRoom(rooms: RoomRepo): ContractRoute {
    val spec = "/rooms" meta {
        description = "This is an unsecured route to create a room."
        summary = "Creates a room."
        tags += Tag("Room operations")
        consumes += ContentType.APPLICATION_JSON
        receiving(roomDTOLens to fakeRoom)
        returning(Status.CREATED)
    } bindContract Method.POST

    fun createRoomHandler(rooms: RoomRepo): HttpHandler = { _: Request ->
        Response(Status.CREATED).header("Location", "http://fake-url.com/fake-path/fake-id")
    }

    return spec to createRoomHandler(rooms)

}