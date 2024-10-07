package com.ajgroup.flagschallenge.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;


import com.ajgroup.flagschallenge.Model.CountriesItem;
import com.ajgroup.flagschallenge.R;

import java.util.ArrayList;
import java.util.List;

public class country_name_adapter extends RecyclerView.Adapter<country_name_adapter.MyViewHolder> {
    private Context mContext;
    List<CountriesItem> countriesItems = new ArrayList<>();
    onAnswerClick oac;
    int answerId = 0;
    boolean isShow = false;

    public interface onAnswerClick {
        void onAnswerClick(int position,boolean isRight);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout layout_;
        TextView title_radio_checkbox, right_worng_txt;

        public MyViewHolder(View view) {
            super(view);
            layout_ = (ConstraintLayout) view.findViewById(R.id.select_layout);
            title_radio_checkbox = (TextView) view.findViewById(R.id.title_radio_checkbox);
            right_worng_txt = (TextView) view.findViewById(R.id.right_worng_txt);
        }
    }

    public country_name_adapter(Context mContext, List<CountriesItem> CountriesItem, int answerId, onAnswerClick oac,boolean isShow) {
        this.mContext = mContext;
        this.countriesItems = CountriesItem;
        this.oac = oac;
        this.answerId = answerId;
        this.isShow = isShow;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_name, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,
                                 @SuppressLint("RecyclerView") int position) {
        CountriesItem list = countriesItems.get(position);
        holder.title_radio_checkbox.setText(list.getCountryName());
        if (list.isCheck()) {
            CardView_Setbackground(holder.layout_, "#113e70", "#113e70");
            holder.title_radio_checkbox.setTextColor(Color.parseColor("#ffffff"));
        } else {
            CardView_Setbackground(holder.layout_, "#9d9d9d", "#ffffff");
            holder.title_radio_checkbox.setTextColor(Color.parseColor("#000000"));
        }

//006600
        holder.right_worng_txt.setTextColor((answerId == list.getId()) ? Color.parseColor("#006600") : Color.parseColor("#CC0000"));

        holder.right_worng_txt.setText((answerId == list.getId()) ? "CORRECT" : "WRONG");

        if (isShow) {


            boolean isanswer = (answerId == list.getId());
            if (list.isCheck() || isanswer) {



                holder.right_worng_txt.setVisibility(View.VISIBLE);
            }else {
                holder.right_worng_txt.setVisibility(View.GONE);
            }
        } else {
            holder.right_worng_txt.setVisibility(View.GONE);
        }

        holder.layout_.setEnabled(!isShow );

        holder.layout_.setFilterTouchesWhenObscured(true);
        holder.layout_.setOnClickListener(  new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                update_value(position);
                oac.onAnswerClick(position,(answerId == list.getId()));
                // }
            }
        });
    }

    public void CardView_Setbackground(ConstraintLayout a, String getBorderColor,
                                       String getBackgroundColor) {
        try {
            LayerDrawable Back_action = (LayerDrawable) mContext.getResources()
                    .getDrawable(R.drawable.dynamic_back_desgin);
            GradientDrawable gradientDrawable = (GradientDrawable) Back_action
                    .getDrawable(0);
            gradientDrawable.setColor(Color.parseColor(getBackgroundColor));
            gradientDrawable.setStroke(2, Color.parseColor(getBorderColor));
            gradientDrawable.setCornerRadius(15);
            a.setBackground(Back_action);
        } catch (Exception e) {
        }
    }


    public void showanswer() {
        isShow = true;
        notifyDataSetChanged();

    }

    public void update_value(int position) {

        for (int i = 0; i < countriesItems.size(); i++) {
            countriesItems.get(i).setCheck(i == position);
        }


        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return countriesItems.size();
    }

}
