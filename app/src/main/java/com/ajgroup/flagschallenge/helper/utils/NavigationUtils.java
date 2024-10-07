package com.ajgroup.flagschallenge.helper.utils;
import android.content.Context;
import android.content.Intent;

public class NavigationUtils {
    public static void move_next_activity(Context Context, Class next_activity){
        Intent goToNextActivity = new Intent(Context, next_activity);
        Context.startActivity(goToNextActivity);
    }
}
