package jp.gr.java_conf.androtaku.calendardatepicker;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by takuma on 2015/03/01.
 */
public class CalendarDatePicker extends DialogFragment{
    int year,month,day;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final DayPickerView dayPickerView = new DayPickerView(getActivity());
        dayPickerView.setDayPickerView(year, month);

        builder.setView(dayPickerView)
               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        day = dayPickerView.getDayOfMonth();
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public void setDate(int year,int month){
        this.year = year;
        this.month = month;
    }

    public int getDayOfMonth(){
        return day;
    }
}
