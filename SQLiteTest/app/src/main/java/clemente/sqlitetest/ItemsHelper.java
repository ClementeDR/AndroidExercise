package clemente.sqlitetest;

import android.provider.BaseColumns;

/**
 * Created by clemente on 25/11/16.
 */

public class ItemsHelper implements BaseColumns{


    public static final String TABLE_NAME = "items";

    public static final String NAME = "name";
    public static final String QUANTITY = "quantity";

    public static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME +
            "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NAME + " TEXT NOT NULL, " +
            QUANTITY + " INTEGER NOT NULL " +
            ");";

    public static final String DROP_QUERY = "DROP TABLE " + TABLE_NAME;

}
