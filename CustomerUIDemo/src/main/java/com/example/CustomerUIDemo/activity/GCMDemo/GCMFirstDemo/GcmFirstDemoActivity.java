package com.example.CustomerUIDemo.activity.GCMDemo.GCMFirstDemo;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.CustomerUIDemo.R;
import com.example.CustomerUIDemo.activity.GCMDemo.GCMFirstDemo.RegisterGCMTask.RegisterGCMTaskListener;
import com.example.CustomerUIDemo.activity.GCMDemo.GCMFirstDemo.UnregisterGCMTask.UnregisterGCMTaskListener;
import com.example.CustomerUIDemo.util.StringUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GcmFirstDemoActivity extends Activity implements OnClickListener,
        RegisterGCMTaskListener,
        UnregisterGCMTaskListener {


    private static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";
    public final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
    public static final String API_KEY = "AIzaSyBbzlhXB5HQcz28I9xwqLMB04uTAd8qYtE";
    private final static String senderId = "643534156510";

    private Button registerButton;
    private Button unregisterButton;
    private Button pushMessageButton;
    private TextView responseTextView;

    public enum PlayServicesState {
        SUPPROT, NEED_PLAY_SERVICE, UNSUPPORT;
    }

    public enum GCMState {
        PLAY_SERVICES_NEED_PLAY_SERVICE, PLAY_SERVICES_UNSUPPORT, NEED_REGISTER, AVAILABLE;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gcm_first_demo_activity);

        registerButton = (Button) findViewById(R.id.gcmFirstDemo_registerButton);
        registerButton.setOnClickListener(this);

        unregisterButton = (Button) findViewById(R.id.gcmFirstDemo_unregisterButton);
        unregisterButton.setOnClickListener(this);

        pushMessageButton = (Button) findViewById(R.id.gcmFirstDemo_pushMessageButton);
        pushMessageButton.setOnClickListener(this);

        responseTextView = (TextView) findViewById(R.id.gcmFirstDemo_responseTextView);
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
        new RegisterGCMTask(this, this, senderId).execute();
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
        return getSharedPreferences(this.getClass().getSimpleName(), Context.MODE_PRIVATE);
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


    private void pushMessage() {


        new Thread(new Runnable() {
            @Override
            public void run() {
                String regId = getRegistrationId();
                try {

                    String gcmMessage = "{" +
                            "\"registration_ids\":[\"" + regId + "\"]," +
                            "\"data\":{" +
                            "   \"message\":{" +
                            "       \"model\":\"AAA-001\"," +
                            "       \"name\":\"Car\"," +
                            "       \"color\":\"Red\""+
                            "       }" +
                            "   }" +
                            "}";

                    // Create connection to send GCM Message request.
                    URL url = new URL("https://android.googleapis.com/gcm/send");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Authorization", "key=" + API_KEY);
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);

                    // Send GCM message content.
                    OutputStream outputStream = conn.getOutputStream();
                    outputStream.write(gcmMessage.getBytes());

                    // Read GCM response.
                    InputStream inputStream = conn.getInputStream();
                    showResponse(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    StringBuilder stringBuilder = new StringBuilder();
    private void showResponse(InputStream inputStream){
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
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.gcmFirstDemo_registerButton:
                register();
                break;

            case R.id.gcmFirstDemo_unregisterButton:
                unregister();
                break;

            case R.id.gcmFirstDemo_pushMessageButton:
                pushMessage();
                break;

            default:
                break;
        }

    }

}
