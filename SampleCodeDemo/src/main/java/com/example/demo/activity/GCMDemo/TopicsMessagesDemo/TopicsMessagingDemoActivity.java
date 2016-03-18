
package com.example.demo.activity.GCMDemo.TopicsMessagesDemo;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.activity.GCMDemo.DownstreamMessagingDemo.DownstreamMessagingActivity;
import com.example.demo.activity.GCMDemo.TopicsMessagesDemo.SubscribeTopicsTask.SubscribeTopicsListener;
import com.example.demo.activity.GCMDemo.TopicsMessagesDemo.UnsubscribeTopicsTask.UnsubscribeTopicsListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class TopicsMessagingDemoActivity extends Activity implements OnClickListener,
        SubscribeTopicsListener, UnsubscribeTopicsListener {


    public final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private String API_KEY;

    private Button subscribeButton;
    private Button unsubscribeButton;
    private Button pushMessageButton;
    private TextView sendGCMMessageTextView;
    private TextView responseTextView;

    public enum PlayServicesState {
        SUPPORT, NEED_PLAY_SERVICE, UNSUPPORT;
    }

    public enum GCMState {
        PLAY_SERVICES_NEED_PLAY_SERVICE, PLAY_SERVICES_UNSUPPORT, NEED_REGISTER, AVAILABLE;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topics_messaging_demo_activity);

        API_KEY = getString(R.string.gcm_api_key);

        subscribeButton = (Button) findViewById(R.id.topicsMessagingDemo_subscribeButton);
        subscribeButton.setOnClickListener(this);

        unsubscribeButton = (Button) findViewById(R.id.topicsMessagingDemo_unsubscribeButton);
        unsubscribeButton.setOnClickListener(this);

        pushMessageButton = (Button) findViewById(R.id.topicsMessagingDemo_pushMessageButton);
        pushMessageButton.setOnClickListener(this);

        sendGCMMessageTextView = (TextView) findViewById(R.id.topicsMessagingDemo_sendGCMMessageTextView);
        responseTextView = (TextView) findViewById(R.id.topicsMessagingDemo_responseTextView);

    }

    private String getToken() {

        final SharedPreferences prefs = getSharedPreferences(DownstreamMessagingActivity.SHARE_PREFERENCE_GCM,
                Context.MODE_PRIVATE);

        String token = prefs.getString(DownstreamMessagingActivity.PROPERTY_TOKEN, "");
        String regId = prefs.getString(DownstreamMessagingActivity.PROPERTY_REG_ID, "");

        if (token.isEmpty()) {
            return "";
        }

        int registeredVersion = prefs.getInt(DownstreamMessagingActivity.PROPERTY_APP_VERSION,
                Integer.MIN_VALUE);
        int currentVersion = getAppVersion();
        if (registeredVersion != currentVersion) {
            return "";
        }

        return token;

    }

    private void cleanToken() {

        final SharedPreferences prefs = getSharedPreferences(DownstreamMessagingActivity.SHARE_PREFERENCE_GCM,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(DownstreamMessagingActivity.PROPERTY_TOKEN, "");
        editor.commit();

    }


    private GCMState subscribe() {

        switch (checkPlayServices()) {

            case SUPPORT:
                String token = getToken();
                if (token.isEmpty()) {
                    new SubscribeTopicsTask(this, this).execute();
                    return GCMState.NEED_REGISTER;
                } else {
                    return GCMState.AVAILABLE;
                }

            case NEED_PLAY_SERVICE:
                return GCMState.PLAY_SERVICES_NEED_PLAY_SERVICE;

            default:
                return GCMState.PLAY_SERVICES_UNSUPPORT;
        }


    }

    private void unsubscribe() {
        String token = getToken();
        if (!token.isEmpty()) {
            new UnsubscribeTopicsTask(this, token, this).execute();
        }
    }

    private void pushMessage() {

        String TO = "/topics/global";

        final String sendMessage = "{" +
                "\"to\":\"" + TO + "\"," +
                "\"data\":{" +
                "   \"message\":{" +
                "       \"model\":\"AAA-001\"," +
                "       \"name\":\"Car\"," +
                "       \"color\":\"Red\"" +
                "       }" +
                "   }" +
                "}";


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    showSendMessage(sendMessage);
                    // Create connection to send GCM Message request.
                    URL url = new URL("https://android.googleapis.com/gcm/send");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Authorization", "key=" + API_KEY);
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);

                    // Send GCM message content.
                    OutputStream outputStream = conn.getOutputStream();
                    outputStream.write(sendMessage.getBytes());

                    // Read GCM response.
                    InputStream inputStream = conn.getInputStream();
                    showResponse(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    private void showSendMessage(final String sendMessage) {
        Log.d("Mylog", "sendMessage="+sendMessage);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                sendGCMMessageTextView.setText(sendMessage);
            }
        });

    }

    StringBuilder stringBuilder;

    private void showResponse(InputStream inputStream) {

        stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        String line;
        try {

            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Log.d("Mylog", "response="+stringBuilder.toString());
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseTextView.setText(stringBuilder);
            }
        });

    }

    public int getAppVersion() {
        try {
            PackageInfo packageInfo = this.getPackageManager()
                    .getPackageInfo(this.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            throw new RuntimeException("Could not get package name: " + e);
        }
    }

    public PlayServicesState checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        if (resultCode != ConnectionResult.SUCCESS) {

            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();

                return PlayServicesState.NEED_PLAY_SERVICE;

            } else {
                return PlayServicesState.UNSUPPORT;
            }

        }

        return PlayServicesState.SUPPORT;
    }


    @Override
    public void subscribeTopicsSuccess() {
        Toast.makeText(this, "Has subscribed topics",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void subscribeTopicsFailed() {
        cleanToken();
        Toast.makeText(this, "Fail to subscribed topics",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void unsubscribeTopicsSuccess() {
        cleanToken();
        Toast.makeText(this, "Has unsubscribed topics",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void unsubscribeTopicsFailed() {
        cleanToken();
        Toast.makeText(this, "Fail to subscribed topics",
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.topicsMessagingDemo_subscribeButton:
                subscribe();
                break;

            case R.id.topicsMessagingDemo_unsubscribeButton:
                unsubscribe();
                break;

            case R.id.topicsMessagingDemo_pushMessageButton:
                pushMessage();
                break;

            default:
                break;
        }
    }
}
