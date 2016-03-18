package com.example.demo.activity.GCMDemo.DownstreamMessagingDemo;

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
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.R;
import com.example.demo.activity.GCMDemo.DownstreamMessagingDemo.RegisterGCMTask.RegisterGCMTaskListener;
import com.example.demo.activity.GCMDemo.DownstreamMessagingDemo.UnregisterGCMTask.UnregisterGCMTaskListener;
import com.example.demo.util.StringUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownstreamMessagingActivity extends Activity implements OnClickListener,
        RegisterGCMTaskListener,
        UnregisterGCMTaskListener,
        RadioGroup.OnCheckedChangeListener {

    public static final String SHARE_PREFERENCE_GCM = "SHARE_PREFERNCE_GCM";

    public static final String PROPERTY_REG_ID = "registration_id";
    public static final String PROPERTY_APP_VERSION = "appVersion";
    public static final String PROPERTY_TOKEN = "token";

    public final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    private String API_KEY;
    private String SEND_ID;

    private String sendMessage;
    private Button registerButton;
    private Button unregisterButton;
    private Button pushMessageButton;
    private TextView responseTextView;
    private TextView sendGCMMessageTextView;

    private RadioGroup messageTypeRadioGroup;


    public enum PlayServicesState {
        SUPPROT, NEED_PLAY_SERVICE, UNSUPPORT;
    }

    public enum GCMState {
        PLAY_SERVICES_NEED_PLAY_SERVICE, PLAY_SERVICES_UNSUPPORT, NEED_REGISTER, AVAILABLE;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.downsteam_messaging_activity);

        API_KEY = getString(R.string.gcm_api_key);
        SEND_ID = getString(R.string.gcm_senderId);

        registerButton = (Button) findViewById(R.id.downstreamMessaging_registerButton);
        registerButton.setOnClickListener(this);

        unregisterButton = (Button) findViewById(R.id.downstreamMessaging_unregisterButton);
        unregisterButton.setOnClickListener(this);

        pushMessageButton = (Button) findViewById(R.id.downstreamMessaging_pushMessageButton);
        pushMessageButton.setOnClickListener(this);

        responseTextView = (TextView) findViewById(R.id.downstreamMessaging_responseTextView);

        sendGCMMessageTextView = (TextView) findViewById(R.id.downstreamMessaging_sendGCMMessageTextView);

        messageTypeRadioGroup = (RadioGroup) findViewById(R.id.downstreamMessaging_messageTypeRadioGroup);
        messageTypeRadioGroup.setOnCheckedChangeListener(this);
    }

    private GCMState register() {

        switch (checkPlayServices()) {

            case SUPPROT:
                String regId = getRegistrationId();

                if (regId.isEmpty()) {
                    registerInBackground();
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

    private void unregister() {
        String regId = getRegistrationId();

        if (!StringUtil.isEmpty(regId)) {
            new UnregisterGCMTask(this, this).execute();
        } else {
            Toast.makeText(this, "Register ID is empty",
                    Toast.LENGTH_LONG).show();
        }
    }


    private void registerInBackground() {
        new RegisterGCMTask(this, this, SEND_ID).execute();
    }

    public String getRegistrationId() {
        final SharedPreferences prefs = getGCMPreferences();
        String registrationId = prefs.getString(PROPERTY_REG_ID, "");
        if (registrationId.isEmpty()) {
            return "";
        }

        int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION,
                Integer.MIN_VALUE);
        int currentVersion = getAppVersion();
        if (registeredVersion != currentVersion) {
            return "";
        }
        return registrationId;
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

    private SharedPreferences getGCMPreferences() {
        return getSharedPreferences(SHARE_PREFERENCE_GCM, Context.MODE_PRIVATE);
    }

    private void storeRegistrationId(String regId) {
        final SharedPreferences prefs = getGCMPreferences();
        int appVersion = getAppVersion();
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(PROPERTY_REG_ID, regId);
        editor.putInt(PROPERTY_APP_VERSION, appVersion);
        editor.commit();
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

        return PlayServicesState.SUPPROT;
    }


    private void pushMessage(final String sendMessage) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    showSendMessage(sendMessage);
                    Log.d("Mylog", "gcmMessage=" + sendMessage);
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

        Log.d("Mylog", "responseMessage=" + stringBuilder);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseTextView.setText(stringBuilder);
            }
        });

    }

    @Override
    public void onRegisterGCMTaskSucced(String regId) {

        Toast.makeText(this, "Register GCM successfully!",
                Toast.LENGTH_LONG).show();

        storeRegistrationId(regId);
    }

    @Override
    public void onRegisterGCMTaskFailed() {
        Toast.makeText(this, "Register GCM fail!",
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onUnregisterGCMTaskSucced() {
        Toast.makeText(this, "Unregister GCM successfully!",
                Toast.LENGTH_LONG).show();

        storeRegistrationId("");
    }

    @Override
    public void onUnregisterGCMTaskFailed() {
        Toast.makeText(this, "Unregister GCM fail!",
                Toast.LENGTH_LONG).show();
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        String regId = getRegistrationId();

        switch (checkedId) {
            case R.id.downstreamMessaging_singleMessageRadioButton:
                // Message JSON Type 1
                sendMessage = "{" +
                        "\"to\":\"" + regId + "\"," +
                        "\"data\":{" +
                        "   \"message\":{" +
                        "       \"model\":\"AAA-001\"," +
                        "       \"name\":\"Car\"," +
                        "       \"color\":\"Red\"" +
                        "       }" +
                        "   }" +
                        "}";

                break;

            case R.id.downstreamMessaging_multiUserRadioButton:
                // Message JSON Type 2
                sendMessage = "{" +
                        "\"registration_ids\":[\"" + regId + "\"]," +
                        "\"data\":{" +
                        "   \"message\":{" +
                        "       \"model\":\"AAA-001\"," +
                        "       \"name\":\"Car\"," +
                        "       \"color\":\"Red\"" +
                        "       }" +
                        "   }" +
                        "}";

                break;

            default:
                break;
        }
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.downstreamMessaging_registerButton:
                register();
                break;

            case R.id.downstreamMessaging_unregisterButton:
                unregister();
                break;

            case R.id.downstreamMessaging_pushMessageButton:
                if (messageTypeRadioGroup.getCheckedRadioButtonId() != -1) {
                    pushMessage(sendMessage);
                } else {
                    Toast.makeText(this, "Choose one message type!",
                            Toast.LENGTH_LONG).show();
                }

                break;

            default:
                break;
        }

    }

}
