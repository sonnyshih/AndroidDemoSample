
package com.example.demo.activity.GCMDemo.DownstreamMessagingDemo;

import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

public class RegisterGCMTask extends AsyncTask<Void, Void, String> {

    private Context context;
    private String senderID;
    private RegisterGCMTaskListener registerGCMTaskListener;

    public RegisterGCMTask(Context context, RegisterGCMTaskListener registerGCMTaskListener,
                           String senderID){

        this.context = context;
        this.registerGCMTaskListener = registerGCMTaskListener;
        this.senderID = senderID;
    }

    @Override
    protected String doInBackground(Void... params) {
        String regId = "";

        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
        try {
            regId = gcm.register(senderID);
        } catch (IOException e) {
            registerGCMTaskListener.onRegisterGCMTaskFailed();
            e.printStackTrace();
        }

        return regId;
    }

    @Override
    protected void onPostExecute(String regId) {
        super.onPostExecute(regId);
        registerGCMTaskListener.onRegisterGCMTaskSucced(regId);
    }

    public interface RegisterGCMTaskListener {
        public void onRegisterGCMTaskSucced(String regId);
        public void onRegisterGCMTaskFailed();
    }
}
