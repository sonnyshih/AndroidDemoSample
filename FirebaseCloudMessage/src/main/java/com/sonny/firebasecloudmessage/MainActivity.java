package com.sonny.firebasecloudmessage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    private String SERVER_API_KEY = "AAAAAw750N0:APA91bG7_61z5X0iRYiEUK6McMX4pOWvXo1aVW01QygVgK5hVZ7naBnc_RDzYUhbIRxJ2qvH37BinHaCXEWP7pfbtXKV81nZ8ohv4avQEqTe3JyE9JD0Gw-8cutHG9PEYlwKLWiFkcuE";
    private String token = "";

    String nexus9Token = "dtIkIIvLe2I:APA91bGDKJoirxIb_-kE9l7mNLutW9vqHnpreOsO4Re4EJEX1RdUoa5Yr__2IejJ6UaKYOnCmR5pXvduToNOVOhV-t_EubRbyWYY-xdQCj1MOT0SiOpB8MjuMj21vDPYeo3w8LVS1DW2";
    String nexus4Token = "cL3QYDgdtgM:APA91bEZdEp-65xRIJ1sM6E8_TFTPsuTE7e8XLwIaR9HLeBtP5xqmSHrIrkKG_-xk9UaR-QixJwU3FUIGJmS2jtLEOx0Aj2-TRL9fI-JzHT9yMaJedTUhRb--wP_lpJH853yLnOzPoHa";

    private TextView sendMessageTextView;
    private TextView responseMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sendMessageTextView = (TextView) findViewById(R.id.main_sendMessageTextView);
        responseMessageTextView = (TextView) findViewById(R.id.main_responseMessageTextView);

        Button getTokenButton = (Button) findViewById(R.id.main_getTokenButton);
        getTokenButton.setOnClickListener(this);

        Button pushSingleDeviceNotificationButton = (Button) findViewById(R.id.main_pushSingleDeviceNotificationButton);
        pushSingleDeviceNotificationButton.setOnClickListener(this);

        Button pushMultiDeviceNotificationButton = (Button) findViewById(R.id.main_pushMultiDeviceNotificationButton);
        pushMultiDeviceNotificationButton.setOnClickListener(this);

        Button pushSingleDeviceDataButton = (Button) findViewById(R.id.main_pushSingleDeviceDataButton);
        pushSingleDeviceDataButton.setOnClickListener(this);

        Button pushMultiDeviceDataButton = (Button) findViewById(R.id.main_pushMultiDeviceDataButton);
        pushMultiDeviceDataButton.setOnClickListener(this);

    }

    private void pushMessageToDevice(final String sendMessage) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    showSendMessage(sendMessage);

                    // Create connection to send GCM Message request.
                    URL url = new URL("https://fcm.googleapis.com/fcm/send");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestProperty("Authorization", "key=" + SERVER_API_KEY);
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestMethod("POST");
                    conn.setDoOutput(true);

                    // Send GCM message content.
                    OutputStream outputStream = conn.getOutputStream();
                    outputStream.write(sendMessage.getBytes());

                    int status = conn.getResponseCode();
                    switch (status) {
                        case HttpURLConnection.HTTP_OK:
                            // Read GCM response.
                            InputStream inputStream = conn.getInputStream();
                            showResponse(inputStream);
                            break;

                        default:
                            break;
                    }


                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }

    private void pushSingleDeviceNotification() {
        String sendMessage = getSingleDeviceNotification(token);
        pushMessageToDevice(sendMessage);
    }

    private String getSingleDeviceNotification(String token) {
        String sendMessage = "{" +
                "    \"to\" : \"" + token + "\"," +
                "    \"notification\" : {" +
                "      \"body\" : \"great match!\"," +
                "      \"title\" : \"Portugal vs. Denmark\"," +
                "      \"icon\" : \"myicon\"" +
                "    }" +
                "  }";

        return sendMessage;
    }

    private void pushMultiDeviceNotification() {
        String sendMessage = getMultiDeviceNotification();
        pushMessageToDevice(sendMessage);
    }

    private String getMultiDeviceNotification() {

        String sendMessage = "{" +
                "    \"registration_ids\" : [\"" + nexus4Token + "\",\"" + nexus9Token + "\"]," +
                "    \"notification\" : {" +
                "      \"body\" : \"great match!\"," +
                "      \"title\" : \"Portugal vs. Denmark\"," +
                "      \"icon\" : \"myicon\"" +
                "    }" +
                "  }";

        return sendMessage;

    }

    private void pushSingleDeviceData() {
        String sendMessage = getSingleDeviceData(token);
        pushMessageToDevice(sendMessage);
    }

    private String getSingleDeviceData(String token) {

        String sendMessage = "{" +
                "\"to\":\"" + token + "\"," +
                "\"data\":{" +
                "   \"message\":{" +
                "       \"model\":\"AAA-001\"," +
                "       \"name\":\"Car\"," +
                "       \"color\":\"Red\"" +
                "       }" +
                "   }" +
                "}";

        return sendMessage;
    }

    private void pushMultiDeviceData() {
        String sendMessage = getMultiDeviceData();
        pushMessageToDevice(sendMessage);
    }


    private String getMultiDeviceData() {

        String sendMessage = "{" +
                "\"registration_ids\" : [\"" + nexus4Token + "\",\"" + nexus9Token + "\"]," +
                "\"data\":{" +
                "   \"message\":{" +
                "       \"model\":\"AAA-001\"," +
                "       \"name\":\"Car\"," +
                "       \"color\":\"Red\"" +
                "       }" +
                "   }" +
                "}";

        return sendMessage;
    }

    private void showResponse(InputStream inputStream) {

        StringBuilder stringBuilder = new StringBuilder();
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

        showResponseMessage(stringBuilder.toString());
    }

    private void showSendMessage(final String message) {
        Log.d("Mylog", "sendMessage=" + message);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                sendMessageTextView.setText(message);
            }
        });

    }

    private void showResponseMessage(final String message) {
        Log.d("Mylog", "responseMessage=" + message);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                responseMessageTextView.setText(message);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.main_getTokenButton:
                token = FirebaseInstanceId.getInstance().getToken();
                String msg = getString(R.string.msg_token_fmt, token);
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                Log.d("Mylog", "token = " + token);
                break;

            case R.id.main_pushSingleDeviceNotificationButton:
                pushSingleDeviceNotification();
                break;

            case R.id.main_pushMultiDeviceNotificationButton:
                pushMultiDeviceNotification();
                break;

            case R.id.main_pushSingleDeviceDataButton:
                pushSingleDeviceData();
                break;

            case R.id.main_pushMultiDeviceDataButton:
                pushMultiDeviceData();
                break;

            default:
                break;
        }

    }
}
