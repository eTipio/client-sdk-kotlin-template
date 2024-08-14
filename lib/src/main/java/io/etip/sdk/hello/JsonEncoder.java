package io.etip.sdk.hello;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.function.Function;

@FunctionalInterface
public interface JsonEncoder {
    public String encode(Object obj);
}

// implementation examples of Jackson ObjectMapper and Gson
/*
class ObjectMapperEncoder implements JsonEncoder {
    private final ObjectMapper objectMapper;

    public ObjectMapperEncoder(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public String encode(Object obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }
}

class GsonEncoder implements JsonEncoder {
    private final Gson gson;

    public GsonEncoder(Gson gson) {
        this.gson = gson;
    }

    @Override
    public String encode(Object obj) {
        return gson.toJson(obj);
    }
}

class JsonbEncoder implements JsonEncoder {
    private final Jsonb jsonb;

    public JsonbEncoder(Jsonb jsonb) {
        this.jsonb = jsonb;
    }

    @Override
    public String encode(Object obj) {
        return jsonb.toJson(obj);
    }
}
*/
