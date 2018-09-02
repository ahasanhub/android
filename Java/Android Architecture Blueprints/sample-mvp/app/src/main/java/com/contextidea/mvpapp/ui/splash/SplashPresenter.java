package com.contextidea.mvpapp.ui.splash;

import android.support.annotation.NonNull;

import com.contextidea.mvpapp.data.DataManager;

public class SplashPresenter implements SplashContract.Presenter {
    private final DataManager mDataManager;
    private final SplashContract.View mSplashView;
    public SplashPresenter(DataManager dataManager, @NonNull SplashContract.View splashView) {
        mDataManager=dataManager;
        mSplashView=splashView;
        //Set presenter inside view
        mSplashView.setPresenter(this);
    }

    @Override
    public void decideNextActivity() {
        if (mDataManager.getLoggedInMode()) {
            mSplashView.openMainActivity();
        } else {
            mSplashView.openLoginActivity();
        }
    }

    /**
     * This is presenter entry point. Instruction come from view onResume event
     */
    @Override
    public void start() {
        decideNextActivity();
    }
}
