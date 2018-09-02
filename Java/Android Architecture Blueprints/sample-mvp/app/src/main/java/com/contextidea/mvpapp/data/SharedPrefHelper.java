package com.contextidea.mvpapp.data;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by User on 12/16/2017.
 */

public class SharedPrefHelper {
    public static final String MY_PREFS = "MY_PREFS";
    public static final String EMAIL = "EMAIL";
    SharedPreferences mSharedPreferences;
    public SharedPrefHelper(Context context){
        mSharedPreferences=context.getSharedPreferences(MY_PREFS,MODE_PRIVATE);
    }
    public void clear() {
        mSharedPreferences.edit().clear().apply();
    }

    public void putEmail(String email) {
        mSharedPreferences.edit().putString(EMAIL, email).apply();
    }

    public String getEmail() {
        return mSharedPreferences.getString(EMAIL, null);
    }

    public boolean getLoggedInMode() {
        return mSharedPreferences.getBoolean("IS_LOGGED_IN", false);
    }

    public void setLoggedInMode(boolean loggedIn) {
        mSharedPreferences.edit().putBoolean("IS_LOGGED_IN", loggedIn).apply();
    }
}
