package com.scipath.becomeaking.serializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import com.scipath.becomeaking.contract.model.IPersonage;
import com.scipath.becomeaking.model.Personage;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


public class IPersonageSerializer implements JsonSerializer<IPersonage>, JsonDeserializer<IPersonage> {

    private static final String TYPE_FIELD = "type";
    private static final String DATA_FIELD = "data";

    private static final Map<String, Class<? extends IPersonage>> TYPE_MAP = new HashMap<>();

    static {
        TYPE_MAP.put("personage", Personage.class);
    }


    @Override
    public JsonElement serialize(IPersonage src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        String type = getTypeName(src);
        result.addProperty(TYPE_FIELD, type);
        result.add(DATA_FIELD, context.serialize(src));
        return result;
    }


    @Override
    public IPersonage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get(TYPE_FIELD).getAsString();
        JsonElement data = jsonObject.get(DATA_FIELD);

        Class<? extends IPersonage> clazz = TYPE_MAP.get(type);
        if (clazz == null) {
            throw new JsonParseException("Unknown type: " + type);
        }

        return context.deserialize(data, clazz);
    }

    private String getTypeName(IPersonage iPersonage) {
        for (Map.Entry<String, Class<? extends IPersonage>> entry : TYPE_MAP.entrySet()) {
            if (entry.getValue().equals(iPersonage.getClass())) {
                return entry.getKey();
            }
        }
        throw new IllegalArgumentException("Unknown class: " + iPersonage.getClass().getName());
    }
}