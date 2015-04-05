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

    public static final int SELECT_DAY_OF_MONTH = 1;
    public static final int SELECT_MONTH = 2;
    public static final int SELECT_YEAR = 3;
    private int minSelect;

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
        if(minSelect == SELECT_DAY_OF_MONTH) {
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
        if(minSelect == SELECT_MONTH || minSelect == SELECT_DAY_OF_MONTH) {
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

    public void setMinSelect(int level){
        if(level == SELECT_DAY_OF_MONTH || level == SELECT_MONTH || level == SELECT_YEAR){
            minSelect = level;
        }
    }
}
