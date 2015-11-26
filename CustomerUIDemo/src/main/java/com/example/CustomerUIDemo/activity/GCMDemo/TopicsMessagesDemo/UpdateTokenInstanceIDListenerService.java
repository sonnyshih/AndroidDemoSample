
package com.example.CustomerUIDemo.activity.GCMDemo.TopicsMessagesDemo;


import com.google.android.gms.iid.InstanceIDListenerService;
import com.example.CustomerUIDemo.activity.GCMDemo.TopicsMessagesDemo.SubscribeTopicsTask.SubscribeTopicsListener;


public class UpdateTokenInstanceIDListenerService extends InstanceIDListenerService implements
        SubscribeTopicsListener{

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. This call is initiated by the
     * InstanceID provider.
     */
    @Override
    public void onTokenRefresh() {
        // Fetch updated Instance ID token and notify our app's server of any changes (if applicable).
        new SubscribeTopicsTask(this,this).execute();
    }

    @Override
    public void subscribeTopicsSuccess() {

    }

    @Override
    public void subscribeTopicsFailed() {

    }
}
