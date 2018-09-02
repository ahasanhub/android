package com.contextidea.samplemvp.ui.splash;

import com.contextidea.samplemvp.data.DataManager;
import com.contextidea.samplemvp.ui.base.BasePresenter;

/**
 * Created by User on 1/9/2018.
 */

public class SplashPresenter<V extends SplashMvpView> extends BasePresenter<V> implements SplashMvpPresenter<V> {
    public SplashPresenter(DataManager dataManager) {
        super(dataManager);
    }
    @Override
    public void decideNextActivity(){
        if (getDataManager().getLoggedInMode()) {
            getMvpView().openMainActivity();
        } else {
            getMvpView().openLoginActivity();
        }
    }
}
