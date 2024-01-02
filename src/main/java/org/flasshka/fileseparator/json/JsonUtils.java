package org.flasshka.fileseparator.json;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtils {
    private final static ObjectMapper OBJ_MAPPER = new ObjectMapper();

    public static <T> String objToJson(T obj) throws IOException {
        return OBJ_MAPPER.writeValueAsString(obj);
    }

    public static <T> T jsonToObj(String json, Class<T> tClass) throws IOException {
        return OBJ_MAPPER.readValue(json, tClass);
    }
}
