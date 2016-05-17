package com.example.demo.activity.WebServiceTaskManagerDemo.task;


import com.example.demo.activity.VolleyDemo.CustomerGsonRequestDemo.entity.CustomerInfo.MessageEntity;
import com.example.demo.util.StringUtil;

public abstract class MessageWebServiceTask <R> extends WebServiceTask<MessageEntity<R>>{

    public enum MessageEntityState {
        SUCCESS, SUCCESS_WITH_DESCRIPTION, FAIL, FAIL_BUT_NO_DESCRIPTION, SERVER_ERROR, SUCCESS_WITH_NULL_DESCRIPTION, SUCCESS_BUT_BODY_NULL
        ,AMEX_REQUEST_PHONE, AMEX_REQUEST_EMAIL
    }

    private final static String MESSAGE_SUCCESS = "000";
    private final static String MESSAGE_FAIL = "111";
    private final static String MESSAGE_FAIL_PARAMETERS_ERROR = "222";
    private final static String MESSAGE_SUCCESS_WITH_DESCRIPTION = "999";

    private MessageEntityState checkMessageEntity(MessageEntity<?> entity) {
        if (entity == null) {
            // Fix Null Pointer Exception. - Bale.Y.Lin 2013/11/14
            return MessageEntityState.SERVER_ERROR;
        } else if (entity.getCode() == null) {

            return MessageEntityState.SERVER_ERROR;

        } else if (entity.getCode().equals(MESSAGE_SUCCESS)) {
            if (entity.getBody() == null) {
                return MessageEntityState.SUCCESS_BUT_BODY_NULL;
            } else {
                return MessageEntityState.SUCCESS;
            }

        } else if (entity.getCode().equals(MESSAGE_FAIL)
                || entity.getCode().equals(MESSAGE_FAIL_PARAMETERS_ERROR)) {

            if (StringUtil.isEmpty(entity.getDescription())) {
                return MessageEntityState.FAIL_BUT_NO_DESCRIPTION;
            } else {
                return MessageEntityState.FAIL;
            }

        } else if (entity.getCode().equals(MESSAGE_SUCCESS_WITH_DESCRIPTION)) {

            if (entity.getBody() == null) {
                return MessageEntityState.SUCCESS_BUT_BODY_NULL;
            } else {
                if (!StringUtil.isEmpty(entity.getDescription())) {
                    return MessageEntityState.SUCCESS_WITH_DESCRIPTION;
                } else {
                    return MessageEntityState.SUCCESS_WITH_NULL_DESCRIPTION;
                }
            }

        } else if (entity.getCode().equals("21001")) {
            return MessageEntityState.AMEX_REQUEST_EMAIL;
        } else if (entity.getCode().equals("21002")) {
            return MessageEntityState.AMEX_REQUEST_PHONE;
        }
        return MessageEntityState.SERVER_ERROR;
    }

    @Override
    public void onTaskSucceed(MessageEntity<R> entity) {
        onTaskSucceed(checkMessageEntity(entity), entity.getBody(),
                entity.getDescription());

    }

    public abstract void onTaskSucceed(MessageEntityState state, R body,
                                       String description);

}
