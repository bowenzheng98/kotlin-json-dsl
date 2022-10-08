
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS

@TestInstance(PER_CLASS)
class JsonBuilderTest {

    @Test
    fun `test empty object is valid json`() {
        val json: String = json { }.toString()
        assertThat(json).isValidJSON()
        assertThat(json).isEqualTo("{}")
    }

    @Test
    fun `test object with value null`() {
        val json: String = json { "value" to null }
        assertThat(json).isValidJSON()
        assertThat(json).isEqualTo("""{"value":null}""")
    }

    @Test
    fun `test object with value number`() {
        val json: String = json { "value" to 1 }
        assertThat(json).isValidJSON()
        assertThat(json).isEqualTo("""{"value":1}""")
    }

    @Test
    fun `test nested object`() {
        val json: String = json { "nice_thing" to json { "value" to 1 } }
        assertThat(json).isValidJSON()
        assertThat(json).isEqualTo("""{"nice_thing":{"value":1}}""")
    }

    @Test
    fun `test array`() {
        val json: String = json { "list" to arrayOf(json { "value" to 1 }) }
        assertThat(json).isValidJSON()
        assertThat(json).isEqualTo("""{"list":[{"value":1}]}""")
    }

    @Test
    fun `test object expands object with value number`() {
        val baseJson: String = json { "value" to 1 }
        val json: String = json { expand(baseJson) }
        assertThat(json).isValidJSON()
        assertThat(json).isEqualTo("""{"value":1}""")
    }

    @Test
    fun `test array of integers`() {
        val json: String = json { "list" to arrayOf(1, 2, 3, 4) }
        assertThat(json).isValidJSON()
        assertThat(json).isEqualTo("""{"list":[1,2,3,4]}""")
    }

    @Test
    fun `test array of string`() {
        val json: String = json { "list" to arrayOf("a", "b", "c", "d") }
        assertThat(json).isValidJSON()
        assertThat(json).isEqualTo("""{"list":["a","b","c","d"]}""")
    }

    @Test
    fun `test object expands another and can add more values`() {
        val baseJson: String = json { "value" to 1 }
        val json: String = json {
            expand(baseJson)
            "value_two" to 2
        }
        assertThat(json).isValidJSON()
        assertThat(json).isEqualTo("""{"value":1,"value_two":2}""")
    }
}
