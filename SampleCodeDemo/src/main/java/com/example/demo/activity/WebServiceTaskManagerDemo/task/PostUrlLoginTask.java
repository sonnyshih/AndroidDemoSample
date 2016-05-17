package com.example.demo.activity.WebServiceTaskManagerDemo.task;


import com.example.demo.activity.VolleyDemo.CustomerGsonRequestDemo.entity.CustomerInfo.MessageEntity;
import com.example.demo.activity.VolleyDemo.CustomerGsonRequestDemo.entity.CustomerInfo.UILoginResultInfoEntity;
import com.example.demo.activity.VolleyDemo.CustomerGsonRequestDemo.entity.CustomerInfo.UIValidatorInfo;
import com.example.demo.activity.VolleyDemo.CustomerGsonRequestDemo.entity.UILoginInputInfoEntity;
import com.example.demo.activity.WebServiceTaskManagerDemo.type.ErrorType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class PostUrlLoginTask extends MessageWebServiceTask<UILoginResultInfoEntity>{

    private String userName;
    private String password;
    private loginWebServiceTaskListener listener;

    public PostUrlLoginTask(String userName, String password,loginWebServiceTaskListener listener) {
        this.userName = userName;
        this.password = password;
        this.listener = listener;
    }

    @Override
    public HttpMethodType getMethod() {
        return HttpMethodType.POST;
    }

    @Override
    public String generatePostBody() {
        Gson gson = new Gson();
        UILoginInputInfoEntity postEntity = new UILoginInputInfoEntity();
        postEntity.setLoginName(userName);
        postEntity.setPassword(password);

        return gson.toJson(postEntity);
    }

    @Override
    public String generateServiceUrl() {
        return "http://175.98.136.168:4000/ShoppingLogin.egg";
    }

    @Override
    public Type generateResultType() {
        return new TypeToken<MessageEntity<UILoginResultInfoEntity>>() {
        }.getType();
    }

    @Override
    public void onTaskSucceed(MessageEntityState state, UILoginResultInfoEntity body, String description) {

        switch (state) {
            case SUCCESS:
                listener.onLoginWebServiceTaskSucceed(body);
                break;
            case FAIL:
                listener.onLoginWebServiceTaskFailed(body.getValidator(),
                        description);
                break;
            case FAIL_BUT_NO_DESCRIPTION:
                listener.onLoginWebServiceTaskFailedButNoDescription(body
                        .getValidator());
                break;
            case SERVER_ERROR:
            case SUCCESS_BUT_BODY_NULL:
            default:
                listener.onLoginWebServiceTaskError(ErrorType.WEB_SERVER_ERROR);
                break;
        }
    }

    @Override
    public void onTaskFailed(ErrorType errorType) {
        listener.onLoginWebServiceTaskError(errorType);
    }

    public static interface loginWebServiceTaskListener {
        public void onLoginWebServiceTaskError(ErrorType errorType);

        public void onLoginWebServiceTaskSucceed(
                UILoginResultInfoEntity entity);

        public void onLoginWebServiceTaskFailed(
                UIValidatorInfo entity, String description);

        public void onLoginWebServiceTaskFailedButNoDescription(
                UIValidatorInfo entity);
    }
}
