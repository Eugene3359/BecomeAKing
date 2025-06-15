package com.scipath.becomeaking.data;

import android.content.Context;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class SaveManager {

    private static final String FILE_NAME = "save.json";

    public static void saveGame(Context context, GameState gameState) {
        try {
            String json = new Gson().toJson(gameState);
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
                return new Gson().fromJson(isr, GameState.class);
            }
        } catch (IOException e) {
            return null;
        }
    }

    public static boolean saveExists(Context context) {
        File file = new File(context.getFilesDir(), FILE_NAME);
        return file.exists();
    }

    public static boolean deleteSave(Context context) {
        return context.deleteFile(FILE_NAME);
    }
}
