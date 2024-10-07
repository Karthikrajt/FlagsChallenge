package com.ajgroup.flagschallenge.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ajgroup.flagschallenge.R;


public class NavigationToolBar extends AppCompatActivity {
    ImageView iv_home, iv_sch, iv_notification, iv_help;
    RecyclerView menu_item;
    Context context;
    boolean isDrawerOpen = false;
    ConstraintLayout tool_bar_view;
    @SuppressLint("CheckResult")
    public final void onCreate(Bundle savedInstanceState, int layoutId) {

        super.onCreate(savedInstanceState);
        setContentView(layoutId);
        context = this;
        init();
       ;
    }
    @Override
    protected void onPause() {
        super.onPause();
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
    }
    @Override
    protected void onResume() {
        super.onResume();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_SECURE);
    }

    public void title(String text) {
        ((TextView) findViewById(R.id.txt_title)).setText(text);
    }


    private void init() {
        //  iv_home = findViewById(R.id.iv_home);
        // iv_sch = findViewById(R.id.iv_sch);
        //  iv_notification = findViewById(R.id.iv_notification);
        //  iv_help = findViewById(R.id.iv_help);
        tool_bar_view = findViewById(R.id.tool_bar_view);
    }



    public void MoveBack(View v) {
        getOnBackPressedDispatcher().onBackPressed();
    }


}
