package com.contextidea.newsmvp.data.source.local;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;


import com.contextidea.newsmvp.data.model.Article;
import com.contextidea.newsmvp.data.model.Source;
import com.contextidea.newsmvp.data.model.UrlsToLogos;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


/**
 * Created by Igor Havrylyuk on 27.03.2017.
 */

public class LocalDataSource implements ILocalDataSource {


    private static final String LOG_TAG = LocalDataSource.class.getSimpleName();

    private static LocalDataSource INSTANCE;

    private ContentResolver contentResolver;

    private LocalDataSource(@NonNull ContentResolver contentResolver) {
        checkNotNull(contentResolver);
        this.contentResolver = contentResolver;
    }

    public static LocalDataSource getInstance(@NonNull ContentResolver contentResolver) {
        if (INSTANCE == null) {
            INSTANCE = new LocalDataSource(contentResolver);
        }
        return INSTANCE;
    }

    public void getSource(@NonNull String sourceId, @NonNull LoadDataCallback callback) {
    }

    @Override
    public void getAllSources(@NonNull LoadDataCallback<Source> callback) {
        List<Source> sources = new ArrayList<>();
        Cursor c = contentResolver.query(NewsContract.SourcesEntry.CONTENT_URI, null, null, null, null);
        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                String id = c.getString(c.getColumnIndexOrThrow(NewsContract.SourcesEntry.SOURCE_ID));
                String name = c.getString(c.getColumnIndexOrThrow(NewsContract.SourcesEntry.SOURCE_NAME));
                String desc = c.getString(c.getColumnIndexOrThrow(NewsContract.SourcesEntry.SOURCE_DESC));
                String logo = c.getString(c.getColumnIndexOrThrow(NewsContract.SourcesEntry.SOURCE_LOGO));
                Source task = new Source(id, name, desc, new UrlsToLogos(logo));
                sources.add(task);
            }
        }
        if (c != null) {
            c.close();
        }
        if (sources.isEmpty()) {
            // This will be called if the table is new or just empty.
            callback.onDataNotAvailable();
        } else {
            callback.onDataLoaded(sources);
        }
    }

    @Override
    public void refreshSource(@NonNull Source source) {
        checkNotNull(source);
        ContentValues values = NewsValues.from(source);
        String selection = NewsContract.SourcesEntry.SOURCE_ID + " = ?";
        String[] selectionArgs = {source.getId()};
        contentResolver.update(NewsContract.SourcesEntry.CONTENT_URI, values, selection, selectionArgs);
    }

    @Override
    public void saveSource(@NonNull Source source) {
        checkNotNull(source);
        ContentValues values = NewsValues.from(source);
        contentResolver.insert(NewsContract.SourcesEntry.CONTENT_URI, values);
    }

    @Override
    public void saveSources(@NonNull List<Source> sources) {
        checkNotNull(sources);
        ContentValues[] values = new ContentValues[sources.size()];
        for (int i = 0; i < sources.size(); i++) {
            values[i] = NewsValues.from(sources.get(i));
        }
        contentResolver.bulkInsert(NewsContract.SourcesEntry.CONTENT_URI, values);
    }

    @Override
    public void deleteAllSources() {
        contentResolver.delete(NewsContract.SourcesEntry.CONTENT_URI, null, null);
    }

    @Override
    public void deleteSource(@NonNull String sourceId) {
        checkNotNull(sourceId);
        String selection = NewsContract.SourcesEntry._ID + " = ?";
        String[] selectionArgs = {sourceId};
        contentResolver.delete(NewsContract.SourcesEntry.CONTENT_URI, selection, selectionArgs);
    }

    @Override
    public void getArticles(@NonNull String sourceId, @NonNull LoadDataCallback<Article> callback) {
        List<Article> articles = new ArrayList<>();
        Cursor c = contentResolver.query(NewsContract.ArticlesEntry.CONTENT_URI, null,
                NewsContract.ArticlesEntry.ART_SOURCE + " = ? ",
                new String[]{sourceId},
                null);
        if (c != null && c.getCount() > 0) {
            while (c.moveToNext()) {
                String author = c.getString(c.getColumnIndexOrThrow(NewsContract.ArticlesEntry.ART_AUTHOR));
                String title = c.getString(c.getColumnIndexOrThrow(NewsContract.ArticlesEntry.ART_TITLE));
                String desc = c.getString(c.getColumnIndexOrThrow(NewsContract.ArticlesEntry.ART_DESC));
                String date = c.getString(c.getColumnIndexOrThrow(NewsContract.ArticlesEntry.ART_DATE));
                String image = c.getString(c.getColumnIndexOrThrow(NewsContract.ArticlesEntry.ART_IMAGE));
                String url = c.getString(c.getColumnIndexOrThrow(NewsContract.ArticlesEntry.ART_URL));
                Article article = new Article(author, image, title, desc, url, date);
                articles.add(article);
            }
        }
        if (c != null) {
            c.close();
        }
        if (articles.isEmpty()) {
            // This will be called if the table is new or just empty.
            callback.onDataNotAvailable();
        } else {
            callback.onDataLoaded(articles);
        }
    }

    public void getAllArticles() {
    }

    @Override
    public void refreshArticle(@NonNull String sourceId, @NonNull Article article) {
        checkNotNull(article);
        ContentValues values = NewsValues.from(sourceId, article);
        String selection = NewsContract.ArticlesEntry._ID + " = ?";
        String[] selectionArgs = {article.getSourceId()};
        contentResolver.update(NewsContract.ArticlesEntry.CONTENT_URI, values, selection, selectionArgs);
    }

    @Override
    public void saveArticles(@NonNull String sourceId, @NonNull Article article) {
        checkNotNull(article);
        ContentValues values = NewsValues.from(sourceId, article);
        contentResolver.insert(NewsContract.ArticlesEntry.CONTENT_URI, values);
    }

    @Override
    public void saveArticles(@NonNull String sourceId, @NonNull List<Article> articles) {
        if (!checkNotNull(articles).isEmpty()){
            deleteArticles(sourceId);
            ContentValues[] values = new ContentValues[articles.size()];
            for (int i = 0; i < articles.size(); i++) {
                values[i] = NewsValues.from(sourceId, articles.get(i));
            }
            contentResolver.bulkInsert(NewsContract.ArticlesEntry.CONTENT_URI, values);
        }

    }

    @Override
    public void deleteAllArticles() {
        contentResolver.delete(NewsContract.ArticlesEntry.CONTENT_URI, null, null);
    }

    @Override
    public void deleteArticles(@NonNull String sourceId) {
        checkNotNull(sourceId);
        String selection = NewsContract.ArticlesEntry.ART_SOURCE + " = ?";
        String[] selectionArgs = {sourceId};
        contentResolver.delete(NewsContract.ArticlesEntry.CONTENT_URI, selection, selectionArgs);
    }


}
