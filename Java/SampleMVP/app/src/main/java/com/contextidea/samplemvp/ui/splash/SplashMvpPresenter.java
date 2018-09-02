package com.contextidea.samplemvp.ui.splash;

import com.contextidea.samplemvp.ui.base.MvpPresenter;

/**
 * Created by User on 1/9/2018.
 */

public interface SplashMvpPresenter<V extends SplashMvpView> extends MvpPresenter<V> {
    void decideNextActivity();
}
