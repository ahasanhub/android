package com.contextidea.mvpapp.ui.main;

import com.contextidea.mvpapp.data.DataManager;


public class MainPresenter implements MainContract.Presenter {
    private final DataManager mDataManager;
    private final MainContract.View mMainView;

    public MainPresenter(DataManager dataManager, MainContract.View mainView) {
        this.mDataManager = dataManager;
        this.mMainView = mainView;

        mMainView.setPresenter(this);
    }
    @Override
    public void loadEmail() {
       mMainView.showEmail(mDataManager.getEmailId());
    }

    @Override
    public void setUserLoggedOut() {
        mDataManager.clear();
        mMainView.openSplashActivity();
    }

    @Override
    public void start() {
        loadEmail();
    }
}
