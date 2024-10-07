package com.ajgroup.flagschallenge.ui;



import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.PictureDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajgroup.flagschallenge.Controller.FlagController;
import com.ajgroup.flagschallenge.Model.Questiondetails;
import com.ajgroup.flagschallenge.R;
import com.ajgroup.flagschallenge.adapter.country_name_adapter;
import com.ajgroup.flagschallenge.helper.GobalVariable;
import com.ajgroup.flagschallenge.helper.NavigationToolBar;
import com.ajgroup.flagschallenge.helper.SvgSoftwareLayerSetter;
import com.ajgroup.flagschallenge.helper.utils.NavigationUtils;
import com.ajgroup.flagschallenge.helper.utils.SharedPreferences;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;


public class FlagChallenge extends NavigationToolBar {


    FlagController Fc;

    // TextView timer_txt;

    Context context = this;

    ImageView flag_image;

    RecyclerView country_name_list;

    Questiondetails qd;


    TextView timer_text_top_bar,question_number;

    boolean answer_submit = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_flag_challenge);
        getSupportActionBar().hide();

        init();

    }

    country_name_adapter country_name;
    public void init() {
        Fc = new FlagController(this);
        title("Flags Challenge");
        timer_text_top_bar = findViewById(R.id.timer_text_top_bar);

        flag_image = findViewById(R.id.flag_image);
        country_name_list = findViewById(R.id.country_name_list);
        question_number = findViewById(R.id.question_number);
        qd = new Gson().fromJson(SharedPreferences.getString("flag_details_question", this), Questiondetails.class);
        setquestion();

        ((Button) findViewById(R.id.save_submit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(answer_submit)
                {
                    setquestion();
                }else
                {
                    updateAnswer();
                }
            }
        });

    }

    int index = -1;
    public void setquestion() {
        index = get_question_details_index();

        if(index != -1) {
            if (CDT != null) {
                CDT.cancel();
            }

            findViewById(R.id.challege_card).setVisibility(View.VISIBLE);
            //game_over
            findViewById(R.id.game_over).setVisibility(View.GONE);
            ((Button) findViewById(R.id.save_submit)).setText("Check Answer");

            answer_submit = false;


            // if(flag_image.)
            // Picasso.get().load(qd.getQuestions().get(index).getImageurl()).into(flag_image);

            //   if (!qd.getQuestions().get(index).getImageurl().toLowerCase().endsWith(".svg")) {
            Picasso.get().load(qd.getQuestions().get(index).getImageurl()).into(flag_image);


            findViewById(R.id.save_submit).setEnabled(false);
            country_name = new country_name_adapter(this, qd.getQuestions().get(index).getCountries(), qd.getQuestions().get(index).getAnswerId(), new country_name_adapter.onAnswerClick() {
                @Override
                public void onAnswerClick(int position, boolean isMarkedCorrect) {
                    qd.getQuestions().get(index).setIsMarked(true);
                    qd.getQuestions().get(index).setMarkedCorrectly(isMarkedCorrect);
                }
            }, false);

            question_number.setText("" + ((index + 1)));

            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
            country_name_list.setLayoutManager(mLayoutManager);
            country_name_list.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(1), true));
            country_name_list.setItemAnimator(new DefaultItemAnimator());
            country_name_list.setAdapter(country_name);
            cal_counter_30_sec();
        }else
        {
            findViewById(R.id.challege_card).setVisibility(View.GONE);
            //game_over
            findViewById(R.id.game_over).setVisibility(View.VISIBLE);


            int totalQuestions = qd.getQuestions().size();

            int correctAnswers = 0;

            for (int i = 0; i < qd.getQuestions().size(); i++) {
                if (qd.getQuestions().get(i).isMarkedCorrectly()) {
                    correctAnswers++;
                }
            }


            // Calculate the score on a 100-point scale
            double score = (correctAnswers / (double)totalQuestions) * 100;

            ((TextView) findViewById(R.id.score_txt)).setText(""+String.format("%.2f", score)+"/100");

            findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.setString("flag_details_question", "",context);
                    NavigationUtils.move_next_activity(context, Schedule.class);
                }
            });
        }

    }


    int get_question_details_index() {
        int index = -1;
        for (int i = 0; i < qd.getQuestions().size(); i++) {
            if (!qd.getQuestions().get(i).isIsMarked()) {
                return i;
            }
        }
        return -1;
    }

    CountDownTimer CDT;



    public  void updateAnswer()
    {

        qd.getQuestions().get(index).setIsMarked(true);

        if (CDT != null) {
            CDT.cancel();
        }

        country_name = new country_name_adapter(context, qd.getQuestions().get(index).getCountries(), qd.getQuestions().get(index).getAnswerId(), new country_name_adapter.onAnswerClick() {
            @Override
            public void onAnswerClick(int position, boolean isMarkedCorrect) {
             //   qd.getQuestions().get(index).setIsMarked(true);
             //   qd.getQuestions().get(index).setMarkedCorrectly(isMarkedCorrect);
            }
        },true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        country_name_list.setLayoutManager(mLayoutManager);
        country_name_list.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(1), true));
        country_name_list.setItemAnimator(new DefaultItemAnimator());
        country_name_list.setAdapter(country_name);
        answer_submit = true;
        Gson gson = new Gson();
        String json = gson.toJson(qd);
        ((Button) findViewById(R.id.save_submit)).setText("Submit/Next");

        SharedPreferences.setString("flag_details_question", json, context);


    }


    void cal_counter_30_sec() {
        CDT = new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {

                String sec = "" + millisUntilFinished / 1000;

                String txt_sec = (sec.length() == 2) ? sec : "0" + sec;

                if ((millisUntilFinished / 1000) < 20) {
                    findViewById(R.id.save_submit).setEnabled(true);
                }

                timer_text_top_bar.setText("00 :" + txt_sec);
            }

            public void onFinish() {
                updateAnswer();

            }

        }.start();
    }


    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public static class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private final int spanCount;
        private final int spacing;
        private final boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(
                @NonNull Rect outRect, @NonNull View view, RecyclerView parent,
                @NonNull RecyclerView.State state
        ) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column
            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f
                // / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f /
                // spanCount) * spacing)
                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) *
                // spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing -
                // (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                r.getDisplayMetrics()
        ));
    }


}