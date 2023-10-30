import rooms.RoomDTO
import rooms.ports.RoomRepo

fun roomsFake() = object : RoomRepo {

    override fun rooms(): List<RoomDTO> = listOf(fakeRoom)

    override fun createRoom(room: RoomDTO) {
        TODO("Not yet implemented")
    }

}

// this is used for specs as well
val fakeRoom =  RoomDTO(
    dimensions = Triple(1, 1, 1),
    sensoryImpression = "You can sense a funny smell...",
    initialObservations = "In the back of the room there seems to be a heap of rotten organic materials...",
    findings = "50 pieces of gold.",
    gameMasterInformation = "Ursula is neutral-good.",
    // monsters = listOf(Monster("Ursula", "Ancient shambling mound"))
)