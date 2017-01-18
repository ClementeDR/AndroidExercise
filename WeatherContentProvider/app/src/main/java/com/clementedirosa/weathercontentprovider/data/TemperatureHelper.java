package com.clementedirosa.weathercontentprovider.data;

import android.provider.BaseColumns;

/**
 * Created by clementedirosa on 13/01/2017.
 */

public class TemperatureHelper implements BaseColumns {
    public static final String TABLE_NAME = "Temperature";
    public static final String TEMPERATURE = "Temp";
    public static final String DATA = "Data";
    public static final String CITY_ID = "City_ID";

    public static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME +
            "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TEMPERATURE + " REAL NOT NULL, " +
            DATA + " INTEGER NOT NULL, " +
            CITY_ID + " INTEGER NOT NULL" +
            ");";

    public static final String DROP_QUERY = "DROP TABLE " + TABLE_NAME;
}
