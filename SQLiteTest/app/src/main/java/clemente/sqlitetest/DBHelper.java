package clemente.sqlitetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by clemente on 25/11/16.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "ilmiodatabase";
    private static final int VERSION = 1;


    public DBHelper(Context aContext){
        super(aContext,DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ItemsHelper.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(ItemsHelper.DROP_QUERY);
    }
}
