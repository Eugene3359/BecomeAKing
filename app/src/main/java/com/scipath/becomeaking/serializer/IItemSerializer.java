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
import com.scipath.becomeaking.contract.model.IItem;
import com.scipath.becomeaking.model.item.Food;
import com.scipath.becomeaking.model.item.Item;
import com.scipath.becomeaking.model.item.SelectableItem;
import com.scipath.becomeaking.model.item.Work;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;


public class IItemSerializer implements JsonSerializer<IItem>, JsonDeserializer<IItem> {

    private static final String TYPE_FIELD = "type";
    private static final String DATA_FIELD = "data";

    private static final Map<String, Class<? extends IItem>> TYPE_MAP = new HashMap<>();
    static {
        TYPE_MAP.put("item", Item.class);
        TYPE_MAP.put("selectable_item", SelectableItem.class);
        TYPE_MAP.put("food", Food.class);
        TYPE_MAP.put("work", Work.class);
    }

    private final Context context;
    private final Resources res;


    public IItemSerializer(Context context) {
        this.context = context.getApplicationContext();
        this.res = context.getResources();
    }


    @Override
    public JsonElement serialize(IItem src, Type typeOfSrc, JsonSerializationContext context) {
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
    public IItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
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

        Class<? extends IItem> clazz = TYPE_MAP.get(type);
        if (clazz == null) {
            throw new JsonParseException("Unknown type: " + type);
        }

        return context.deserialize(data, clazz);
    }

    private String getTypeName(IItem iItem) {
        for (Map.Entry<String, Class<? extends IItem>> entry : TYPE_MAP.entrySet()) {
            if (entry.getValue().equals(iItem.getClass())) {
                return entry.getKey();
            }
        }
        throw new IllegalArgumentException("Unknown class: " + iItem.getClass().getName());
    }
}