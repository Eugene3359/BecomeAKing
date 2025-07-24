package com.scipath.becomeaking.serializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import com.scipath.becomeaking.contract.model.ILevel;
import com.scipath.becomeaking.model.Level;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


public class ILevelSerializer implements JsonSerializer<ILevel>, JsonDeserializer<ILevel> {

    private static final String TYPE_FIELD = "type";
    private static final String DATA_FIELD = "data";

    private static final Map<String, Class<? extends ILevel>> TYPE_MAP = new HashMap<>();

    static {
        TYPE_MAP.put("level", Level.class);
    }


    @Override
    public JsonElement serialize(ILevel src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        String type = getTypeName(src);
        result.addProperty(TYPE_FIELD, type);
        result.add(DATA_FIELD, context.serialize(src));
        return result;
    }


    @Override
    public ILevel deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get(TYPE_FIELD).getAsString();
        JsonElement data = jsonObject.get(DATA_FIELD);

        Class<? extends ILevel> clazz = TYPE_MAP.get(type);
        if (clazz == null) {
            throw new JsonParseException("Unknown type: " + type);
        }

        return context.deserialize(data, clazz);
    }

    private String getTypeName(ILevel iLevel) {
        for (Map.Entry<String, Class<? extends ILevel>> entry : TYPE_MAP.entrySet()) {
            if (entry.getValue().equals(iLevel.getClass())) {
                return entry.getKey();
            }
        }
        throw new IllegalArgumentException("Unknown class: " + iLevel.getClass().getName());
    }
}