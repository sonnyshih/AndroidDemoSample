
package com.example.demo.activity.ManagerDemo.manager;

import android.content.Context;

import com.example.demo.activity.ManagerDemo.manager.Interface.AppManagerInterface;
import com.example.demo.activity.ManagerDemo.manager.Interface.AppMangerCenterMediator;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AppManagerCenter implements AppMangerCenterMediator {

    private static AppManagerCenter managerCenter = null;

    private Map<AppManagerType, AppManagerInterface> appManagerMap = new HashMap<>();

    private Context context;

    private AppManagerCenter() {
    }

    public static AppManagerCenter getInstance() {
        if (managerCenter == null) {
            managerCenter = new AppManagerCenter();
        }

        return managerCenter;
    }

    @Override
    public void onNotify(AppManagerType managerType, String notifyType) {
        Collection<AppManagerInterface> appManagerCollection = appManagerMap.values();
        for (AppManagerInterface appManager : appManagerCollection) {
            if (appManager.getAppManagerType() != managerType) {
                appManager.onReceive(notifyType);
            }
        }
    }

    @Override
    public Context getContext() {
        return context;
    }

    public void init(Context context) {
        this.context = context;
    }

    public void register(AppManagerType type, AppManagerInterface appManager) {
        appManagerMap.put(type, appManager);
        appManager.AppMangerCenterMediator(this);
    }

    public AppManagerInterface getAppManager(AppManagerType type) {
        return appManagerMap.get(type);
    }

    public static LoginAppManager getLoginAppManager() {
        return (LoginAppManager) getInstance().getAppManager(AppManagerType.Login);
    }


    public static SettingManager getSettingManager() {
        return (SettingManager) getInstance().getAppManager(AppManagerType.Setting);
    }
}
