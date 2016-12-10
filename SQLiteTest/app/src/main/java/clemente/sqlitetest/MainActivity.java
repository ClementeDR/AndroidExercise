package clemente.sqlitetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private DBHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDBHelper = new DBHelper(this);


        insertData();

        SQLiteDatabase vDB = mDBHelper.getReadableDatabase();

        Cursor vCursor = vDB.query(ItemsHelper.TABLE_NAME, new String[]{ItemsHelper.QUANTITY},null,null,ItemsHelper.QUANTITY,ItemsHelper.QUANTITY + " < 50",ItemsHelper.QUANTITY + " DESC");

        while (vCursor.moveToNext()){
           // int vID = vCursor.getInt(vCursor.getColumnIndex(ItemsHelper._ID));
            //String vName = vCursor.getString(vCursor.getColumnIndex(ItemsHelper.NAME));
            int vQuantity = vCursor.getInt(vCursor.getColumnIndex(ItemsHelper.QUANTITY));

            //Log.d(TAG, "DATA _ID: " + vID + " Name: " + vName + " Quantity: " + vQuantity);
            //Log.d(TAG,  " Name: " + vName );
            Log.d(TAG, "Quantity: " + vQuantity);
        }
        vCursor.close();
        vDB.close();
    }

    void insertData(){
        SQLiteDatabase vDB = mDBHelper.getReadableDatabase();
        Cursor vCursor = vDB.query(ItemsHelper.TABLE_NAME, null, null,null,null,null,null);
        if(vCursor.getCount() == 0){
            vCursor.close();
            Log.d(TAG, "insert data");
            for (int vIndex = 0; vIndex < 100; vIndex++) {
                ContentValues vCV = new ContentValues();
                vCV.put(ItemsHelper.NAME, "Name " + (vIndex + 1));
                vCV.put(ItemsHelper.QUANTITY, vIndex + 1);
                vDB.insert(ItemsHelper.TABLE_NAME, null, vCV);
            }
        }
        vDB.close();
    }

}
