package rooms

import org.http4k.contract.openapi.OpenAPIJackson.auto
import org.http4k.core.Body
import org.http4k.core.ContentType
import org.http4k.lens.ContentNegotiation

data class RoomDTO(
    val dimensions: Triple<Int, Int, Int>,
    val sensoryImpression: String,
    val initialObservations: String,
    val findings: String,
    val gameMasterInformation: String,
    //val monsters: List<Monster>
)

val roomDTOLens = Body.auto<RoomDTO>(
    description = "The room data.",
    contentNegotiation = ContentNegotiation.Strict,
    contentType = ContentType.APPLICATION_JSON
).toLens()