package com.example.neelimapradhan.ourstory;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * Created by Chris on 4/2/2017.
 */

public class CreateEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_create_event);

        /*Toolbar Setup*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*Setup Date and Time Pickers*/
        final TextView showDate = (TextView) findViewById(R.id.date_field);
        showDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                DialogFragment timeFragment = new TimePickerFragment();
                timeFragment.show(getFragmentManager(),"TimePicker");

                DialogFragment dateFragment = new DatePickerFragment();
                dateFragment.show(getFragmentManager(),"DatePicker");
            }
        });

        Button saveEventButton = (Button) findViewById(R.id.save_button);
        saveEventButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setResult(RESULT_FIRST_USER);
                finish();
            }
        });

        Button publishEventButton = (Button) findViewById(R.id.publish_button);
        publishEventButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();
            }
        });


    }

    public static class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {
        public TimePickerFragment() {}

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            TextView dateText = (TextView) getActivity().findViewById(R.id.date_field);
            GregorianCalendar c = new GregorianCalendar(0,0,0,hourOfDay,minute);

            if (view.isShown())
                dateText.setText(dateText.getText().toString() + " - " +
                        new SimpleDateFormat("h:mm a").format(c.getTime()));
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            TextView dateText = (TextView) getActivity().findViewById(R.id.date_field);
            GregorianCalendar c = new GregorianCalendar(year,month,day);
            dateText.setText(new SimpleDateFormat("MMMM d, y").format(c.getTime()));
        }
    }
}

