package com.example.CustomerUIDemo.activity.AlarmManagerDemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.util.Calendar;


public class AlarmManagerReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; // Jan = 0, dec = 11
        int day = calendar.get(Calendar.DATE);
        int hour = calendar.get(Calendar.HOUR); // 12 hour clock
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        int millisecond = calendar.get(Calendar.MILLISECOND);

        Toast.makeText(context, "Now Time \n" +
                        "Date: " + year + " - " + month + " - " + day + "\n" +
                        "Time: " + hourOfDay + " : " + minute + " : " + second + " : " + millisecond,
                Toast.LENGTH_LONG).show();
    }
}
