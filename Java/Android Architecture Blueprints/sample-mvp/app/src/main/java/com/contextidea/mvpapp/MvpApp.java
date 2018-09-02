package com.contextidea.mvpapp;

import android.app.Application;

import com.contextidea.mvpapp.data.DataManager;
import com.contextidea.mvpapp.data.SharedPrefHelper;


/**
 * Created by User on 1/9/2018.
 */

public class MvpApp extends Application {
    DataManager dataManager;
    @Override
    public void onCreate(){
        super.onCreate();

        SharedPrefHelper sharedPrefsHelper = new SharedPrefHelper(getApplicationContext());
        dataManager = new DataManager(sharedPrefsHelper);
    }
    public DataManager getDataManager() {
        return dataManager;
    }
}
