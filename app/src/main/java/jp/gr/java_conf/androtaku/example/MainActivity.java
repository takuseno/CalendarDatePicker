package jp.gr.java_conf.androtaku.example;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import jp.gr.java_conf.androtaku.calendarpicker.CalendarPicker;
import jp.gr.java_conf.androtaku.calendarpicker.DateSelectedListener;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalendarPicker calendarPicker = new CalendarPicker();
                calendarPicker.setDate(2014,4,23);
                calendarPicker.setDateSelectedListenr(new DateSelectedListener() {
                    @Override
                    public void onDateSelectedListener(int year, int month, int dayOfMonth) {
                        String date = "" + year+ "/" + month + "/" + dayOfMonth;
                        Toast.makeText(getApplicationContext(),date,Toast.LENGTH_SHORT).show();
                    }
                });
                calendarPicker.show(getSupportFragmentManager(),"calendar");
            }
        });

        final DatePickerDialog datePickerDialog = new DatePickerDialog(this,null,2014,4,23);
        Button button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
