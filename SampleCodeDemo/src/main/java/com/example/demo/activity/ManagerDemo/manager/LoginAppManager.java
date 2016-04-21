
package com.example.demo.activity.ManagerDemo.manager;


import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.example.demo.activity.ManagerDemo.manager.Interface.AppManagerInterface;
import com.example.demo.activity.ManagerDemo.manager.Interface.AppMangerCenterMediator;

public class LoginAppManager implements AppManagerInterface {
    private AppMangerCenterMediator appCenter;
    private Context context;

    public static final String NOTIFY_LOGIN = "NOTIFY_LOGIN";
    public static final String NOTIFY_LOGOUT = "NOTIFY_LOGOUT";

    @Override
    public void AppMangerCenterMediator(AppMangerCenterMediator mediator) {
        appCenter = mediator;

    }

    @Override
    public AppManagerType getAppManagerType() {
        return AppManagerType.Login;

    }

    @Override
    public void onReceive(String notifyType) {

    }

    public LoginAppManager(Context context) {
        this.context = context;
    }

    public void login(String username, String password){
        sendNotify(NOTIFY_LOGIN);

    }

    public void logout() {
            sendNotify(NOTIFY_LOGOUT);
    }

    private void sendNotify(String notify) {
        if (appCenter != null) {
            appCenter.onNotify(getAppManagerType(), notify);
        }

        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(notify));
    }
}
