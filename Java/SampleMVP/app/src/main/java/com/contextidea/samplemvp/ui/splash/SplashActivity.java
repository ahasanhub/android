package com.contextidea.samplemvp.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.contextidea.samplemvp.MvpApp;
import com.contextidea.samplemvp.R;
import com.contextidea.samplemvp.data.DataManager;
import com.contextidea.samplemvp.ui.base.BaseActivity;
import com.contextidea.samplemvp.ui.login.LoginActivity;
import com.contextidea.samplemvp.ui.main.MainActivity;

public class SplashActivity extends BaseActivity implements SplashMvpView {
    SplashPresenter mSplashPresenter;
    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, SplashActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        DataManager dataManager = ((MvpApp) getApplication()).getDataManager();

        mSplashPresenter = new SplashPresenter(dataManager);

        mSplashPresenter.onAttach(this);

        mSplashPresenter.decideNextActivity();
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }
}
