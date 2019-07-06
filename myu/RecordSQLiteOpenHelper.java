package com.meida.shaokaoshop.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 项目名称：ShaoKaoShop
 * 创建人：刘
 * 创建时间：2019-05-06 08:57
 * 功能描述：
 */
public class RecordSQLiteOpenHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "temp.db";
    private final static int DB_VERSION = 1;

    public RecordSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlStr = "CREATE TABLE IF NOT EXISTS records (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);";
        db.execSQL(sqlStr);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
