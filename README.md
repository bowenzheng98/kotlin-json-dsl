# kotlin-json-dsl

A simple kotlin json dsl 

## Usage

Standard usage of this library:

```
val json: String = json { "value" to 1 }
```

The result of this will be a `String` which represents the following json object: 
```lang=json
{
    "value": 1
}
```

json objects can be compositely built with this dsl using the `expand` infix

```
val baseJson: String = json { "value" to 1 }
val json: String = json {
    expand(baseJson)
    "value_two" to 2
}
```

The result of this will be a `String` which represents the following json object:
```lang=json
{
    "value": 1,
    "value_two": 2
}
```

