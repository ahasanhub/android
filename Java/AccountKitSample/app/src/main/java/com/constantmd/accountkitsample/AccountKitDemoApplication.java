package com.constantmd.accountkitsample;

import android.app.Application;

import com.facebook.accountkit.AccountKit;

/**
 * Created by Shobuz on 10/29/2017.
 */

public class AccountKitDemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //Initialize Facebook Account Kit
        AccountKit.initialize(getApplicationContext());
    }
}
