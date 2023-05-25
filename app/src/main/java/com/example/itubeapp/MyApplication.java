package com.example.itubeapp;

import android.app.Application;

public class MyApplication extends Application {
    public static User user;
    @Override
    public void onCreate() {
        super.onCreate();
        DBManager dbManager =  new DBManager(this);
        dbManager.open();
    }
}
