import org.http4k.core.then
import org.http4k.filter.DebuggingFilters
import org.http4k.server.SunHttp
import org.http4k.server.asServer
import rooms.RoomsApi


fun main() {
    val printingApp = DebuggingFilters.PrintRequest().then(RoomsApi())
    printingApp.asServer(SunHttp(8000)).start()
}