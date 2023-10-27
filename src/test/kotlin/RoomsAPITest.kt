import com.natpryce.hamkrest.allOf
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
import rooms.endpoints.fakeRoom
import rooms.endpoints.roomLens

class RoomsAPITest {

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
        assertThat(
            unsecuredApp(Request(GET, "/rooms")),
            allOf(
                hasStatus(Status.OK),
                hasContentType(APPLICATION_JSON),
                hasBody(containsSubstring("""Deep in the mines of moria..."""))
            )
        )
    }

    @Test
    fun `can create rooms`() {
        val request = Request(POST, "/rooms").with(roomLens of fakeRoom)
        val response = unsecuredApp(request)
        val roomFromResponse = roomLens(response)

        assertThat(response, hasStatus(Status.OK).and(hasContentType(APPLICATION_JSON)))
        assertEquals(fakeRoom, roomFromResponse)
    }
}

