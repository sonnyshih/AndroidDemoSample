package com.example.demo.activity.ManagerDemo.manager.Interface;


import android.content.Context;

import com.example.demo.activity.ManagerDemo.manager.AppManagerType;

public interface AppMangerCenterMediator {

    void onNotify(AppManagerType managerType, String notifyType);

    Context getContext();


}
