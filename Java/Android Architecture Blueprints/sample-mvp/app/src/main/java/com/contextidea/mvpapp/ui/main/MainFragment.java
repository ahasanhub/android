package com.contextidea.mvpapp.ui.main;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.contextidea.mvpapp.R;
import com.contextidea.mvpapp.ui.splash.SplashActivity;
import com.contextidea.mvpapp.ui.splash.SplashContract;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements MainContract.View{

    private MainContract.Presenter mPresenter;
    TextView textViewShow;
    Button buttonLogout;
    public MainFragment() {
        // Required empty public constructor
    }
    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        textViewShow = (TextView) view.findViewById(R.id.textViewShow);

        buttonLogout = (Button) view.findViewById(R.id.buttonLogout);

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.setUserLoggedOut();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showEmail(String email) {
        textViewShow.setText(email);
    }

    @Override
    public void openSplashActivity() {
        Intent intent = new Intent(getContext(), SplashActivity.class);
        startActivity(intent);
    }

    @Override
    public void setPresenter(MainContract.Presenter presenter) {
     mPresenter=presenter;
    }
}
