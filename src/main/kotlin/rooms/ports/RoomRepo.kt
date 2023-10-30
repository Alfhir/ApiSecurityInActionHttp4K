package rooms.ports

import rooms.RoomDTO

interface RoomRepo {
    fun rooms(): List<RoomDTO>
    fun createRoom(room: RoomDTO): Long
}
