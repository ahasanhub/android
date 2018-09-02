package com.contextidea.samplemvp;

import android.app.Application;

import com.contextidea.samplemvp.data.DataManager;
import com.contextidea.samplemvp.data.SharedPrefHelper;

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
