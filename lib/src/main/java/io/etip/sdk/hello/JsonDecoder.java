package io.etip.sdk.hello;

import java.util.function.BiFunction;

@FunctionalInterface
public interface JsonDecoder {

    <T> T decode(String json, Class<T> clazz);
}

// Implementations examples of Jackson ObjectMapper and Gson
/*
class ObjectMapperDecoder implements JsonDecoder {
    private final ObjectMapper objectMapper;

    public ObjectMapperDecoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public <T> T decode(String json, Class<T> clazz) {
        return objectMapper.readValue(json, clazz);
    }
}

class GsonDecoder implements JsonDecoder {
    private final Gson gson;

    public GsonDecoder(Gson gson) {
        this.gson = gson;
    }

    @Override
    public <T> T decode(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}

class JsonbDecoder implements JsonDecoder {
    private final Jsonb jsonb;

    public JsonbDecoder(Jsonb jsonb) {
        this.jsonb = jsonb;
    }

    @Override
    public <T> T decode(String json, Class<T> clazz) {
        return jsonb.fromJson(json, clazz);
    }
}
*/