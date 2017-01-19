package com.clementedirosa.contactdetail;

import android.app.LoaderManager;
import android.content.ContentValues;
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

import com.clementedirosa.contactdetail.data.AddDialog;
import com.clementedirosa.contactdetail.data.ContactAdapter;
import com.clementedirosa.contactdetail.data.ContactHelper;
import com.clementedirosa.contactdetail.data.MyContentProvider;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, SearchView.OnQueryTextListener, AddDialog.IOAdd {

    public static final String ITEM_ID = "ITEM_ID";
    private ContactAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView vList = (ListView) findViewById(R.id.contactListView);

        mAdapter = new ContactAdapter(this,null);

        mAdapter.setFilterQueryProvider(new FilterQueryProvider() {

            public Cursor runQuery(CharSequence constraint) {
                String vInfo2Search = new String(constraint.toString()).toUpperCase();
                Cursor vCursor = getContentResolver().query(MyContentProvider.CONTACTS_URI, null, "UPPER(" + ContactHelper.COGNOME + " || ' ' || " + ContactHelper.NOME + ") LIKE '%" + vInfo2Search + "%' OR UPPER(" + ContactHelper.NOME + " || ' ' || " + ContactHelper.COGNOME + ") LIKE '%" + vInfo2Search + "%'" , null, null);
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
//                ContentValues vale = new ContentValues();
//                vale.put(ContactHelper.NOME, "clem");
//                vale.put(ContactHelper.COGNOME, "dr");
//                getContentResolver().insert(MyContentProvider.CONTACTS_URI, vale);
                AddDialog.getInstance().show(getSupportFragmentManager(), "AddDialog");
            }
        });
}

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d("Loader<Cursor>", "a" + id);
        CursorLoader cursorLoader = new CursorLoader(this, MyContentProvider.CONTACTS_URI, null,null,null,null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        Log.d("onLoadFinished", "" + loader.getId());
        mAdapter.swapCursor(data);
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
        Log.d("YES", "Main yes: ");
        ContentValues vValues = new ContentValues();
        vValues.put(ContactHelper.NOME, aName);
        vValues.put(ContactHelper.COGNOME, aSurname);
        getContentResolver().insert(MyContentProvider.CONTACTS_URI, vValues);

    }
}
