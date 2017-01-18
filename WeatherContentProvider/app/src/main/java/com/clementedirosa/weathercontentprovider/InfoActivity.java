package com.clementedirosa.weathercontentprovider;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.clementedirosa.weathercontentprovider.data.CityHelper;
import com.clementedirosa.weathercontentprovider.data.DBHelper;
import com.clementedirosa.weathercontentprovider.data.DialogAddTemperature;
import com.clementedirosa.weathercontentprovider.data.DialogUpdateTemperature;
import com.clementedirosa.weathercontentprovider.data.MyContentProvider;
import com.clementedirosa.weathercontentprovider.data.TemperatureAdapter;
import com.clementedirosa.weathercontentprovider.data.TemperatureHelper;

public class InfoActivity extends AppCompatActivity implements  LoaderManager.LoaderCallbacks<Cursor>, DialogAddTemperature.IOAddTemperature, DialogUpdateTemperature.IOUpdateTemperature {

    long mCityID;
    private ListView mTemperatureList;
    private Button mBtnAddTemperature;
    private TemperatureAdapter mTemperatureAdapter;
    private static final String CITY_NAME = "City_name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Bundle vExtras = getIntent().getExtras();
        mCityID = vExtras.getLong(MainActivity.ITEM_ID);
        // Giusto per visualizzare anche il nome della città
        if (savedInstanceState != null){
            setTitle(savedInstanceState.getString(CITY_NAME));
        } else {
            SQLiteDatabase vDB = new DBHelper(this).getReadableDatabase();
            Cursor vCursor = vDB.query(CityHelper.TABLE_NAME, null, CityHelper._ID + "=" + mCityID,null,null,null,null);
            while (vCursor.moveToNext()){;
               setTitle(vCursor.getString(vCursor.getColumnIndex(CityHelper.NAME)));
            }
            vCursor.close();
            vDB.close();
        }

        mBtnAddTemperature = (Button) findViewById(R.id.addTemperature);
        mTemperatureList = (ListView) findViewById(R.id.listTemperature);

        mTemperatureAdapter = new TemperatureAdapter(this, null);
        mTemperatureList.setAdapter(mTemperatureAdapter);

        getLoaderManager().initLoader(1,null,this);

        mTemperatureList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DialogUpdateTemperature.getInstance(id).show(getSupportFragmentManager(), "UpdateTemperature");
                return true;
            }
        });

        mBtnAddTemperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAddTemperature.getInstance(mCityID).show(getSupportFragmentManager(), "AddTemperature");
            }
        });
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d("onCreateLoader", "onCreateLoader");
        CursorLoader cursorLoader;
        cursorLoader = new CursorLoader(this, MyContentProvider.TEMPERATURE_URI, null,TemperatureHelper.CITY_ID + "=" + mCityID,null,null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mTemperatureAdapter.swapCursor(data);
        Log.d("onLoadFinished", "onLoadFinished");
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mTemperatureAdapter.swapCursor(null);
        Log.d("onLoaderReset", "onLoaderReset");
    }

    @Override
    public void yes(float aTemperature, long aDate, long aCityID) {
        ContentValues vValues = new ContentValues();
        vValues.put(TemperatureHelper.TEMPERATURE, aTemperature);
        vValues.put(TemperatureHelper.DATA, aDate);
        vValues.put(TemperatureHelper.CITY_ID, aCityID);
        getContentResolver().insert(MyContentProvider.TEMPERATURE_URI, vValues);
    }

    @Override
    public void update(long aTempID, float aTemperature) {
        ContentValues vValues = new ContentValues();
        vValues.put(TemperatureHelper.TEMPERATURE, aTemperature);
        Uri vUriToUpdate = Uri.parse(MyContentProvider.TEMPERATURE_URI + "/" + aTempID);
        getContentResolver().update(vUriToUpdate, vValues,null,null);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(CITY_NAME, getTitle().toString());
    }
}
