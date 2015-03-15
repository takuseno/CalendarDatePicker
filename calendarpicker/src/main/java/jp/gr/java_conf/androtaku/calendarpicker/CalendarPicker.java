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
    ParentView parentView;


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        parentView = new ParentView(getActivity());
        parentView.setYear(year);
        parentView.setMonth(month);
        parentView.setDayOfMonth(dayOfMonth);
        parentView.setDayPickerView();

        builder.setView(parentView)
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
        return parentView.getDayOfMonth();
    }
    public int getYear(){
        return parentView.getYear();
    }
    public int getMonth(){
        return parentView.getMonth();
    }
}
