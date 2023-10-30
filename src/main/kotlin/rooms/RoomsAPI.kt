@file:Suppress("FunctionName")

package rooms

import org.http4k.contract.contract
import org.http4k.contract.openapi.ApiInfo
import org.http4k.contract.openapi.v3.ApiServer
import org.http4k.contract.openapi.v3.OpenApi3
import org.http4k.contract.security.NoSecurity
import org.http4k.core.Uri
import org.http4k.core.then
import org.http4k.filter.CorsPolicy
import org.http4k.filter.ServerFilters
import org.http4k.format.Jackson
import org.http4k.routing.RoutingHttpHandler
import rooms.endpoints.createRoom
import rooms.endpoints.getAllRooms

// If you want to view the API on another origin like https://www.http4k.org/openapi3/ you need a Cors-policy
 fun veryUnsafeCorsFilter() = ServerFilters.Cors(CorsPolicy.UnsafeGlobalPermissive.copy(headers = listOf("*")))

// API classes usually take env, clock, random, handlers for using fakes (In-memory implementations of repositories,
// faked external services) wire everything together and apply logging and tracing functionality..
fun RoomsApi(): RoutingHttpHandler = veryUnsafeCorsFilter().then(contract {
    renderer = OpenApi3(
        apiInfo = ApiInfo(
            title = "Dungeon API Example",
            version = "0.1",
            description = "<p>Credentials are <b>foo/bar</b></p><a href='https://http4k.org/openapi3'>view this API in OpenAPI</a>"
        ),
        json = Jackson,
        servers = listOf(ApiServer(Uri.of("http://localhost:8000"), "Home Sweet Home"))
    )
    descriptionPath = "/api/v1/openapi3.json"
    security = NoSecurity
    routes += getAllRooms()
    routes += createRoom()
})