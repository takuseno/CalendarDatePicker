package jp.gr.java_conf.androtaku.calendardatepicker;

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

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * Created by takuma on 2015/03/01.
 */
public class DayPickerView extends LinearLayout {

    private Context context;

    private LinearLayout prevSelectedLayout;

    public String weekday[] = {"Su","Mo","Tu","We","Th","Fr","St"};
    private static final int NUM_OF_WEEKDAYS = 7;
    private static final int MAX_NUM_OF_WEEKS = 5;

    private int selectedDay;
    private int selectedYear,selectedMonth;

    public DayPickerView(Context context){
        super(context);
        this.context = context;
    }


    public DayPickerView(Context context,AttributeSet attrs){
        super(context,attrs);
        this.context = context;
    }


    public DayPickerView(Context context,AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
        this.context = context;
    }

    public void setDayPickerView(){
        //set padding to this layout
        this.setPadding(10,20,10,20);

        final Calendar calendar = Calendar.getInstance();
        if(selectedYear == 0 || selectedMonth == 0) {
            selectedYear = calendar.get(Calendar.YEAR);
            selectedMonth = calendar.get(Calendar.MONTH) + 1;
        }
        else{
            calendar.set(Calendar.YEAR,selectedYear);
            calendar.set(Calendar.MONTH,selectedMonth - 1);
        }
        //set date as the first day of month
        calendar.set(Calendar.DAY_OF_MONTH,1);

        setOrientation(VERTICAL);

        //Top Layout
        LinearLayout.LayoutParams layoutParams
                = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout topLayout = new LinearLayout(context);
        topLayout.setLayoutParams(layoutParams);
        topLayout.setOrientation(HORIZONTAL);
        topLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        Button previousButton = new Button(context);
        previousButton.setText("Prev");
        previousButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAllViews();
                --selectedMonth;
                if (selectedMonth == 0) {
                    selectedMonth = 12;
                    --selectedYear;
                }
                setYear(selectedYear);
                setMonth(selectedMonth);
                setDayPickerView();
            }
        });
        Button nextButton = new Button(context);
        nextButton.setText("Next");
        nextButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAllViews();
                ++selectedMonth;
                if(selectedMonth == 13){
                    selectedMonth = 1;
                    ++selectedYear;
                }
                setYear(selectedYear);
                setMonth(selectedMonth);
                setDayPickerView();
            }
        });

        TextView topText = new TextView(context);
        DecimalFormat decimalFormat = new DecimalFormat("00");
        topText.setText(selectedYear + "/" + decimalFormat.format(selectedMonth));

        topLayout.addView(previousButton);
        topLayout.addView(topText);
        topLayout.addView(nextButton);
        addView(topLayout);

        //Weekday Layout
        LayoutParams textLayoutParams
                = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textLayoutParams.weight = 1.0f;
        LinearLayout weekdayLayout = new LinearLayout(context);
        weekdayLayout.setLayoutParams(layoutParams);
        weekdayLayout.setOrientation(HORIZONTAL);
        for(int i = 0;i < NUM_OF_WEEKDAYS;++i){
            TextView weekdayText = new TextView(context);
            weekdayText.setText(weekday[i]);
            weekdayText.setTextColor(Color.rgb(0, 0, 0));
            weekdayText.setTextSize(20);
            weekdayLayout.addView(wrapText(weekdayText,-1));
        }
        addView(weekdayLayout);

        //Date Layout
        LinearLayout dateLayout = new LinearLayout(context);
        dateLayout.setLayoutParams(layoutParams);
        dateLayout.setOrientation(VERTICAL);

        //layout for one week
        LinearLayout lineLayout[] = new LinearLayout[MAX_NUM_OF_WEEKS];
        for(int i = 0;i < MAX_NUM_OF_WEEKS;++i) {
            lineLayout[i] = new LinearLayout(context);
            lineLayout[i].setLayoutParams(layoutParams);
            lineLayout[i].setOrientation(HORIZONTAL);
        }

        //fill blanks until the first day
        int startWeek = calendar.get(Calendar.DAY_OF_WEEK);
        for(int i = 0;i < startWeek - 1;++i){
            TextView blankText = new TextView(context);
            lineLayout[0].addView(wrapText(blankText,-1));
        }
        //fill the rest of dates
        int weekIndex = startWeek;
        boolean endOfMonth = false;
        for(int i = 0;i < MAX_NUM_OF_WEEKS;++i){
            while(weekIndex <= NUM_OF_WEEKDAYS){
                TextView dateText = new TextView(context);
                dateText.setText("" + calendar.get(Calendar.DAY_OF_MONTH));
                dateText.setTextSize(20);
                lineLayout[i].addView(wrapText(dateText,calendar.get(Calendar.DAY_OF_MONTH)));
                calendar.add(Calendar.DAY_OF_MONTH,1);
                //if calendar moves to next month. exit this loop
                if(calendar.get(Calendar.DAY_OF_MONTH) == 1){
                    endOfMonth = true;
                    break;
                }
                ++weekIndex;
            }
            //fill the rest with blank
            if(endOfMonth){
                while(weekIndex < NUM_OF_WEEKDAYS){
                    TextView blankText = new TextView(context);
                    lineLayout[i].addView(wrapText(blankText,-1));
                    ++weekIndex;
                }
                dateLayout.addView(lineLayout[i]);
                break;
            }
            dateLayout.addView(lineLayout[i]);
            weekIndex = 1;
        }

        addView(dateLayout);
    }

    private LinearLayout wrapText(TextView textView, final int day){
        //selected background
        final GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(20);
        drawable.setColor(Color.parseColor("#66AAAAAA"));

        final LinearLayout wrapTextLayout = new LinearLayout(context);
        if(day != -1){
            wrapTextLayout.setOnClickListener(new OnClickListener() {
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
                    selectedDay = day;
                }
            });
        }
        if(selectedDay == day){
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

    public int getDayOfMonth(){
        return selectedDay;
    }
    public int getYear(){
        return selectedYear;
    }
    public int getMonth(){
        return selectedMonth;
    }

    public void setDayOfMonth(int dayOfMonth){
        selectedDay = dayOfMonth;
    }
    public void setYear(int year){
        selectedYear = year;
    }
    public void setMonth(int month){
        selectedMonth = month;
    }
}