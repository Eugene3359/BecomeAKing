package com.scipath.becomeaking.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;


public class LocaleManager {

    // Fields
    private static final String PREFS_NAME = "app_prefs";
    private static final String KEY_LANGUAGE = "language";
    private static Locale currentLocale;
    private static final Locale[] SUPPORTED_LOCALES = {
            new Locale("en"),
            new Locale("ru"),
            new Locale("uk")
    };


    // Methods
    public static Locale[] getLocales() {
        return SUPPORTED_LOCALES;
    }

    public static void setLocale(String languageCode, Context context) {
        currentLocale = new Locale(languageCode);
        Locale.setDefault(currentLocale);

        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(KEY_LANGUAGE, languageCode).apply();

        updateResources(context, currentLocale);
    }

    public static Context wrap(Context context) {
        if (currentLocale == null) {
            // Load saved language
            SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
            String language = prefs.getString(KEY_LANGUAGE, Locale.getDefault().getLanguage());
            currentLocale = new Locale(language);
        }
        return updateResources(context, currentLocale);
    }

    private static Context updateResources(Context context, Locale locale) {
        Resources resources = context.getResources();
        Configuration config = new Configuration(resources.getConfiguration());
        config.setLocale(locale);
        return context.createConfigurationContext(config);
    }
}
