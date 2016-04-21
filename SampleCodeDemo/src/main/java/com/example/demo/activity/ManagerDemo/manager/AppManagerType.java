package com.example.demo.activity.ManagerDemo.manager;


public enum AppManagerType {
    Login, Setting;

    public String getActionName() {
        return AppManagerType.class.getName() + this;
    }
}
