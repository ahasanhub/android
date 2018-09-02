package com.contextidea.mvpapp.ui.main;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.contextidea.mvpapp.MvpApp;
import com.contextidea.mvpapp.R;


public class MainActivity extends AppCompatActivity {
private MainContract.Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainFragment mainFragment=(MainFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (mainFragment==null)
        {
            mainFragment=MainFragment.newInstance();
            FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.contentFrame,mainFragment);
            transaction.commit();
        }
        mPresenter=new MainPresenter(((MvpApp)getApplication()).getDataManager(),mainFragment);
    }
}
