package com.example.CustomerUIDemo.activity.AlarmManagerDemo;


import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.example.CustomerUIDemo.R;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class AlarmManagerDemoActivity extends Activity implements OnClickListener {

    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    private Button setAlarmButton;
    private Button cancelAlarmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alarm_manager_demo_activity);

        alarmManager = (AlarmManager) getBaseContext().getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent("com.example.CustomerUIDemo.AlarmManager.action");
        pendingIntent = PendingIntent.getBroadcast(getBaseContext(), 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);


        setAlarmButton = (Button) findViewById(R.id.alarmManagerDemo_setAlarmButton);
        setAlarmButton.setOnClickListener(this);

        cancelAlarmButton = (Button) findViewById(R.id.alarmManagerDemo_cancelAlarmButton);
        cancelAlarmButton.setOnClickListener(this);


    }

    private void setAlarmManage() {
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Taipei");
        Calendar calendar = new GregorianCalendar(timeZone);

        // Add 10 seconds to now time.
        calendar.add(Calendar.SECOND, 10);


        // Every 1 hour alarm one time.
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
//                calendar.getTimeInMillis(), AlarmManager.INTERVAL_HOUR,
//                pendingIntent);

        // Every 5 seconds alarm one time.
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(), 5000,
                pendingIntent);

        Toast.makeText(this, "Setting Alarm Manager", Toast.LENGTH_LONG).show();
    }

    private void cancelAlarmManage(){
        alarmManager.cancel(pendingIntent);
        Toast.makeText(this, "Cancel Alarm Manager", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.alarmManagerDemo_setAlarmButton:
                setAlarmManage();
                break;

            case R.id.alarmManagerDemo_cancelAlarmButton:
                cancelAlarmManage();
                break;

            default:
                break;

        }
    }


}
