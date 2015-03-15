package jp.gr.java_conf.androtaku.calendarpicker;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by takuma on 2015/03/15.
 */
public class MonthPickerView extends BaseView{

    public MonthPickerView(Context context){
        super(context);
        this.context = context;
    }


    public MonthPickerView(Context context,AttributeSet attrs){
        super(context,attrs);
        this.context = context;
    }


    public MonthPickerView(Context context,AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
        this.context = context;
    }

    public void setMonthPickerView(){
        //set padding to this layout
        this.setPadding(10,10,10,10);

        final Calendar calendar = Calendar.getInstance();
        //set year value
        if(selectedYear <= 0) {
            selectedYear = calendar.get(Calendar.YEAR);
        }
        else{
            calendar.set(Calendar.YEAR,selectedYear);
        }
        //set month value
        if(selectedMonth < 1 || selectedMonth > 12){
            selectedMonth = calendar.get(Calendar.MONTH) + 1;
        }
        else{
            calendar.set(Calendar.MONTH,selectedMonth - 1);
        }
        //set day of month value
        if(selectedDay < 1 || selectedDay > 31){
            selectedDay = calendar.get(Calendar.DAY_OF_MONTH);
        }

        setOrientation(VERTICAL);

        //date text
        final TextView topText = new TextView(context);
        topText.setTextColor(Color.BLACK);
        topText.setText("" + selectedYear);
        topText.setTextSize(20);

        //Top Layout
        LinearLayout.LayoutParams layoutParams
                = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout topLayout = new LinearLayout(context);
        topLayout.setLayoutParams(layoutParams);
        topLayout.setOrientation(HORIZONTAL);
        topLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        Button previousButton = new Button(context);
        previousButton.setText(R.string.prev);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                --selectedYear;
                setYear(selectedYear);
                topText.setText("" + selectedYear);
            }
        });
        Button nextButton = new Button(context);
        nextButton.setText(R.string.next);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++selectedYear;
                setYear(selectedYear);
                topText.setText("" + selectedYear);
            }
        });

        //set button background
        previousButton.setBackgroundResource(R.drawable.button_background);
        nextButton.setBackgroundResource(R.drawable.button_background);

        //set button margin
        LinearLayout.LayoutParams marginLayoutParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        marginLayoutParam.setMargins(50,marginLayoutParam.topMargin,50,marginLayoutParam.bottomMargin);
        nextButton.setLayoutParams(marginLayoutParam);
        previousButton.setLayoutParams(marginLayoutParam);

        topLayout.addView(previousButton);
        topLayout.addView(topText);
        topLayout.addView(nextButton);
        addView(topLayout);

        //Month Layout
        LinearLayout.LayoutParams textLayoutParams
                = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayoutParams.weight = 1.0f;
        for(int i = 0;i < NUM_OF_LINES;++i){
            LinearLayout monthLayout = new LinearLayout(context);
            monthLayout.setLayoutParams(layoutParams);
            monthLayout.setOrientation(HORIZONTAL);
            monthLayout.setPadding(0,20,0,20);
            for(int j = 0;j < NUM_OF_MONTH / NUM_OF_LINES;++j){
                int index = j + (i * NUM_OF_MONTH / NUM_OF_LINES);
                TextView weekdayText = new TextView(context);
                weekdayText.setText(monthNames[index]);
                weekdayText.setTextSize(20);
                monthLayout.addView(wrapText(weekdayText,index + 1));
            }
            addView(monthLayout);
        }
    }

    private LinearLayout wrapText(TextView textView, final int month){
        //selected background
        final GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(20);
        drawable.setColor(Color.parseColor("#00bcd4"));
        final LinearLayout wrapTextLayout = new LinearLayout(context);
        wrapTextLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(prevSelectedLayout != null){
                    prevSelectedLayout.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                }
                if(Build.VERSION.SDK_INT < 16) {
                    wrapTextLayout.setBackgroundDrawable(drawable);
                }
                else{
                    wrapTextLayout.setBackground(drawable);
                }
                prevSelectedLayout = wrapTextLayout;
                selectedMonth = month;

                parentView.setYear(selectedYear);
                parentView.setMonth(selectedMonth);
                parentView.setDayOfMonth(selectedDay);
                if(parentView != null){
                    parentView.setDayPickerView();
                }
            }
        });
        if(selectedMonth == month){
            if(Build.VERSION.SDK_INT < 16) {
                wrapTextLayout.setBackgroundDrawable(drawable);
            }
            else{
                wrapTextLayout.setBackground(drawable);
            }
            prevSelectedLayout = wrapTextLayout;
        }
        LinearLayout.LayoutParams wrapLayoutParams
                = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        wrapLayoutParams.weight = 1.0f;
        wrapTextLayout.setGravity(Gravity.CENTER_HORIZONTAL);
        wrapTextLayout.setLayoutParams(wrapLayoutParams);
        wrapTextLayout.addView(textView);

        return wrapTextLayout;
    }
}
