package com.clementedirosa.activeandroidapp;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FilterQueryProvider;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Cache;
import com.activeandroid.content.ContentProvider;
import com.activeandroid.query.Select;
import com.clementedirosa.activeandroidapp.adapter.ContactAdapter;
import com.clementedirosa.activeandroidapp.data.Anagrafica;
import com.clementedirosa.activeandroidapp.data.ContactHelper;
import com.clementedirosa.activeandroidapp.dialog.AddDialog;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, SearchView.OnQueryTextListener, AddDialog.IOAdd {

    public static final String ITEM_ID = "ITEM_ID";
    private ContactAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActiveAndroid.initialize(this);
        ListView vList = (ListView) findViewById(R.id.contactListView);

        mAdapter = new ContactAdapter(this,null);

        mAdapter.setFilterQueryProvider(new FilterQueryProvider() {

            public Cursor runQuery(CharSequence constraint) {
                String vInfo2Search = new String(constraint.toString()).toUpperCase();
                String resultRecords = new Select().
                        from(Anagrafica.class).where("UPPER(" + ContactHelper.COGNOME + " || ' ' || " + ContactHelper.NOME + ") LIKE '%"
                        + vInfo2Search + "%' OR UPPER(" + ContactHelper.NOME + " || ' ' || " + ContactHelper.COGNOME
                        + ") LIKE '%" + vInfo2Search + "%'").toSql();
                Cursor  vCursor = Cache.openDatabase().rawQuery(resultRecords, null);
                return vCursor;
            }

        });

        vList.setAdapter(mAdapter);

        getLoaderManager().initLoader(0,null,this);

        vList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Bundle vBundle = new Bundle();
                vBundle.putLong(ITEM_ID, id);
                startActivity(new Intent(MainActivity.this, DetailActivity.class).putExtras(vBundle));
            }
        });

        SearchView vSearch = (SearchView) findViewById(R.id.searchView);
        vSearch.setOnQueryTextListener(this);


        ImageButton vBtn = (ImageButton)findViewById(R.id.imageButton);
        vBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                AddDialog.getInstance().show(getSupportFragmentManager(), "AddDialog");
            }
        });
}

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d("Loader<Cursor>", "a" + id);
        CursorLoader cursorLoader = new CursorLoader(this,
                ContentProvider.createUri(Anagrafica.class, null),
                null, null, null, null
        );
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d("onLoadFinished", "" + loader.getId());
        if (!data.isClosed()){
            mAdapter.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        //mAdapter.getFilter().filter(query.toString());
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mAdapter.getFilter().filter(newText.toString());
        return true;
    }

    @Override
    public void yes(String aName, String aSurname) {
        Anagrafica vData = new Anagrafica();
        vData.name = aName;
        vData.cognome = aSurname;
        long saved = vData.save();
        mAdapter.notifyDataSetChanged();
        Log.d("YES", "Main yes: " + saved);
    }
}
