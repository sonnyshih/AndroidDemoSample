package com.example.CustomerUIDemo.activity.GCMDemo.GCMFirstDemo;


import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

public class UnregisterGCMTask extends AsyncTask<Void, Void, Boolean> {

    private Context context;
    private UnregisterGCMTaskListener unregisterGCMTaskListener;

    public UnregisterGCMTask(Context context, UnregisterGCMTaskListener unregisterGCMTaskListener) {
        this.context = context;
        this.unregisterGCMTaskListener = unregisterGCMTaskListener;
    }

    @Override
    protected Boolean doInBackground(Void... params) {

        boolean isSuccess = false;

        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);

        try {
            gcm.unregister();
            isSuccess = true;
        } catch (IOException e) {
            e.printStackTrace();
            isSuccess = false;
        }

        return isSuccess;
    }

    @Override
    protected void onPostExecute(Boolean isSuccess) {
        super.onPostExecute(isSuccess);
        if (isSuccess) {
            unregisterGCMTaskListener.onUnregisterGCMTaskSucced();
        } else {
            unregisterGCMTaskListener.onUnregisterGCMTaskFailed();
        }
    }

    public interface UnregisterGCMTaskListener {
        public void onUnregisterGCMTaskSucced();
        public void onUnregisterGCMTaskFailed();
    }

}
