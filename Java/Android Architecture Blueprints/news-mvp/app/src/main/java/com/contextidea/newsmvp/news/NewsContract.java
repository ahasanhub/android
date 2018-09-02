package com.contextidea.newsmvp.news;

import android.support.annotation.NonNull;


import com.contextidea.newsmvp.BasePresenter;
import com.contextidea.newsmvp.BaseView;
import com.contextidea.newsmvp.data.model.Article;

import java.util.List;

/**
 * Created by Igor Havrylyuk on 27.03.2017.
 */

public interface NewsContract {

    interface View extends BaseView {

        void showArticles(@NonNull List<Article> sources);

        void setRefreshing(boolean refreshing);

        boolean isNetworkAvailable();

        boolean isActive();

        void showLoadingSourcesError();

        void showNoSourcesData();
    }

    interface Presenter extends BasePresenter {

        void loadArticles(String sourceId);
    }
}
