package com.ajgroup.flagschallenge.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ajgroup.flagschallenge.Controller.FlagController;
import com.ajgroup.flagschallenge.R;
import com.ajgroup.flagschallenge.helper.GobalVariable;
import com.ajgroup.flagschallenge.helper.NavigationToolBar;
import com.ajgroup.flagschallenge.helper.utils.NavigationUtils;
import com.ajgroup.flagschallenge.helper.utils.SharedPreferences;

public class Dashboard extends NavigationToolBar {

    FlagController Fc;

    TextView timer_txt;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dash_baord);
        getSupportActionBar().hide();

        init();
       // SharedPreferences.setString("flag_details_question", "",this);
        if (SharedPreferences.getBool("isChallengeStarted", this) && !SharedPreferences.getString("flag_details_question", this).equals("")) {
            NavigationUtils.move_next_activity(this, FlagChallenge.class);
        }else
        {
            cal_counter_20_sec();
        }


    }

    public void init() {
        Fc = new FlagController(this);
        title("Flags Challenge");
        timer_txt = findViewById(R.id.timer_txt);


    }


    void cal_counter_20_sec() {
        new CountDownTimer(20000, 1000) {

            public void onTick(long millisUntilFinished) {

                String sec = "" + millisUntilFinished / 1000;

                String txt_sec = (sec.length() == 2) ? sec : "0" + sec;

                timer_txt.setText("00 :" + txt_sec);
            }

            public void onFinish() {
                SharedPreferences.setBool("isChallengeStarted", true, context);
                String question_detaills = GobalVariable.loadJSONFromAsset("question.json", context);
                SharedPreferences.setString("flag_details_question", question_detaills, context);
                NavigationUtils.move_next_activity(context, FlagChallenge.class);
            }

        }.start();
    }


}