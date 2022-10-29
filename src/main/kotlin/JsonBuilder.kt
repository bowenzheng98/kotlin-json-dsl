import org.json.JSONArray
import org.json.JSONObject
import java.util.*

// Source: https://stackoverflow.com/questions/41861449/kotlin-dsl-for-creating-json-objects-without-creating-garbage

fun json(build: JsonBuilder.() -> Unit): String {
    return JsonBuilder().json(build).toString()
}

class JsonBuilder {
    private val deque: Deque<JSONObject> = ArrayDeque()

    fun json(build: JsonBuilder.() -> Unit): JSONObject {
        deque.push(JSONObject())
        this.build()
        return deque.pop()
    }

    infix fun String.to(value: Int) {
        deque.peek().put(this, value)
    }

    infix fun String.to(array: Array<JSONObject>) {
        val jsonArray = JSONArray().apply { array.forEach { put(it) } }
        deque.peek().put(this, jsonArray)
    }

    infix fun String.to(json: JSONObject) {
        deque.peek().put(this, json)
    }

    infix fun String.to(value: String?) {
        if (value == null) {
            deque.peek().put(this, JSONObject.NULL)
        } else {
            deque.peek().put(this, value)
        }
    }

    infix fun String.to(value: Array<String>) {
        deque.peek().put(this, value)
    }

    infix fun String.to(value: Array<Int>) {
        deque.peek().put(this, value)
    }

    infix fun expand(value: String) {
        val json = JSONObject(value)
        deque.addFirst(json)
    }
}
