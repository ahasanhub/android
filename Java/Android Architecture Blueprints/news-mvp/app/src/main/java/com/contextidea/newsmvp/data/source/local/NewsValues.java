package com.contextidea.newsmvp.data.source.local;

import android.content.ContentValues;
import android.support.annotation.NonNull;

import com.contextidea.newsmvp.data.model.Article;
import com.contextidea.newsmvp.data.model.Source;


/**
 * Created by Igor Havrylyuk on 28.03.2017.
 */

public class NewsValues {

    public static ContentValues from(Source source) {
        ContentValues values = new ContentValues();
        values.put(NewsContract.SourcesEntry.SOURCE_ID, source.getId());
        values.put(NewsContract.SourcesEntry.SOURCE_NAME, source.getName());
        values.put(NewsContract.SourcesEntry.SOURCE_DESC, source.getDescription());
        values.put(NewsContract.SourcesEntry.SOURCE_CATEGORY, source.getCategory());
        values.put(NewsContract.SourcesEntry.SOURCE_URL, source.getUrl());
        values.put(NewsContract.SourcesEntry.SOURCE_LOGO, source.getUrlsToLogos().getMedium());
        return values;
    }

    public static ContentValues from(@NonNull String sourceId, Article article) {
        ContentValues values = new ContentValues();
        values.put(NewsContract.ArticlesEntry.ART_SOURCE, sourceId);
        values.put(NewsContract.ArticlesEntry.ART_AUTHOR, article.getAuthor());
        values.put(NewsContract.ArticlesEntry.ART_IMAGE, article.getImageUrl());
        values.put(NewsContract.ArticlesEntry.ART_TITLE, article.getTitle());
        values.put(NewsContract.ArticlesEntry.ART_DESC, article.getDecr());
        values.put(NewsContract.ArticlesEntry.ART_URL, article.getUrl());
        values.put(NewsContract.ArticlesEntry.ART_DATE, article.getPublishedAt());
        return values;
    }
}
