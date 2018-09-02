package com.contextidea.mvpapp.ui.login;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.contextidea.mvpapp.MvpApp;
import com.contextidea.mvpapp.R;
import com.contextidea.mvpapp.ui.splash.SplashFragment;
import com.contextidea.mvpapp.ui.splash.SplashPresenter;

public class LoginActivity extends AppCompatActivity {
 private LoginContract.Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginFragment loginFragment=(LoginFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (loginFragment==null)
        {
            loginFragment=LoginFragment.newInstance();
            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentFrame,loginFragment);
            transaction.commit();
        }
        mPresenter=new LoginPresenter(((MvpApp)getApplication()).getDataManager(),loginFragment);
    }
}
