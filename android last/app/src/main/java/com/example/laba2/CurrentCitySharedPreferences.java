package com.example.laba2;

import android.content.Context;
import android.content.SharedPreferences;


public class CurrentCitySharedPreferences {
    private static final String CURRENT_CITY_PREFERENCES = "CURRENT CITY";
    public static final String CURRENT_CITY_NAME = "CITY NAME";
    public static final String CURRENT_CITY_LANGUAGE = "LANGUAGE";
    public static final String CURRENT_CITY_COUNTRY = "COUNTRY";
    public static final String CURRENT_CITY_POPULATION = "POPULATION";

    private static SharedPreferences mSettings;

    public static void SetValue(Context context, String key, String value) {
        mSettings = context.getSharedPreferences(CURRENT_CITY_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void SetValue(Context context, String key, Integer value) {
        mSettings = context.getSharedPreferences(CURRENT_CITY_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static String GetString(Context context, String key) {
        mSettings = context.getSharedPreferences(CURRENT_CITY_PREFERENCES, Context.MODE_PRIVATE);
        return mSettings.getString(key, null);
    }

    public static Integer GetInteger(Context context, String key) {
        mSettings = context.getSharedPreferences(CURRENT_CITY_PREFERENCES, Context.MODE_PRIVATE);
        return mSettings.getInt(key, Integer.MIN_VALUE);
    }

}
