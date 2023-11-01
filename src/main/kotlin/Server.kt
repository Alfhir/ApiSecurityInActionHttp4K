import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.http4k.events.AutoMarshallingEvents
import org.http4k.format.ConfigurableJackson
import org.http4k.format.asConfigurable
import org.http4k.format.withStandardMappings
import org.http4k.server.SunHttp
import org.http4k.server.asServer
import rooms.RoomsApi
import java.time.Clock


fun main() {

    RoomsApi(
        clock = Clock.systemUTC(),
        events = AutoMarshallingEvents(Json),
        roomsPort = roomsFake()
    ).asServer(SunHttp(8000)).start()
}

object Json : ConfigurableJackson(
    KotlinModule.Builder().build()
        .asConfigurable()
        .withStandardMappings()
        // .value(TinyType)
        // .value(TinyType)
        .done()
)