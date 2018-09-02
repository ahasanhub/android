package com.contextidea.samplemvp.data;

/**
 * Created by User on 12/16/2017.
 */

public class DataManager {
    SharedPrefHelper mSharedPrefHelper;
    public DataManager(SharedPrefHelper sharedPrefHelper){
        mSharedPrefHelper=sharedPrefHelper;
    }
    public void clear(){
        mSharedPrefHelper.clear();
    }
    public void saveEmailId(String email) {
        mSharedPrefHelper.putEmail(email);
    }

    public String getEmailId() {
        return mSharedPrefHelper.getEmail();
    }

    public void setLoggedIn() {
        mSharedPrefHelper.setLoggedInMode(true);
    }

    public Boolean getLoggedInMode() {
        return mSharedPrefHelper.getLoggedInMode();
    }
}
