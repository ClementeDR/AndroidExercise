package com.example.cdr.contentprovider;

import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
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

import com.example.cdr.contentprovider.data.AddContactDialog;
import com.example.cdr.contentprovider.data.ContactContentProvider;
import com.example.cdr.contentprovider.data.ContactCursorAdapter;
import com.example.cdr.contentprovider.data.ContactsHelper;
import com.example.cdr.contentprovider.data.DeleteDialog;
import com.example.cdr.contentprovider.data.UpdateDialog;

import java.util.Random;

public class   MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> , UpdateDialog.IOUpdateDialog, DeleteDialog.IODeleteDialog, AddContactDialog.IOAddContact
{
    ContactCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button vBtnAddContact = (Button) findViewById(R.id.btnAddContact);
        ListView vList = (ListView) findViewById(R.id.listView);

        mAdapter = new ContactCursorAdapter(this,null);
        vList.setAdapter(mAdapter);

        getLoaderManager().initLoader(0,null,this);
        getLoaderManager().initLoader(1,null,this);

        vList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                deleteItem(id);
            }
        });

        vList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                updateItem(id);
                return true;
            }
        });

        vBtnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItemDialog();
            }
        });
    }

    private void addItemDialog() {
        AddContactDialog.getInstance().show(getSupportFragmentManager(), "Add");
    }

    private void updateItem(long aItemId) {
        UpdateDialog.getInstance(aItemId).show(getSupportFragmentManager(),"Update");
    }

    private void deleteItem(long aItemId) {
        DeleteDialog.getInstance(aItemId).show(getSupportFragmentManager(),"Delete");
    }

    private void addItem() {
        Random vRand = new Random();
        ContentValues vValues = new ContentValues();
        int a = vRand.nextInt();
        vValues.put(ContactsHelper.NAME, "name " + a);
        vValues.put(ContactsHelper.SURNAME, "Surname " + a);
        getContentResolver().insert(ContactContentProvider.CONTACTS_URI, vValues);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d("Loader<Cursor>", "a" + id);
        CursorLoader cursorLoader = new CursorLoader(this, ContactContentProvider.CONTACTS_URI, null,null,null,null);
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
    public void update(long aID, String aName, String aSurname) {

        ContentValues vValues = new ContentValues();
        vValues.put(ContactsHelper.NAME, aName);
        vValues.put(ContactsHelper.SURNAME, aSurname);

        Uri vUriToUpdate = Uri.parse(ContactContentProvider.CONTACTS_URI + "/" + aID);
        getContentResolver().update(vUriToUpdate, vValues,null,null);

    }

    @Override
    public void delete(long aID) {
        Uri uriToDelete = Uri.parse(ContactContentProvider.CONTACTS_URI + "/" + aID);
        getContentResolver().delete(uriToDelete, null,null);
    }

    @Override
    public void yes() {
        addItem();
    }
}
