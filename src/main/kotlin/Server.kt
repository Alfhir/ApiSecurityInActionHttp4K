import com.fasterxml.jackson.module.kotlin.KotlinModule
import infra.AppEvents
import org.http4k.core.then
import org.http4k.events.AutoMarshallingEvents
import org.http4k.filter.DebuggingFilters
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
        roomRepo = roomsFake()
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