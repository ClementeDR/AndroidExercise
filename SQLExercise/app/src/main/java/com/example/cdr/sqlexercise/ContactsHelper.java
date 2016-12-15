package com.example.cdr.sqlexercise;

import android.provider.BaseColumns;

/**
 * Created by cdr on 15/12/16.
 */


public class ContactsHelper implements BaseColumns {
    public static final String TABLE_NAME = "Contacts";
    public static final String NAME = "name";
    public static final String SURNAME = "surname";

    public static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME +
            "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT NOT NULL, " +
            SURNAME + " INTEGER NOT NULL " +
            ");";

    public static final String DROP_QUERY = "DROP TABLE " + TABLE_NAME;


}
