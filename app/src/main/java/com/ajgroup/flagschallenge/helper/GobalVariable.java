package com.ajgroup.flagschallenge.helper;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GobalVariable {

    public static String loadJSONFromAsset(String jsonFile, Context c) {
        String json = null;
        try {
            InputStream is = c.getAssets().open(jsonFile);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
