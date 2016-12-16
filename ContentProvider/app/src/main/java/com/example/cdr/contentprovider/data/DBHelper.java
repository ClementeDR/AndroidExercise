package com.example.cdr.contentprovider.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by cdr on 15/12/16.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "ContentExercise";
    private static final int VERSION = 1;


    public DBHelper(Context aContext){
        super(aContext,DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ContactsHelper.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
}
