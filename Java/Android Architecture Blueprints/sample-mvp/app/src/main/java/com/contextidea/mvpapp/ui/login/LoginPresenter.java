package com.contextidea.mvpapp.ui.login;

import com.contextidea.mvpapp.data.DataManager;
import com.contextidea.mvpapp.ui.splash.SplashContract;

public class LoginPresenter implements LoginContract.Presenter {
    private final DataManager mDataManager;
    private final LoginContract.View mLoginView;

    public LoginPresenter(DataManager dataManager, LoginContract.View loginView) {
        this.mDataManager = dataManager;
        this.mLoginView = loginView;

        mLoginView.setPresenter(this);
    }

    @Override
    public void startLogin(String emailId) {
        mDataManager.saveEmailId(emailId);
        mDataManager.setLoggedIn();
        mLoginView.openMainActivity();
    }

    @Override
    public void start() {

    }
}
