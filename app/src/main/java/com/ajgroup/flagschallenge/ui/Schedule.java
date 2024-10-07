package com.ajgroup.flagschallenge.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ajgroup.flagschallenge.R;
import com.ajgroup.flagschallenge.helper.utils.NavigationUtils;
import com.ajgroup.flagschallenge.helper.utils.SharedPreferences;

public class Schedule extends AppCompatActivity {


    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_schedule);


        if (!SharedPreferences.getString("flag_details_question", this).equals("")) {
            NavigationUtils.move_next_activity(this, FlagChallenge.class);
        }

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NavigationUtils.move_next_activity(context, Dashboard.class);
            }
        });



    }
}