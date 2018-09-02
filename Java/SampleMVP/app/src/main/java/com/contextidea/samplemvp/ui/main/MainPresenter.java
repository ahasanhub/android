
package com.contextidea.samplemvp.ui.main;


import com.contextidea.samplemvp.data.DataManager;
import com.contextidea.samplemvp.ui.base.BasePresenter;

/**
 * Created by gaura on 23-08-2017.
 */

public class MainPresenter<V extends MainMvpView> extends BasePresenter<V> implements MainMvpPresenter<V> {

    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public String getEmailId() {
        return getDataManager().getEmailId();
    }

    @Override
    public void setUserLoggedOut() {
        getDataManager().clear();
        getMvpView().openSplashActivity();
    }

}
