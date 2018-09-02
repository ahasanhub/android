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

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 *
 * Created by Igor Havrylyuk on 28.03.2017.
 */

public class NewsDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "news.db";

    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;

    private Context mContext;

    private final String SQL_CREATE_SOURCES_TABLE = "CREATE TABLE " +
            NewsContract.SourcesEntry.TABLE_NAME + " (" +
            NewsContract.SourcesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NewsContract.SourcesEntry.SOURCE_ID + " TEXT NOT NULL DEFAULT '', " +
            NewsContract.SourcesEntry.SOURCE_NAME + " TEXT NOT NULL DEFAULT '', " +
            NewsContract.SourcesEntry.SOURCE_DESC + " TEXT NOT NULL DEFAULT '', " +
            NewsContract.SourcesEntry.SOURCE_CATEGORY + " TEXT NOT NULL DEFAULT '', " +
            NewsContract.SourcesEntry.SOURCE_URL + " TEXT NOT NULL DEFAULT '', " +
            NewsContract.SourcesEntry.SOURCE_LOGO + " TEXT NOT NULL DEFAULT '', " +
            " UNIQUE (" + NewsContract.SourcesEntry.SOURCE_ID + ") ON CONFLICT REPLACE);";

    private final String SQL_CREATE_ARTICLES_TABLE = "CREATE TABLE " +
            NewsContract.ArticlesEntry.TABLE_NAME + " (" +
            NewsContract.ArticlesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NewsContract.ArticlesEntry.ART_SOURCE + " TEXT NOT NULL DEFAULT '', " +
            NewsContract.ArticlesEntry.ART_AUTHOR + " TEXT DEFAULT '', " +
            NewsContract.ArticlesEntry.ART_IMAGE + " TEXT DEFAULT '', " +
            NewsContract.ArticlesEntry.ART_TITLE + " TEXT NOT NULL DEFAULT '', " +
            NewsContract.ArticlesEntry.ART_DESC + " TEXT DEFAULT '', " +
            NewsContract.ArticlesEntry.ART_URL + " TEXT NOT NULL DEFAULT '', " +
            NewsContract.ArticlesEntry.ART_DATE + " TEXT NOT NULL DEFAULT '', " +
            " UNIQUE (" + NewsContract.ArticlesEntry.ART_URL + ") ON CONFLICT REPLACE);";

    public NewsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_SOURCES_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_ARTICLES_TABLE);

    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
