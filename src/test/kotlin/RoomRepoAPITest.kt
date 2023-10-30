import com.natpryce.hamkrest.and
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.containsSubstring
import org.http4k.core.ContentType.Companion.APPLICATION_JSON
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Request
import org.http4k.core.Status
import org.http4k.core.with
import org.http4k.hamkrest.hasBody
import org.http4k.hamkrest.hasContentType
import org.http4k.hamkrest.hasStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import rooms.RoomsApi
import rooms.roomDTOLens

class RoomRepoAPITest {

    private val unsecuredApp = RoomsApi()

    @Test
    fun `can load open api doc`() {
        assertThat(
            unsecuredApp(Request(GET, "/api/v1/openapi3.json")),
            hasStatus(Status.OK).and(hasBody(containsSubstring(""""title":"Dungeon API Example"""")))
        )
    }

    @Test
    fun `can get all rooms`() {
        val response = unsecuredApp(Request(GET, "/rooms"))
        val roomFromResponse = roomDTOLens(response)
        assertThat(
            response,
            hasStatus(Status.OK).and(hasContentType(APPLICATION_JSON))
        )
        assertEquals(roomFromResponse, fakeRoom)
    }

    @Test
    fun `can create rooms`() {
        val request = Request(POST, "/rooms").with(roomDTOLens of fakeRoom)
        val response = unsecuredApp(request)
        val locationHeader = response.header("Location")
        assertThat(response, hasStatus(Status.CREATED))
        assertEquals("http://fake-url.com/fake-path/fake-id", locationHeader)
    }
}

