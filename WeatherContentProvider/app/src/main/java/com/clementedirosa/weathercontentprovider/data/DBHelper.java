package com.clementedirosa.weathercontentprovider.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by clementedirosa on 13/01/2017.
 */

public class DBHelper  extends SQLiteOpenHelper {
    private static final String DB_NAME = "CityTemp";
    private static final int VERSION = 1;


    public DBHelper(Context aContext){
        super(aContext,DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CityHelper.CREATE_QUERY);
        sqLiteDatabase.execSQL(TemperatureHelper.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(CityHelper.DROP_QUERY);
        sqLiteDatabase.execSQL(TemperatureHelper.DROP_QUERY);
    }
}