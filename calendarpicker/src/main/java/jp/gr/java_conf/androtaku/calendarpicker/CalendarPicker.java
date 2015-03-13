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
    private DialogInterface.OnClickListener okClickListener = null;
    DayPickerView dayPickerView;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        dayPickerView = new DayPickerView(getActivity());
        dayPickerView.setYear(year);
        dayPickerView.setMonth(month);
        dayPickerView.setDayOfMonth(dayOfMonth);
        dayPickerView.setDayPickerView();

        builder.setView(dayPickerView)
                .setPositiveButton("OK", okClickListener)
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public void setOnOkClickListener(DialogInterface.OnClickListener listener) {
        this.okClickListener = listener;
    }

    public void setDate(int year,int month,int dayOfMonth){
        this.year = year;
        this.month = month;
        this.dayOfMonth = dayOfMonth;
    }

    public int getDayOfMonth(){
        return dayPickerView.getDayOfMonth();
    }
    public int getYear(){
        return dayPickerView.getYear();
    }
    public int getMonth(){
        return dayPickerView.getMonth();
    }
}
