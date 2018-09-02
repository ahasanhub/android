package com.contextidea.mvpapp.ui.splash;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.contextidea.mvpapp.R;
import com.contextidea.mvpapp.ui.login.LoginActivity;
import com.contextidea.mvpapp.ui.main.MainActivity;


public class SplashFragment extends Fragment implements SplashContract.View{
 private SplashContract.Presenter mPresenter;
    public SplashFragment() {
        // Required empty public constructor
    }

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        //This is start point
        mPresenter.start();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void openMainActivity() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void openLoginActivity() {
        Intent intent = new Intent(getContext(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPresenter(@NonNull SplashContract.Presenter presenter) {
     mPresenter=presenter;
    }
}
