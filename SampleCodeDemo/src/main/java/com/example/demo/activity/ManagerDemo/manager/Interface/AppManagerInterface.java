
package com.example.demo.activity.ManagerDemo.manager.Interface;

import com.example.demo.activity.ManagerDemo.manager.AppManagerType;

public interface AppManagerInterface {

    void AppMangerCenterMediator(AppMangerCenterMediator mediator);

    AppManagerType getAppManagerType();

    void onReceive(String notifyType);

}
