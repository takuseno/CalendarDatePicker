package jp.gr.java_conf.androtaku.calendarpicker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by takuma on 2015/03/01.
 */
public class CalendarPicker extends DialogFragment{
    int year,month,dayOfMonth;
    private DateSelectedListener dateSelectedListener = null;
    ParentView parentView;

    public static final int SELECT_DAY_OF_MONTH = 1;
    public static final int SELECT_MONTH = 2;
    public static final int SELECT_YEAR = 3;
    private int minSelect;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        parentView = new ParentView(getActivity());
        parentView.setYear(year);
        parentView.setMonth(month);
        parentView.setDayOfMonth(dayOfMonth);
        if(minSelect == 0){
            minSelect = SELECT_DAY_OF_MONTH;
        }
        parentView.setMinSelect(minSelect);
        switch(minSelect){
            case SELECT_DAY_OF_MONTH:
                parentView.setDayPickerView();
                break;
            case SELECT_MONTH:
                parentView.setMonthPickerView();
                break;
            case SELECT_YEAR:
                parentView.setYearPickerView();
                break;
        }

        builder.setView(parentView)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dateSelectedListener.onDateSelectedListener(getYear(),getMonth(),getDayOfMonth());
                    }
                })
                .setNegativeButton("CANCEL",null);
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public void setDateSelectedListenr(DateSelectedListener dateSelectedListener) {
        this.dateSelectedListener = dateSelectedListener;
    }

    public void setDate(int year,int month,int dayOfMonth){
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
    }

    public void setMinSelect(int level){
        if(level == SELECT_DAY_OF_MONTH || level == SELECT_MONTH || level == SELECT_YEAR){
            minSelect = level;
        }
    }

    public int getDayOfMonth(){
        return parentView.getDayOfMonth();
    }
    public int getYear(){
        return parentView.getYear();
    }
    public int getMonth(){
        return parentView.getMonth();
    }
}
