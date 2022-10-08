import org.assertj.core.api.StringAssert
import org.json.JSONException
import org.json.JSONObject

// Source: https://github.com/lectra-tech/koson/blob/master/src/test/kotlin/com/lectra/koson/JsonAsserter.kt

fun assertThat(value: String) = JsonAsserter(value)

class JsonAsserter(actual: String) : StringAssert(actual) {

    fun isValidJSON(): JsonAsserter {
        try {
            JSONObject(actual)
        } catch (e: JSONException) {
            failWithMessage("$actual is not a valid JSON")
        }
        return this
    }

}
