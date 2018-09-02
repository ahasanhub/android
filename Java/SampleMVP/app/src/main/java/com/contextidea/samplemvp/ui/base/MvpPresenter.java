package com.contextidea.samplemvp.ui.base;

/**
 * Created by User on 1/9/2018.
 */

public interface MvpPresenter<V extends MvpView> {
  void onAttach(V mvpView);
}
