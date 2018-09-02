package com.contextidea.samplemvp.ui.base;

import com.contextidea.samplemvp.data.DataManager;

/**
 * Created by User on 1/9/2018.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {
    private V mMvpView;
    DataManager mDataManager;

    public BasePresenter(DataManager dataManager){
        mDataManager = dataManager;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }
    public V getMvpView() {
        return mMvpView;
    }
    public DataManager getDataManager() {
        return mDataManager;
    }
}
