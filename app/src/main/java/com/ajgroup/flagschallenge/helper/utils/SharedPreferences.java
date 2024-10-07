package com.ajgroup.flagschallenge.helper.utils;
import android.content.Context;

public class SharedPreferences {
    public static void setString(String Key, String Value, Context c) {
        android.content.SharedPreferences sharedPreferences = c.getSharedPreferences("ajgroup", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(Key, Value);
        editor.commit();
    }
    public static void setInt(String Key, int Value, Context c) {
        android.content.SharedPreferences sharedPreferences = c.getSharedPreferences("ajgroup", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Key, Value);
        editor.commit();
    }
    public static void setBool(String Key, Boolean Value, Context c) {
        android.content.SharedPreferences sharedPreferences = c.getSharedPreferences("ajgroup", Context.MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(Key, Value);
        editor.commit();
    }
    public static String getString(String Key, Context c) {
        return c.getSharedPreferences("ajgroup", Context.MODE_PRIVATE).getString(Key, "");
    }
    public static int getInt(String Key, Context c) {
        return c.getSharedPreferences("ajgroup", Context.MODE_PRIVATE).getInt(Key, 0);
    }
    public static Boolean getBool(String Key, Context c) {
        return c.getSharedPreferences("ajgroup", Context.MODE_PRIVATE).getBoolean(Key, false);
    }
}
