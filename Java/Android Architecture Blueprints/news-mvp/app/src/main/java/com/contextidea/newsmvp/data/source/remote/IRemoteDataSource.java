package com.contextidea.newsmvp.data.source.remote;

import android.support.annotation.NonNull;

import com.contextidea.newsmvp.data.model.Article;
import com.contextidea.newsmvp.data.model.Source;
import com.contextidea.newsmvp.data.source.IDataSource;


/**
 * Created by Igor Havrylyuk on 27.03.2017.
 */

public interface IRemoteDataSource extends IDataSource {

    void getSources(@NonNull LoadDataCallback<Source> callback);

    void getArticles(String source, @NonNull LoadDataCallback<Article> callback);
}
