package com.scipath.becomeaking.serializer;

import android.content.Context;
import android.content.res.Resources;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.scipath.becomeaking.contract.model.ICategory;
import com.scipath.becomeaking.model.Category;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


public class ICategoryKeySerializer implements JsonSerializer<ICategory>, JsonDeserializer<ICategory> {

    private static final String TYPE_FIELD = "type";
    private static final String DATA_FIELD = "data";

    private static final Map<String, Class<? extends ICategory>> TYPE_MAP = new HashMap<>();
    static {
        TYPE_MAP.put("category", Category.class);
    }

    private final Context context;
    private final Resources res;


    public ICategoryKeySerializer(Context context) {
        this.context = context.getApplicationContext();
        this.res = context.getResources();
    }


    @Override
    public JsonElement serialize(ICategory src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        String type = getTypeName(src);
        result.addProperty(TYPE_FIELD, type);

        // Serialize
        JsonObject data = context.serialize(src).getAsJsonObject();

        // Replace resource IDs with stable names
        if (data.has("nameId")) {
            int id = data.get("nameId").getAsInt();
            data.addProperty("nameKey", res.getResourceEntryName(id));
            data.remove("nameId");
        }
        if (data.has("imageId")) {
            int id = data.get("imageId").getAsInt();
            data.addProperty("imageKey", res.getResourceEntryName(id));
            data.remove("imageId");
        }

        result.add(DATA_FIELD, data);
        return result;
    }

    @Override
    public ICategory deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get(TYPE_FIELD).getAsString();
        JsonObject data = jsonObject.getAsJsonObject(DATA_FIELD);

        // Convert resource names back to IDs
        if (data.has("nameKey")) {
            String key = data.get("nameKey").getAsString();
            data.addProperty("nameId", res.getIdentifier(key, "string", this.context.getPackageName()));
            data.remove("nameKey");
        }
        if (data.has("imageKey")) {
            String key = data.get("imageKey").getAsString();
            data.addProperty("imageId", res.getIdentifier(key, "drawable", this.context.getPackageName()));
            data.remove("imageKey");
        }

        Class<? extends ICategory> clazz = TYPE_MAP.get(type);
        if (clazz == null) {
            throw new JsonParseException("Unknown type: " + type);
        }

        return context.deserialize(data, clazz);
    }

    private String getTypeName(ICategory iCategory) {
        for (Map.Entry<String, Class<? extends ICategory>> entry : TYPE_MAP.entrySet()) {
            if (entry.getValue().equals(iCategory.getClass())) {
                return entry.getKey();
            }
        }
        throw new IllegalArgumentException("Unknown class: " + iCategory.getClass().getName());
    }
}