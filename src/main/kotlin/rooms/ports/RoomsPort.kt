package rooms.ports

import rooms.RoomDTO

interface RoomsPort {
    fun rooms(): List<RoomDTO>
    fun createRoom(room: RoomDTO): Long
}
