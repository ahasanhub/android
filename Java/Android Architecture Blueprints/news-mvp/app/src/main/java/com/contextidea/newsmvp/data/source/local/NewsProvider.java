/*
 * Copyright (c)  2017. Igor Havrylyuk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.contextidea.newsmvp.data.source.local;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.NonNull;



/**
 *
 * Created by Igor Havrylyuk on 28.03.2017.
 */

public class NewsProvider extends ContentProvider {

    // The URI Matcher used by this content provider.
    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private NewsDBHelper openHelper;

    static final int SOURCES = 1000;
    static final int SOURCES_WITH_ID = 1001;
    static final int ARTICLES = 1002;
    static final int ARTICLES_WITH_ID = 1003;

    private static final SQLiteQueryBuilder sSourceByIdQueryBuilder;
    private static final SQLiteQueryBuilder sAtricleByIdQueryBuilder;

    static {
        sSourceByIdQueryBuilder = new SQLiteQueryBuilder();
        sSourceByIdQueryBuilder.setTables(NewsContract.SourcesEntry.TABLE_NAME);
        sAtricleByIdQueryBuilder = new SQLiteQueryBuilder();
        sAtricleByIdQueryBuilder.setTables(NewsContract.ArticlesEntry.TABLE_NAME);
    }

    static UriMatcher buildUriMatcher() {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = NewsContract.CONTENT_AUTHORITY;
        // For each type of URI you want to add, create a corresponding code.
        matcher.addURI(authority, NewsContract.PATH_SOURCES, SOURCES);
        matcher.addURI(authority, NewsContract.PATH_SOURCES +"/#", SOURCES_WITH_ID);
        matcher.addURI(authority, NewsContract.PATH_ARTICLES, ARTICLES);
        matcher.addURI(authority, NewsContract.PATH_ARTICLES +"/#", ARTICLES_WITH_ID);
        return matcher;
    }

    private static final String sourceByIdSelection =
            NewsContract.SourcesEntry.TABLE_NAME + "." + NewsContract.SourcesEntry._ID + " = ? ";

    private static final String articleByIdSelection =
            NewsContract.ArticlesEntry.TABLE_NAME + "." + NewsContract.ArticlesEntry._ID + " = ? ";

    private Cursor getSourceById(Uri uri, String[] projection, String sortOrder) {
        String selectionId = String.valueOf(NewsContract.SourcesEntry.getIdFromUri(uri));
        String[] selectionArgs = new String[]{selectionId};
        return sSourceByIdQueryBuilder.query(openHelper.getReadableDatabase(),
                projection,
                sourceByIdSelection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
    }

    private Cursor getArticleById(Uri uri, String[] projection, String sortOrder) {
        String selectionId = String.valueOf(NewsContract.ArticlesEntry.getIdFromUri(uri));
        String[] selectionArgs = new String[]{selectionId};
        return sAtricleByIdQueryBuilder.query(openHelper.getReadableDatabase(),
                projection,
                articleByIdSelection,
                selectionArgs,
                null,
                null,
                sortOrder
        );
    }

    public NewsProvider() {
    }

    @Override
    public boolean onCreate() {
        openHelper = new NewsDBHelper(getContext());
        return true;
    }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = openHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsDeleted;
        switch (match) {
            case SOURCES:
                rowsDeleted = db.delete(
                        NewsContract.SourcesEntry.TABLE_NAME, selection, selectionArgs);
                break;
            case ARTICLES:
                rowsDeleted = db.delete(
                        NewsContract.ArticlesEntry.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        // Because a null deletes all rows
        if (rowsDeleted != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public String getType(Uri uri) {
        // Use the Uri Matcher to determine what kind of URI this is.
        final int match = sUriMatcher.match(uri);
        switch (match) {
            case SOURCES:
                return NewsContract.SourcesEntry.CONTENT_TYPE;
            case SOURCES_WITH_ID:
                return NewsContract.SourcesEntry.CONTENT_ITEM_TYPE;
            case ARTICLES:
                return NewsContract.ArticlesEntry.CONTENT_TYPE;
            case ARTICLES_WITH_ID:
                return NewsContract.ArticlesEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        final SQLiteDatabase db = openHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        Uri returnUri;
        switch (match) {
            case SOURCES: {
                long _id = db.insert(NewsContract.SourcesEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = NewsContract.SourcesEntry.buildSourceUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            case ARTICLES: {
                long _id = db.insert(NewsContract.ArticlesEntry.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = NewsContract.ArticlesEntry.buildArticleUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            case SOURCES: {
                retCursor = openHelper.getReadableDatabase().query(
                        NewsContract.SourcesEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case SOURCES_WITH_ID: {
                retCursor = getSourceById(uri, projection, sortOrder);
                break;
            }
            case ARTICLES: {
                retCursor = openHelper.getReadableDatabase().query(
                        NewsContract.ArticlesEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            case ARTICLES_WITH_ID: {
                retCursor = getArticleById(uri, projection, sortOrder);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        final SQLiteDatabase db = openHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int rowsUpdated;
        switch (match) {
            case SOURCES:
                rowsUpdated = db.update(NewsContract.SourcesEntry.TABLE_NAME, values, selection,
                       selectionArgs);
                break;
            case ARTICLES:
                rowsUpdated = db.update(NewsContract.ArticlesEntry.TABLE_NAME, values, selection,
                        selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        if (rowsUpdated != 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsUpdated;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = openHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int returnCount = 0;
        switch (match) {
            case SOURCES:
                db.beginTransaction();
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(NewsContract.SourcesEntry.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;
            case ARTICLES:
                db.beginTransaction();
                try {
                    for (ContentValues value : values) {
                        long _id = db.insert(NewsContract.ArticlesEntry.TABLE_NAME, null, value);
                        if (_id != -1) {
                            returnCount++;
                        }
                    }
                    db.setTransactionSuccessful();
                } finally {
                    db.endTransaction();
                }
                getContext().getContentResolver().notifyChange(uri, null);
                return returnCount;
            default:
                return super.bulkInsert(uri, values);
        }
    }
}
