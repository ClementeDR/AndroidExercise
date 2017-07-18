package com.clementedirosa.weathercontentprovider;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.clementedirosa.weathercontentprovider.adapter.TemperatureAdapter;
import com.clementedirosa.weathercontentprovider.data.CityHelper;
import com.clementedirosa.weathercontentprovider.data.DBHelper;
import com.clementedirosa.weathercontentprovider.data.MyContentProvider;
import com.clementedirosa.weathercontentprovider.data.TemperatureHelper;
import com.clementedirosa.weathercontentprovider.dialog.DialogAddTemperature;
import com.clementedirosa.weathercontentprovider.dialog.DialogDeleteTemperature;
import com.clementedirosa.weathercontentprovider.dialog.DialogUpdateTemperature;

public class InfoActivity extends AppCompatActivity implements  LoaderManager.LoaderCallbacks<Cursor>, DialogAddTemperature.IAddTemperature, DialogUpdateTemperature.IUpdateTemperature, DialogDeleteTemperature.IDeleteTemperature {

    long mCityID;
    private ListView mTemperatureList;
    private Button mBtnAddTemperature;
    private TemperatureAdapter mTemperatureAdapter;
    private static final String CITY_NAME = "City_name";
    MyAsyncLoadTitle mLoadTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);


        mBtnAddTemperature = (Button)findViewById(R.id.addTemperature);
        mTemperatureList = (ListView)findViewById(R.id.listTemperature);

        Bundle vExtras = getIntent().getExtras();
        mCityID = vExtras.getLong(MainActivity.ITEM_ID);
        // Giusto per visualizzare anche il nome della città
        if (savedInstanceState != null){
            getSupportActionBar().setTitle(savedInstanceState.getString(CITY_NAME));
        } else {
            mLoadTitle = new MyAsyncLoadTitle();
            mLoadTitle.execute();
        }


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

        mTemperatureList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DialogDeleteTemperature.getInstance(id).show(getSupportFragmentManager(), "Delete Temperature");

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
    protected void onDestroy() {
        super.onDestroy();
        if (mLoadTitle != null && !mLoadTitle.isCancelled()){
            mLoadTitle.cancel(true);
        }
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
        outState.putString(CITY_NAME, getSupportActionBar().getTitle().toString());
    }

    @Override
    public void deleteTemperature(long aID) {
        Uri vUriToDelete = Uri.parse(MyContentProvider.TEMPERATURE_URI + "/" + aID);
        getContentResolver().delete(vUriToDelete, null, null);
    }

    private class MyAsyncLoadTitle extends AsyncTask<Void, Integer, String> {

        @Override
        protected String doInBackground(Void... params) {

            SQLiteDatabase vDB = new DBHelper(getApplicationContext()).getReadableDatabase();
            final Cursor vCursor = vDB.query(CityHelper.TABLE_NAME, null, CityHelper._ID + "=" + mCityID,null,null,null,null);
            while (vCursor.moveToFirst()){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getSupportActionBar().setTitle(vCursor.getString(vCursor.getColumnIndex(CityHelper.NAME)));
                    }
                });
            }
            vCursor.close();
            vDB.close();
            return "Finish";
        }
    }


}
