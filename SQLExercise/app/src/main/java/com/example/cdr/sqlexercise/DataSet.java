package com.example.cdr.sqlexercise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by cdr on 14/12/16.
 */

public class DataSet {

    private static final String TAG = "DataSet";
    private static DataSet mInstance;
    private Context mContext;
    private ArrayList<Contact> mData;
    private DBHelper mDBHelper;


    private DataSet(Context context){
        mContext = context;
        mData = new ArrayList<Contact>();
        mDBHelper = new DBHelper(context);
        //test
//        for (int i = 0; i < 10; i++){
//            Contact vContact = new Contact();
//            vContact.setID(i);
//            vContact.setName("Name " + i);
//            vContact.setSurname("Surname " + i);
//            mData.add(vContact);
//        }

        //loadDB
        SQLiteDatabase vDB = mDBHelper.getReadableDatabase();
        Cursor vCursor = vDB.query(ContactsHelper.TABLE_NAME,null,null,null,null,null,null);
        while (vCursor.moveToNext()){
            int vID = vCursor.getInt(vCursor.getColumnIndex(ContactsHelper._ID));
            String vName = vCursor.getString(vCursor.getColumnIndex(ContactsHelper.NAME));
            String vSurname = vCursor.getString(vCursor.getColumnIndex(ContactsHelper.SURNAME));
            mData.add(new Contact(vID, vName, vSurname));
        }
        vCursor.close();
        vDB.close();
    }

    public static DataSet getInstance(Context context){
        if(mInstance == null)
            mInstance = new DataSet(context);
        return mInstance;

    }

    public ArrayList<Contact> getAllContacts() {
        return mData;
    }

    public Contact getContactByPosition(int aPosition) {
        return mData.get(aPosition);
    }

    public void addContact(Contact aContact) {
        SQLiteDatabase vDB = mDBHelper.getReadableDatabase();
        ContentValues vCV = new ContentValues();
        vCV.put(ContactsHelper.NAME, aContact.getName());
        vCV.put(ContactsHelper.SURNAME, aContact.getSurname());
        int vID = (int)vDB.insert(ContactsHelper.TABLE_NAME, null, vCV);
        aContact.setID(vID);
        mData.add(aContact);
        Log.d(TAG, "Contact inserito");
        vDB.close();
    }

    public void updateContact(Contact aContact){
        //cerca e update
        SQLiteDatabase vDB = mDBHelper.getWritableDatabase();
        ContentValues vContentValue = new ContentValues();
        vContentValue.put(ContactsHelper.NAME, aContact.getName());
        vContentValue.put(ContactsHelper.SURNAME, aContact.getSurname());
        vDB.update(ContactsHelper.TABLE_NAME, vContentValue, ContactsHelper._ID + '=' + aContact.getID(), null);
        mData.set(mData.indexOf(aContact), aContact);
        vDB.close();
    }

    public void deleteContact(int aID){
        //cerca e delete
        //detela dal db
        //deleta dalla lista
        SQLiteDatabase vDB = mDBHelper.getWritableDatabase();
        if(vDB.delete(ContactsHelper.TABLE_NAME, ContactsHelper._ID + "=" + aID, null) > 0){
            for (int i = 0; i < mData.size(); i++){
                if (mData.get(i).getID() == aID) {
                    mData.remove(i);
                    break;
                }
            }
        }
    }
}
