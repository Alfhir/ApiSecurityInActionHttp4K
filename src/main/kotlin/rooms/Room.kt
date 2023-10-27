package rooms

import monsters.Monster

data class Room(
    val dimensions: Triple<Int, Int, Int>,
    val sensoryImpression: String,
    val initialObservations: String,
    val findings: String,
    val gameMasterInformation: String,
    val monsters: List<Monster>
)