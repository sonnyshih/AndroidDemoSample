package com.example;

/*
*  1. Setting build.gradle
*  2. In the "Terminal" sheet, enter the Command
*     gradlew run -P message="sasdasdf" -P to="hello"
*
*  3.
* */

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GcmSender {

    public static final String API_KEY = "AIzaSyBbzlhXB5HQcz28I9xwqLMB04uTAd8qYtE";
    public static final String TO = "/topics/global";

    public static void main(String[] args) {
        System.out.println("Hello World");
//
//        if (args[0] != null) {
//            System.out.println("args 0 (msg) =" + args[0].trim());
//        }
//
//        if (args[1] != null) {
//            System.out.println("args 1 (to) =" + args[1].trim());
//        }


        sendGcmMessage(args);
    }

    public static void sendGcmMessage(String[] args){

        if (args.length < 1 || args.length > 2 || args[0] == null) {
            System.err.println("usage: ./gradlew run -Pmsg=\"MESSAGE\" [-Pto=\"DEVICE_TOKEN\"]");
            System.err.println("");
            System.err.println("Specify a test message to broadcast via GCM. If a device's GCM registration token is\n" +
                    "specified, the message will only be sent to that device. Otherwise, the message \n" +
                    "will be sent to all devices subscribed to the \"global\" topic.");
            System.err.println("");
            System.err.println("Example (Broadcast):\n" +
                    "On Windows:   .\\gradlew.bat run -Pmsg=\"<Your_Message>\"\n" +
                    "On Linux/Mac: ./gradlew run -Pmsg=\"<Your_Message>\"");
            System.err.println("");
            System.err.println("Example (Unicast):\n" +
                    "On Windows:   .\\gradlew.bat run -Pmsg=\"<Your_Message>\" -Pto=\"<Your_Token>\"\n" +
                    "On Linux/Mac: ./gradlew run -Pmsg=\"<Your_Message>\" -Pto=\"<Your_Token>\"");
            System.exit(1);
        }

        try {
            // Prepare JSON containing the GCM message content. What to send and where to send.
            JSONObject jGcmData = new JSONObject();
            JSONObject jData = new JSONObject();
            jData.put("message", args[0].trim());

            // Where to send GCM message.
            if (args.length > 1 && args[1] != null) {
                jGcmData.put("to", args[1].trim());
            } else {
                jGcmData.put("to", TO);
            }

            // What to send in GCM message.
            jGcmData.put("data", jData);

            System.out.print("gcmdate="+jGcmData.toString());

            // Create connection to send GCM Message request.
            URL url = new URL("https://android.googleapis.com/gcm/send");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "key=" + API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Send GCM message content.
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(jGcmData.toString().getBytes());

            // Read GCM response.
            InputStream inputStream = conn.getInputStream();
            String resp = IOUtils.toString(inputStream);
            System.out.println(resp);
            System.out.println("Check your device/emulator for notification or logcat for " +
                    "confirmation of the receipt of the GCM message.");
        } catch (IOException e) {
            System.out.println("Unable to send GCM message.");
            System.out.println("Please ensure that API_KEY has been replaced by the server " +
                    "API key, and that the device's registration token is correct (if specified).");
            e.printStackTrace();
        }
    }

}
