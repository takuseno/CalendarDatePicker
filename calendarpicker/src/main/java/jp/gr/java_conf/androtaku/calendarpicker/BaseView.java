package jp.gr.java_conf.androtaku.calendarpicker;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by takuma on 2015/03/15.
 */
public class BaseView extends LinearLayout {

    public Context context;

    public LinearLayout prevSelectedLayout;

    //parent view
    public ParentView parentView;

    public String weekNames[] = {"Su","Mo","Tu","We","Th","Fr","St"};
    public String monthNames[] = {"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};

    public static final int NUM_OF_WEEKDAYS = 7;
    public static final int MAX_NUM_OF_WEEKS = 5;
    public static final int NUM_OF_MONTH = 12;
    public static final int NUM_OF_LINES = 3;

    public int selectedYear,selectedMonth,selectedDay;

    public BaseView(Context context){
        super(context);
        this.context = context;
    }


    public BaseView(Context context,AttributeSet attrs){
        super(context,attrs);
        this.context = context;
    }


    public BaseView(Context context,AttributeSet attrs,int defStyleAttr){
        super(context,attrs,defStyleAttr);
        this.context = context;
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

    public void setParentView(ParentView parentView){
        this.parentView = parentView;
    }
}
