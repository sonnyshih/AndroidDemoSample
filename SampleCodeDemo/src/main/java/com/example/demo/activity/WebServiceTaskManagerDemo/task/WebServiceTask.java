
package com.example.demo.activity.WebServiceTaskManagerDemo.task;


import com.example.demo.activity.WebServiceTaskManagerDemo.model.HeaderFactory;
import com.example.demo.activity.WebServiceTaskManagerDemo.type.ErrorType;
import com.example.demo.activity.WebServiceTaskManagerDemo.webservice.ClientInfo;
import com.example.demo.manage.ApplicationManager;

import java.lang.reflect.Type;
import java.util.Map;

public abstract class WebServiceTask <R> {

    protected ClientInfo createClientInfo() {
        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setDeviceType(ApplicationManager.getInstance().getDeviceType());
        clientInfo.setAppVersion(ApplicationManager.getInstance().getAppVersion());
        clientInfo.setAuthToken(ApplicationManager.getInstance().getAuthToken());
        return clientInfo;
    }


    public abstract HttpMethodType getMethod();

    public abstract String generateServiceUrl();

    public String generatePostBody() {

        return "";
    }

    public Map<String, String> generateHttpHeaders() {
        return HeaderFactory.create(createClientInfo());
    }

    public abstract Type generateResultType();

    public abstract void onTaskSucceed(R entity);

    public abstract void onTaskFailed(ErrorType errorType);

    public enum HttpMethodType {
        GET, POST
    }


}
