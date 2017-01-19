package com.clementedirosa.contactdetail.data;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by clementedirosa on 19/01/2017.
 */

public class MyContentProvider extends ContentProvider {
    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/people";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/person";

    private static final String AUTHORITY = "com.clementedirosa.contactdetail.data.contentprovider";
    private static final String BASE_PATH_CONTACTS = "anagrafica";

    public static final Uri CONTACTS_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH_CONTACTS);

    private static final int CONTACTS = 10;
    private static final int CONTACTS_ID = 20;

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH_CONTACTS, CONTACTS);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH_CONTACTS + "/#", CONTACTS_ID);
    }

    private DBHelper mDBHelper;

    @Override
    public boolean onCreate() {
        mDBHelper = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sordOrder) {

        SQLiteQueryBuilder vQueryBuilder = new SQLiteQueryBuilder();

        int vUriType = sURIMatcher.match(uri);

        Log.d("uri ", uri.toString());
        switch (vUriType){
            case CONTACTS:
                vQueryBuilder.setTables(ContactHelper.TABLE_NAME);
                break;
            case CONTACTS_ID:
                vQueryBuilder.setTables(ContactHelper.TABLE_NAME);
                vQueryBuilder.appendWhere(ContactHelper._ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase vDB = mDBHelper.getReadableDatabase();
        Cursor vCursor = vQueryBuilder.query(vDB, projection, selection, selectionArgs, null, null, sordOrder);
        vCursor.setNotificationUri(getContext().getContentResolver(), uri);


        return vCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        int vUriType = sURIMatcher.match(uri);
        SQLiteDatabase vDB = mDBHelper.getWritableDatabase();
        long vID = 0;

        switch (vUriType){
            case CONTACTS:
                vID = vDB.insert(ContactHelper.TABLE_NAME, null, contentValues);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        vDB.close();
        if (vID > 0){
            return Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH_CONTACTS + "/" + vID);
        } else {
            return null;
        }

    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int vUriType = sURIMatcher.match(uri);
        SQLiteDatabase vDB = mDBHelper.getWritableDatabase();

        int vRowsDeleted = 0;

        switch (vUriType){
            case CONTACTS:
                vRowsDeleted = vDB.delete(ContactHelper.TABLE_NAME, selection, selectionArgs);
                break;
            case CONTACTS_ID:
                String vID = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)){
                    vRowsDeleted = vDB.delete(ContactHelper.TABLE_NAME, ContactHelper._ID + "=" + vID, null);
                } else {
                    vRowsDeleted = vDB.delete(ContactHelper.TABLE_NAME, ContactHelper._ID + "=" + vID + " and " + selection, selectionArgs);
                }
                break;
            default:
                throw  new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return vRowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        int vUriType = sURIMatcher.match(uri);
        SQLiteDatabase vDB = mDBHelper.getWritableDatabase();
        int vRowsUpdated = 0;

        switch (vUriType){
            case CONTACTS:
                vRowsUpdated = vDB.update(ContactHelper.TABLE_NAME, contentValues, selection, selectionArgs);
                break;

            case CONTACTS_ID:
                String vID = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)){
                    vRowsUpdated = vDB.update(ContactHelper.TABLE_NAME, contentValues, ContactHelper._ID + "=" + vID, null);
                } else {
                    vRowsUpdated = vDB.update(ContactHelper.TABLE_NAME, contentValues, ContactHelper._ID + "=" + vID + " and " + selection, selectionArgs);
                }
                break;

            default:
                throw new IllegalArgumentException("Unknown Uri " + uri);

        }

        if (vRowsUpdated > 0)
            getContext().getContentResolver().notifyChange(uri, null);

        return vRowsUpdated;
    }
}
