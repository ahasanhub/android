package com.contextidea.mvpapp.ui.main;

import com.contextidea.mvpapp.ui.base.BasePresenter;
import com.contextidea.mvpapp.ui.base.BaseView;
import com.contextidea.mvpapp.ui.splash.SplashContract;

public interface MainContract {
    interface View extends BaseView<MainContract.Presenter> {
        void showEmail(String email);
        void openSplashActivity();
    }
    interface Presenter extends BasePresenter {
        void loadEmail();
        void setUserLoggedOut();
    }
}
