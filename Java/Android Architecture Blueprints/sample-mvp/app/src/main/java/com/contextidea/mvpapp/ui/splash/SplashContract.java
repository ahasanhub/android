package com.contextidea.mvpapp.ui.splash;

import com.contextidea.mvpapp.ui.base.BasePresenter;
import com.contextidea.mvpapp.ui.base.BaseView;

public interface SplashContract {
    interface View extends BaseView<Presenter>{
        void openMainActivity();
        void openLoginActivity();
    }
    interface Presenter extends BasePresenter{
        void decideNextActivity();
    }
}
