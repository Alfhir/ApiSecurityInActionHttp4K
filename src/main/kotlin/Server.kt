import endpoints.*
import org.http4k.core.HttpHandler
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer

object DungeonGeneratorAPIServer {
    operator fun invoke(): HttpHandler = routes(
        Get_rooms()
        ,	Post_rooms()
        ,	Get_monsters()
        ,	Post_monsters()
        ,	Post_roommonsters()
    )
}

fun main() {
    DungeonGeneratorAPIServer().asServer(SunHttp(8000)).start()
}