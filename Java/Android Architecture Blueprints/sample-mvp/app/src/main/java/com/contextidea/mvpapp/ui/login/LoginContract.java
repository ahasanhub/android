package com.contextidea.mvpapp.ui.login;

import com.contextidea.mvpapp.ui.base.BasePresenter;
import com.contextidea.mvpapp.ui.base.BaseView;
import com.contextidea.mvpapp.ui.main.MainContract;

public interface LoginContract {
    interface View extends BaseView<LoginContract.Presenter> {
        void openMainActivity();
        void onLoginButtonClick();
    }
    interface Presenter extends BasePresenter {
        void startLogin(String emailId);
    }
}
