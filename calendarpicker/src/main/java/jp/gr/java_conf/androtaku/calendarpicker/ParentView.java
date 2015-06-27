package jp.gr.java_conf.androtaku.calendarpicker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by takuma on 2015/03/15.
 */
public class ParentView extends LinearLayout {
    private Context context;

    private int selectedDay;
    private int selectedYear,selectedMonth;

    private SelectMode mode = SelectMode.DAY_OF_MONTH;

    public ParentView(Context context){
        super(context);
        this.context = context;
        this.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams layoutParams
                = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setLayoutParams(layoutParams);
    }


    public ParentView(Context context,AttributeSet attrs){
        super(context,attrs);
        this.context = context;
        this.setGravity(Gravity.CENTER);
    }


    public ParentView(Context context,AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
        this.context = context;
        this.setGravity(Gravity.CENTER);
    }

    public void setDayPickerView(){
        if(mode == SelectMode.DAY_OF_MONTH) {
            removeAllViews();
            DayPickerView dayPickerView = new DayPickerView(context);
            dayPickerView.setYear(selectedYear);
            dayPickerView.setMonth(selectedMonth);
            dayPickerView.setDayOfMonth(selectedDay);
            dayPickerView.setParentView(this);
            dayPickerView.setDayPickerView();
            addView(dayPickerView);
        }
    }

    public void setMonthPickerView(){
        if(mode != SelectMode.YEAR) {
            removeAllViews();
            MonthPickerView monthPickerView = new MonthPickerView(context);
            monthPickerView.setYear(selectedYear);
            monthPickerView.setMonth(selectedMonth);
            monthPickerView.setDayOfMonth(selectedDay);
            monthPickerView.setParentView(this);
            monthPickerView.setMonthPickerView();
            addView(monthPickerView);
        }
    }

    public void setYearPickerView(){
        removeAllViews();
        YearPickerView yearPickerView = new YearPickerView(context);
        yearPickerView.setYear(selectedYear);
        yearPickerView.setMonth(selectedMonth);
        yearPickerView.setDayOfMonth(selectedDay);
        yearPickerView.setParentView(this);
        yearPickerView.setYearPickerView();
        addView(yearPickerView);
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

    public void setSelectMode(SelectMode mode){
        this.mode = mode;
    }
}
