package com.clementedirosa.weathercontentprovider.data;

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

/**
 * Created by clementedirosa on 13/01/2017.
 */

public class MyContentProvider extends ContentProvider{
    public static final String CITY_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/citys";
    public static final String CITY_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/city";
    public static final String TEMPERATURE_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/temperatures";
    public static final String TEMPERATURE_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/temperature";

    private static final String AUTHORITY = "com.clementedirosa.weathercontentprovider.data.contentprovider";
    private static final String CITY_BASE_PATH = "citys";
    private static final String TEMPERATURE_BASE_PATH = "temperatures";

    public static final Uri CITY_URI = Uri.parse("content://" + AUTHORITY + "/" + CITY_BASE_PATH);
    public static final Uri TEMPERATURE_URI = Uri.parse("content://" + AUTHORITY + "/" + TEMPERATURE_BASE_PATH);

    private static final int CITY = 10;
    private static final int CITY_ID = 11;
    private static final int TEMPERATURE = 20;
    private static final int TEMPERATURE_ID = 21;

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, CITY_BASE_PATH, CITY);
        sURIMatcher.addURI(AUTHORITY, CITY_BASE_PATH + "/#", CITY_ID);
        sURIMatcher.addURI(AUTHORITY, TEMPERATURE_BASE_PATH, TEMPERATURE);
        sURIMatcher.addURI(AUTHORITY, TEMPERATURE_BASE_PATH + "/#", TEMPERATURE_ID);
//        sURIMatcher.addURI(AUTHORITY, TEMPERATURE_BASE_PATH + "/" + TEMP_BY_CITY_PATH + "/#", TEMPERATURE_BY_CITY);
    }

    DBHelper mDBHelper;


    @Override
    public boolean onCreate() {
        mDBHelper = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder vQueryBuilder = new SQLiteQueryBuilder();

        int vUriType = sURIMatcher.match(uri);
        switch (vUriType){
            case CITY:
                vQueryBuilder.setTables(CityHelper.TABLE_NAME);
                break;

            case CITY_ID:
                vQueryBuilder.setTables(CityHelper.TABLE_NAME);
                vQueryBuilder.appendWhere(CityHelper._ID + "=" + uri.getLastPathSegment());
                break;

            case TEMPERATURE:
                vQueryBuilder.setTables(TemperatureHelper.TABLE_NAME);
                if (!TextUtils.isEmpty(selection)){
                    vQueryBuilder.appendWhere(selection);
                }
                break;

            case TEMPERATURE_ID:
                vQueryBuilder.setTables(TemperatureHelper.TABLE_NAME);
                vQueryBuilder.appendWhere(TemperatureHelper._ID + "=" + uri.getLastPathSegment());
                break;

            /*case TEMPERATURE_BY_CITY:
                vQueryBuilder.setTables(TemperatureHelper.TABLE_NAME);
                vQueryBuilder.appendWhere(TemperatureHelper.CITY_ID + "=" + uri.getLastPathSegment());
                break;*/
            default:
                throw new IllegalArgumentException();
        }


        SQLiteDatabase vDB = mDBHelper.getReadableDatabase();
        Cursor vCursor = vQueryBuilder.query(vDB, projection, selection, selectionArgs, null, null, sortOrder);
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
    public Uri insert(Uri uri, ContentValues values) {
        int vUriType = sURIMatcher.match(uri);
        SQLiteDatabase vDB = mDBHelper.getWritableDatabase();

        long vID = 0;
        Uri vUri;

        switch (vUriType){
            case CITY:
                vID = vDB.insert(CityHelper.TABLE_NAME, null, values);
                vUri = Uri.parse("content://" + AUTHORITY + "/" + CITY_BASE_PATH + "/" + vID);
                break;

            case TEMPERATURE:
                vID = vDB.insert(TemperatureHelper.TABLE_NAME, null, values);
                vUri = Uri.parse("content://" + AUTHORITY + "/" + TEMPERATURE_BASE_PATH + "/" + vID);
                break;

            default:
                throw new IllegalArgumentException();
        }

        getContext().getContentResolver().notifyChange(uri, null);
        vDB.close();
        if (vID > 0) {
            return vUri;
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
            case CITY:
                vRowsDeleted = vDB.delete(CityHelper.TABLE_NAME, selection, selectionArgs);
                break;

            case CITY_ID:
                String vCID = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)){
                    vRowsDeleted = vDB.delete(CityHelper.TABLE_NAME, CityHelper._ID + "=" + vCID, null);
                } else {
                    vRowsDeleted = vDB.delete(CityHelper.TABLE_NAME, CityHelper._ID + "=" + vCID + " and " + selection, selectionArgs);
                }
                break;

            case TEMPERATURE:
                vRowsDeleted = vDB.delete(TemperatureHelper.TABLE_NAME, selection, selectionArgs);
                break;

            case TEMPERATURE_ID:
                String vTID = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)){
                    vRowsDeleted = vDB.delete(TemperatureHelper.TABLE_NAME, TemperatureHelper._ID + "=" + vTID, null);
                } else {
                    vRowsDeleted = vDB.delete(TemperatureHelper.TABLE_NAME, TemperatureHelper._ID + "=" + vTID + " and " + selection, selectionArgs);
                }
                break;

            default:
                throw  new IllegalArgumentException("Unknown URI " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return vRowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int vUriType = sURIMatcher.match(uri);
        SQLiteDatabase vDB = mDBHelper.getWritableDatabase();
        int vRowsUpdated = 0;

        switch (vUriType){
            case CITY:
                vRowsUpdated = vDB.update(CityHelper.TABLE_NAME, values, selection, selectionArgs);
                break;

            case CITY_ID:
                String vCID = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)){
                    vRowsUpdated = vDB.update(CityHelper.TABLE_NAME, values, CityHelper._ID + "=" + vCID, null);
                } else {
                    vRowsUpdated = vDB.update(CityHelper.TABLE_NAME, values, CityHelper._ID + "=" + vCID + " and " + selection, selectionArgs);
                }
                break;

            case TEMPERATURE:
                vRowsUpdated = vDB.update(TemperatureHelper.TABLE_NAME, values, selection, selectionArgs);
                break;

            case TEMPERATURE_ID:
                String vTID = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)){
                    vRowsUpdated = vDB.update(TemperatureHelper.TABLE_NAME, values, TemperatureHelper._ID + "=" + vTID, null);
                } else {
                    vRowsUpdated = vDB.update(TemperatureHelper.TABLE_NAME, values, TemperatureHelper._ID + "=" + vTID + " and " + selection, selectionArgs);
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
