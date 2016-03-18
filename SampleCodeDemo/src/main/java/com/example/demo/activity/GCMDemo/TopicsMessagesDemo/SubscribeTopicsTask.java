
package com.example.demo.activity.GCMDemo.TopicsMessagesDemo;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.example.demo.R;
import com.example.demo.activity.GCMDemo.DownstreamMessagingDemo.DownstreamMessagingActivity;
import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

public class SubscribeTopicsTask extends AsyncTask<Void, Void, String> {
    public static final String[] TOPICS = {"global"};

    private Context context;
    private String senderID;
    private SubscribeTopicsListener subscribeTopicsListener;


    public SubscribeTopicsTask(Context context, SubscribeTopicsListener subscribeTopicsListener) {
        this.context = context;
        this.subscribeTopicsListener = subscribeTopicsListener;
        senderID = context.getString(R.string.gcm_senderId);
    }

    @Override
    protected String doInBackground(Void... params) {

        String token = "";
        InstanceID instanceID = InstanceID.getInstance(context);
        try {
            token = instanceID.getToken(senderID,
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            GcmPubSub pubSub = GcmPubSub.getInstance(context);
            for (String topic : TOPICS) {
                try {
                    pubSub.subscribe(token, "/topics/" + topic, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
            subscribeTopicsListener.subscribeTopicsFailed();
        }


        return token;
    }

    @Override
    protected void onPostExecute(String token) {
        super.onPostExecute(token);
        subscribeTopicsListener.subscribeTopicsSuccess();
        storeToken(token);
    }


    private void storeToken(String token) {

        final SharedPreferences prefs = context.getSharedPreferences(DownstreamMessagingActivity.SHARE_PREFERENCE_GCM,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(DownstreamMessagingActivity.PROPERTY_TOKEN, token);
        editor.commit();

    }

    public interface SubscribeTopicsListener {
        public void subscribeTopicsSuccess();
        public void subscribeTopicsFailed();
    }
}
