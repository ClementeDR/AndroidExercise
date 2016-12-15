package com.example.cdr.sqlexercise;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.Date;

public class MainActivity extends AppCompatActivity implements DeleteDialog.IODeleteDialog, UpdateDialog.IOUpdateDialog {
    private ContactsAdapter mAdapter;
    private ListView mListView;
    private Button mBtnAddContact;
    private DataSet mDataSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataSet = DataSet.getInstance(this);

        mListView = (ListView) findViewById(R.id.listView);
        mBtnAddContact = (Button) findViewById(R.id.btnAddContact);
        mAdapter  = new ContactsAdapter(this, mDataSet.getAllContacts());
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //l da identificativo dell'elemento ->> i posizione
                DeleteDialog.getInstance((int)l).show(getSupportFragmentManager(),"Delete");
            }
        });


        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                UpdateDialog.getInstance(i).show(getSupportFragmentManager(),"Update");
                return true;
            }
        });

        mBtnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addContact();
            }
        });
    }

    private void addContact() {
        Contact vContact = new Contact();
        vContact.setName("Name " + new Date().getTime());
        vContact.setSurname("Surname " + new Date().getTime());
        mDataSet.addContact(vContact);
        mAdapter.setList(mDataSet.getAllContacts());
    }

    @Override
    public void delete(int aID) {
        Log.d("TAG!", "deleted");
        mDataSet.deleteContact(aID);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void update(int aID, String aName, String aSurname) {
        Log.d("TAG","updated " + aName + " " + aID + " " + aSurname);

        Contact vContact = mDataSet.getContactByPosition(aID);
        vContact.setName(aName);
        vContact.setSurname(aSurname);

        mDataSet.updateContact(vContact);
        mAdapter.notifyDataSetChanged();

    }
}
