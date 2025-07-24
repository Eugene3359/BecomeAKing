package com.scipath.becomeaking.serializer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.model.Stats;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


public class IStatsSerializer implements JsonSerializer<IStats>, JsonDeserializer<IStats> {

    private static final String TYPE_FIELD = "type";
    private static final String DATA_FIELD = "data";

    private static final Map<String, Class<? extends IStats>> TYPE_MAP = new HashMap<>();

    static {
        TYPE_MAP.put("stats", Stats.class);
    }


    @Override
    public JsonElement serialize(IStats src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        String type = getTypeName(src);
        result.addProperty(TYPE_FIELD, type);
        result.add(DATA_FIELD, context.serialize(src));
        return result;
    }


    @Override
    public IStats deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get(TYPE_FIELD).getAsString();
        JsonElement data = jsonObject.get(DATA_FIELD);

        Class<? extends IStats> clazz = TYPE_MAP.get(type);
        if (clazz == null) {
            throw new JsonParseException("Unknown type: " + type);
        }

        return context.deserialize(data, clazz);
    }

    private String getTypeName(IStats iStats) {
        for (Map.Entry<String, Class<? extends IStats>> entry : TYPE_MAP.entrySet()) {
            if (entry.getValue().equals(iStats.getClass())) {
                return entry.getKey();
            }
        }
        throw new IllegalArgumentException("Unknown class: " + iStats.getClass().getName());
    }
}
