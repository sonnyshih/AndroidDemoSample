package com.example.CustomerUIDemo.activity.SendNotificationDemo;

import com.example.CustomerUIDemo.R;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

public class SendNotificationDemoActivity extends Activity implements
		OnClickListener {

	private Button sendSimpleNotificationButton;
	private Button sendStyleLayoutNotificationButton;
	private Button sendCustomerNotificationButton;
	private Button sendProgressNotificationButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send_notification_demo_activity);

		sendSimpleNotificationButton = (Button) findViewById(R.id.sendNotificationDemo_sendSimpleNotificationButton);
		sendSimpleNotificationButton.setOnClickListener(this);

		sendStyleLayoutNotificationButton = (Button) findViewById(R.id.sendNotificationDemo_sendStyleLayoutNotificationButton);
		sendStyleLayoutNotificationButton.setOnClickListener(this);

		sendCustomerNotificationButton = (Button) findViewById(R.id.sendNotificationDemo_sendCustomerNotificationButton);
		sendCustomerNotificationButton.setOnClickListener(this);

		sendProgressNotificationButton = (Button) findViewById(R.id.sendNotificationDemo_sendProgressNotificationButton);
		sendProgressNotificationButton.setOnClickListener(this);
		
	}

	// Simple Notification
	private void onSimpleNotificationClick() {

		long[] vibratepattern = {100, 400, 500, 400};	// define the shocking time
		
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				this).setSmallIcon(android.R.drawable.ic_dialog_alert)
				.setContentTitle("My notification")
				.setContentText("Hello World!")
				.setDefaults(Notification.DEFAULT_SOUND)	// Setting the sounds
				.setLights(0xFFFFFFFF, 1000, 1000)			// Setting LED light
				.setVibrate(vibratepattern)					// setting the shocking time (Must setting <uses-permission android:name="android.permission.VIBRATE"/> in Manifest.xml)
				.setAutoCancel(true);	// After clicking notification, disappear the notification.

		// Creates an explicit intent for an Activity in your app
		// Must setting the xxxManifest.xml for NotificationResultActivity
		Intent resultIntent = new Intent(this, NotificationResultActivity.class);

		// The stack builder object will contain an artificial back stack for
		// the started Activity.
		// This ensures that navigating backward from the Activity leads out of
		// your application to the Home screen.
		TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

		// Adds the back stack for the Intent (but not the Intent itself)
		stackBuilder.addParentStack(NotificationResultActivity.class);

		// Adds the Intent that starts the Activity to the top of the stack
		stackBuilder.addNextIntent(resultIntent);

		PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,
				PendingIntent.FLAG_UPDATE_CURRENT);
		
		builder.setContentIntent(resultPendingIntent);
		
		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		// id allows you to update the notification later on.
		// id is unique. 
		// If you send the same id, the notification will be recovered.
		// If you send the different ids, it will display different notifications.
		int id = 0;
		notificationManager.notify(id, builder.build());
		
		// Send different ids, display multi notifications
//		for (int i = 0; i < 3; i++) {
//			notificationManager.notify(i, mBuilder.build());	
//		}

	}

	// Style Layout Notification
	private void onStyleLayoutNotificationClick(){
		
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				this).setSmallIcon(android.R.drawable.ic_dialog_alert)
				.setContentTitle("Event tracker")
				.setContentText("Events received")
				.setAutoCancel(true);	// After clicking notification, disappear the notification.
		
		NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
		String[] events = {"hello", "hello world!!", "Good Day"};
		
		// Sets a title for the Inbox in expanded layout
		inboxStyle.setBigContentTitle("Event tracker details:");
		
		// Moves events into the expanded layout
		for (int i=0; i < events.length; i++) {

		    inboxStyle.addLine(events[i]);
		}
		
		inboxStyle.setSummaryText("+3 more");
		
		
		// Moves the expanded layout object into the notification object.
		builder.setStyle(inboxStyle);
		
		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		int id = 1;
		notificationManager.notify(id, builder.build());

	}
	
	// Customer Notification
	private void onCustomerNotificationClick(){
		
		// Customer Notification layout
		RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.notification_customer_layout);		
		remoteViews.setImageViewResource(R.id.notification_image, R.drawable.notification_image);
		remoteViews.setTextViewText(R.id.notification_title, "My custom notification title");
		
		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				this)
				.setSmallIcon(android.R.drawable.ic_dialog_alert)
				.setContent(remoteViews)					// setting the customer notification layout
				.setAutoCancel(true);	// After clicking notification, disappear the notification.

		// Must set the SpecialNotificationResultActivity in the xxxxManifest.xml
		// Set just one Task
		Intent notifyIntent = new Intent(this, SpecialNotificationResultActivity.class);
		
		// Sets the Activity to start in a new, empty task
		notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		
		// Creates the PendingIntent
		PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0,
				notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		
		// Puts the PendingIntent into the notification builder
		builder.setContentIntent(resultPendingIntent);		
		
		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		int id = 2;
		notificationManager.notify(id, builder.build());

	}
	
	
	private void onProgressNotificationClick(){
		final NotificationCompat.Builder builder = new NotificationCompat.Builder(
				this).setSmallIcon(android.R.drawable.ic_dialog_alert)
				.setContentTitle("Picture Download")
				.setContentText("Download in progress")
				.setAutoCancel(true);	// After clicking notification, disappear the notification.

		final NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		final int id = 3;
		
		// Start a lengthy operation in a background thread
		new Thread(new Runnable() {
			@Override
			public void run() {
				int incr;
				// Do the "lengthy" operation 20 times
				for (incr = 0; incr <= 100; incr += 10) {
					// Sets the progress indicator to a max value, the
					// current completion percentage, and "determinate"
					// state
					builder.setProgress(100, incr, false);
					// Displays the progress bar for the first time.
					notificationManager.notify(id, builder.build());
					// Sleeps the thread, simulating an operation
					// that takes time
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						Log.d("Mylog", "sleep failure");
					}
				}

				// When the loop is finished, updates the notification
				builder.setContentText("Download complete")
				// Removes the progress bar
						.setProgress(0, 0, false);
				notificationManager.notify(id, builder.build());
			}
		}
		// Starts the thread by calling the run() method in its Runnable
		).start();		


	}
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.sendNotificationDemo_sendSimpleNotificationButton:
			onSimpleNotificationClick();
			break;

		case R.id.sendNotificationDemo_sendStyleLayoutNotificationButton:
			onStyleLayoutNotificationClick();
			break;

		case R.id.sendNotificationDemo_sendCustomerNotificationButton:
			onCustomerNotificationClick();
			break;

		case R.id.sendNotificationDemo_sendProgressNotificationButton:
			onProgressNotificationClick();
			break;


		default:
			break;
		}
	}
}
