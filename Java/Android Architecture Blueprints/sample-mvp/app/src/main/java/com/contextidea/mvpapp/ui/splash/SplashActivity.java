package com.contextidea.mvpapp.ui.splash;


import android.app.Application;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.contextidea.mvpapp.MvpApp;
import com.contextidea.mvpapp.R;

public class SplashActivity extends AppCompatActivity {
  private SplashContract.Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SplashFragment splashFragment=(SplashFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (splashFragment==null)
        {
            splashFragment=SplashFragment.newInstance();
            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentFrame,splashFragment);
            transaction.commit();
        }
        mPresenter=new SplashPresenter(((MvpApp)getApplication()).getDataManager(),splashFragment);
    }
}
