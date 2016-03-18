package com.example.demo.activity.GCMDemo.TopicsMessagesDemo;


import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.gcm.GcmPubSub;

import java.io.IOException;

public class UnsubscribeTopicsTask extends AsyncTask<Void, Void, Void> {

    private String[] topics = SubscribeTopicsTask.TOPICS;

    private Context context;
    private String token;
    private UnsubscribeTopicsListener unsubscribeTopicsListener;

    public UnsubscribeTopicsTask(Context context, String token,
                                 UnsubscribeTopicsListener unsubscribeTopicsListener) {

        this.context = context;
        this.token = token;
        this.unsubscribeTopicsListener = unsubscribeTopicsListener;

    }

    @Override
    protected Void doInBackground(Void... params) {

        GcmPubSub pubSub = GcmPubSub.getInstance(context);
        for (String topic : topics) {
            try {
                pubSub.unsubscribe(token, "/topics/" + topic);
            } catch (IOException e) {
                e.printStackTrace();
                unsubscribeTopicsListener.unsubscribeTopicsFailed();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        unsubscribeTopicsListener.unsubscribeTopicsSuccess();
    }

    public interface UnsubscribeTopicsListener {
        public void unsubscribeTopicsSuccess();
        public void unsubscribeTopicsFailed();
    }
}
