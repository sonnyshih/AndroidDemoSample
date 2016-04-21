
package com.example.demo.activity.ManagerDemo.manager;


import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;

import com.example.demo.activity.ManagerDemo.manager.Interface.AppManagerInterface;
import com.example.demo.activity.ManagerDemo.manager.Interface.AppMangerCenterMediator;

public class SettingManager implements AppManagerInterface {
    private AppMangerCenterMediator appMangerCenterMediator;
    private static SettingManager instance;

    public static final String NOTIFY_COUNTRY_CHANGE = "NOTIFY_COUNTRY_CHANGE";

    private Context context;

    public static SettingManager getInstance() {
        if (instance == null) {
            instance = new SettingManager();
        }

        return instance;
    }

    private SettingManager() {
        this.context = AppManagerCenter.getInstance().getContext();

    }

    @Override
    public void AppMangerCenterMediator(AppMangerCenterMediator mediator) {
        appMangerCenterMediator = mediator;
    }

    @Override
    public AppManagerType getAppManagerType() {
        return null;
    }

    @Override
    public void onReceive(String notifyType) {

    }

    private void sendNotify(String notify) {
        if (appMangerCenterMediator != null) {
            appMangerCenterMediator.onNotify(getAppManagerType(), notify);
        }

        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(notify));
    }

    public void setCountry(int country) {

        sendNotify(NOTIFY_COUNTRY_CHANGE);
    }
}
