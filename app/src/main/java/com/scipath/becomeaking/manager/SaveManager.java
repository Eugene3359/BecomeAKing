package com.scipath.becomeaking.manager;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scipath.becomeaking.contract.model.ICategory;
import com.scipath.becomeaking.contract.model.ILevel;
import com.scipath.becomeaking.contract.model.IPersonage;
import com.scipath.becomeaking.contract.model.IStats;
import com.scipath.becomeaking.model.GameState;
import com.scipath.becomeaking.contract.model.IItem;
import com.scipath.becomeaking.serializer.ICategorySerializer;
import com.scipath.becomeaking.serializer.IItemSerializer;
import com.scipath.becomeaking.serializer.ILevelSerializer;
import com.scipath.becomeaking.serializer.IPersonageSerializer;
import com.scipath.becomeaking.serializer.IStatsSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;


public class SaveManager {

    private static final String FILE_NAME = "save.json";

    public static void saveGame(GameState gameState, Context context) {
        try {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(IStats.class, new IStatsSerializer())
                    .registerTypeAdapter(IItem.class, new IItemSerializer(context))
                    .registerTypeAdapter(ICategory.class, new ICategorySerializer(context))
                    .registerTypeAdapter(ILevel.class, new ILevelSerializer())
                    .registerTypeAdapter(IPersonage.class, new IPersonageSerializer())
                    .create();
            String json = gson.toJson(gameState);
            try (FileOutputStream fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
                fos.write(json.getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameState loadGame(Context context) {
        try {
            try (FileInputStream fis = context.openFileInput(FILE_NAME)) {
                InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
                Gson gson = new GsonBuilder()
                        .registerTypeAdapter(IStats.class, new IStatsSerializer())
                        .registerTypeAdapter(IItem.class, new IItemSerializer(context))
                        .registerTypeAdapter(ICategory.class, new ICategorySerializer(context))
                        .registerTypeAdapter(ILevel.class, new ILevelSerializer())
                        .registerTypeAdapter(IPersonage.class, new IPersonageSerializer())
                        .create();
                return gson.fromJson(isr, GameState.class);
            }
        } catch (IOException e) {
            return null;
        }
    }

    public static boolean saveExists(Context context) {
        File file = new File(context.getFilesDir(), FILE_NAME);
        return file.exists();
    }

    public static void deleteSave(Context context) {
        context.deleteFile(FILE_NAME);
    }
}
