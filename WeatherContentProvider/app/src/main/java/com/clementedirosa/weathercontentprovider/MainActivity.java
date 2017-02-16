package com.clementedirosa.weathercontentprovider;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.clementedirosa.weathercontentprovider.data.AddCityDialog;
import com.clementedirosa.weathercontentprovider.data.CityAdapter;
import com.clementedirosa.weathercontentprovider.data.CityHelper;
import com.clementedirosa.weathercontentprovider.data.DialogUpdateCity;
import com.clementedirosa.weathercontentprovider.data.MyContentProvider;
import com.clementedirosa.weathercontentprovider.data.TemperatureHelper;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, AddCityDialog.IOAddCity, DialogUpdateCity.IOUpdateCity {
    public static final String ITEM_ID = "ITEM_ID";
    private ListView mListView;
    private Button mBtnAddCity;
    private CityAdapter mCityAdampter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnAddCity = (Button) findViewById(R.id.addCity);
        mListView = (ListView) findViewById(R.id.listCity);

        mCityAdampter = new CityAdapter(this,null);
        mListView.setAdapter(mCityAdampter);

        getLoaderManager().initLoader(0,null,this);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent vIntent = new Intent(MainActivity.this, InfoActivity.class);
                Bundle vBundle = new Bundle();
                vBundle.putLong(ITEM_ID, id);
                vIntent.putExtras(vBundle);
                startActivity(vIntent);
            }
        });

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                updateItem(id);
                return true;
            }
        });


        mBtnAddCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });

    }

    private void updateItem(long id) {
        DialogUpdateCity.getInstance(id).show(getSupportFragmentManager(), "UpdateCity");
    }

    private void add() {
        AddCityDialog.getInstance().show(getSupportFragmentManager(), "AddCityDialog");
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d("onCreateLoader", "onCreateLoader");
        CursorLoader cursorLoader;
        cursorLoader = new CursorLoader(this, MyContentProvider.CITY_URI, null,null,null,null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCityAdampter.swapCursor(data);
        Log.d("onLoadFinished", "onLoadFinished");
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCityAdampter.swapCursor(null);
        Log.d("onLoaderReset", "onLoaderReset");
    }

    @Override
    public void yes(String aCityName) {
        ContentValues vValues = new ContentValues();
        vValues.put(CityHelper.NAME, aCityName);
        getContentResolver().insert(MyContentProvider.CITY_URI, vValues);
    }

    @Override
    public void update(long aID, String aName) {
        ContentValues vValues = new ContentValues();
        vValues.put(CityHelper.NAME, aName);
        Uri vUriToUpdate = Uri.parse(MyContentProvider.CITY_URI + "/" + aID);
        getContentResolver().update(vUriToUpdate, vValues,null,null);
    }

    @Override
    public void deleteCity(long aId) {
        Uri vUriCityToDelete = Uri.parse(MyContentProvider.CITY_URI + "/" + aId);
        getContentResolver().delete(vUriCityToDelete, null, null);
        getContentResolver().delete(MyContentProvider.TEMPERATURE_URI, TemperatureHelper.CITY_ID + "=" + aId, null);

    }
}
