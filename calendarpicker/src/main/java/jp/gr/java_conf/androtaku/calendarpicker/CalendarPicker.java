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

    private SelectMode mode = SelectMode.DAY_OF_MONTH;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        parentView = new ParentView(getActivity());
        parentView.setYear(year);
        parentView.setMonth(month);
        parentView.setDayOfMonth(dayOfMonth);

        parentView.setSelectMode(mode);
        switch(mode){
            case DAY_OF_MONTH:
                parentView.setDayPickerView();
                break;
            case MONTH:
                parentView.setMonthPickerView();
                break;
            case YEAR:
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

    public void setSelectMode(SelectMode mode){
        this.mode = mode;
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
