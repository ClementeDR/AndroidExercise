package com.clementedirosa.contactdetail;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.clementedirosa.contactdetail.data.ContactHelper;
import com.clementedirosa.contactdetail.data.MyContentProvider;


public class DetailActivity extends AppCompatActivity {
    private static final String READING = "READING";
    private static final String SAVE = "SALVA";
    private static final String MODIFICA = "MODIFICA";
    private static final String ANNULLA = "ANNULLA";
    private static final String DELETE = "ELIMINA";
    private boolean mIsReading = true;

    private ImageButton mImageButton;
    private EditText mNameEditText;
    private EditText mSurnameEditText;
    private EditText mDateEditText;
    private EditText mEmailEditText;
    private EditText mTelefonoEditText;
    private EditText mViaEditText;
    private EditText mCivicoEditText;
    private EditText mCittaEditText;
    private EditText mCapEditText;
    private EditText mProvinciaEditText;
    private EditText mLatEditText;
    private EditText mLngEditText;
    private Button mBtnModify;
    private Button mBtnDelete;

    private long mItemID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState != null){
            mIsReading = savedInstanceState.getBoolean(READING);
        } else {
            mIsReading = true;
        }
        init();

        Bundle vBundle = getIntent().getExtras();
        mItemID = vBundle.getLong(MainActivity.ITEM_ID);
        Log.d("MITEM", " " + mItemID);

        if (savedInstanceState != null){
            mNameEditText.setText(savedInstanceState.getString(ContactHelper.NOME));
            mSurnameEditText.setText(savedInstanceState.getString(ContactHelper.COGNOME));
            mDateEditText.setText(savedInstanceState.getString(ContactHelper.DATA_NASCITA));
            mEmailEditText.setText(savedInstanceState.getString(ContactHelper.EMAIL));
            mViaEditText.setText(savedInstanceState.getString(ContactHelper.VIA));
            mTelefonoEditText.setText(savedInstanceState.getString(ContactHelper.TELEFONO));
            mCittaEditText.setText(savedInstanceState.getString(ContactHelper.CITTA));
            mCivicoEditText.setText(savedInstanceState.getString(ContactHelper.CIVICO));
            mCapEditText.setText(savedInstanceState.getInt(ContactHelper.CAP));
            mProvinciaEditText.setText(savedInstanceState.getString(ContactHelper.PROVINCIA));
            mLatEditText.setText("" + savedInstanceState.getFloat(ContactHelper.LAT));
            mLngEditText.setText("" + savedInstanceState.getFloat(ContactHelper.LNG));
        } else {
            setUpData(mItemID);
        }

        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIsReading){
                    Uri vUriToDelete = Uri.parse(MyContentProvider.CONTACTS_URI + "/" + mItemID);
                    getContentResolver().delete(vUriToDelete, null, null);
                }
                finish();
            }
        });

        mBtnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsReading){

                    ContentValues vValues = new ContentValues();
                    vValues.put(ContactHelper.NOME, mNameEditText.getText().toString());
                    vValues.put(ContactHelper.COGNOME, mSurnameEditText.getText().toString());
                    vValues.put(ContactHelper.DATA_NASCITA, mDateEditText.getText().toString());
                    vValues.put(ContactHelper.EMAIL, mEmailEditText.getText().toString());
                    vValues.put(ContactHelper.TELEFONO, mTelefonoEditText.getText().toString());
                    vValues.put(ContactHelper.VIA, mViaEditText.getText().toString());
                    vValues.put(ContactHelper.CITTA, mCittaEditText.getText().toString());
                    vValues.put(ContactHelper.CIVICO, mCivicoEditText.getText().toString());
                    vValues.put(ContactHelper.PROVINCIA, mProvinciaEditText.getText().toString());
                    vValues.put(ContactHelper.CAP, Integer.parseInt(mCapEditText.getText().toString()));
                    vValues.put(ContactHelper.LAT, Float.parseFloat(mLatEditText.getText().toString()));
                    vValues.put(ContactHelper.LNG, Float.parseFloat(mLngEditText.getText().toString()));
                    Uri vUriToUpdate = Uri.parse(MyContentProvider.CONTACTS_URI + "/" + mItemID);
                    int test = getContentResolver().update(vUriToUpdate, vValues,null,null);
                    Log.d("TEMP", "onClick: " + test);
                }
                mIsReading = !mIsReading;
                modify();
            }
        });

    }

    private void modify() {
        mNameEditText.setEnabled(!mIsReading);
        mSurnameEditText.setEnabled(!mIsReading);
        mDateEditText.setEnabled(!mIsReading);
        mEmailEditText.setEnabled(!mIsReading);
        mTelefonoEditText.setEnabled(!mIsReading);
        mViaEditText.setEnabled(!mIsReading);
        mCittaEditText.setEnabled(!mIsReading);
        mCivicoEditText.setEnabled(!mIsReading);
        mCapEditText.setEnabled(!mIsReading);
        mProvinciaEditText.setEnabled(!mIsReading);
        mLatEditText.setEnabled(!mIsReading);
        mLngEditText.setEnabled(!mIsReading);

        if(mIsReading){
            mBtnModify.setText(MODIFICA);
            mBtnDelete.setText(DELETE);
        } else {
            mBtnModify.setText(SAVE);
            mBtnDelete.setText(ANNULLA);
        }
    }

    private void setUpData(long aItemID) {
        Uri vUriQuery = Uri.parse(MyContentProvider.CONTACTS_URI + "/" + aItemID);
        Cursor vCursor = getContentResolver().query(vUriQuery,null,null,null,null);
        vCursor.moveToFirst();
        if (vCursor != null){
            mNameEditText.setText(vCursor.getString(vCursor.getColumnIndex(ContactHelper.NOME)));
            mSurnameEditText.setText(vCursor.getString(vCursor.getColumnIndex(ContactHelper.COGNOME)));
            mDateEditText.setText(vCursor.getString(vCursor.getColumnIndex(ContactHelper.DATA_NASCITA)));
            mEmailEditText.setText(vCursor.getString(vCursor.getColumnIndex(ContactHelper.EMAIL)));
            mViaEditText.setText(vCursor.getString(vCursor.getColumnIndex(ContactHelper.VIA)));
            mTelefonoEditText.setText(vCursor.getString(vCursor.getColumnIndex(ContactHelper.TELEFONO)));
            mCittaEditText.setText(vCursor.getString(vCursor.getColumnIndex(ContactHelper.CITTA)));
            mCivicoEditText.setText(vCursor.getString(vCursor.getColumnIndex(ContactHelper.CIVICO)));
            mCapEditText.setText("" + vCursor.getInt(vCursor.getColumnIndex(ContactHelper.CAP)));
            mProvinciaEditText.setText(vCursor.getString(vCursor.getColumnIndex(ContactHelper.PROVINCIA)));
            mLatEditText.setText("" + vCursor.getFloat(vCursor.getColumnIndex(ContactHelper.LAT)));
            mLngEditText.setText("" + vCursor.getFloat(vCursor.getColumnIndex(ContactHelper.LNG)));
        }
        vCursor.close();
    }

    private void init(){
        mImageButton = (ImageButton) findViewById(R.id.imageButton);
        mNameEditText = (EditText) findViewById(R.id.nomeEdit);
        mSurnameEditText = (EditText) findViewById(R.id.cognomeEdit);
        mDateEditText = (EditText) findViewById(R.id.dateEdit);
        mEmailEditText = (EditText) findViewById(R.id.emailEdit);
        mTelefonoEditText = (EditText) findViewById(R.id.phoneEdit);
        mViaEditText = (EditText) findViewById(R.id.viaEdit);
        mCittaEditText = (EditText) findViewById(R.id.cityEdit);
        mCivicoEditText = (EditText) findViewById(R.id.civicoEdit);
        mCapEditText = (EditText) findViewById(R.id.capEdit);
        mProvinciaEditText = (EditText) findViewById(R.id.provinciaEdit);
        mLatEditText = (EditText) findViewById(R.id.latEdit);
        mLngEditText = (EditText) findViewById(R.id.lngEdit);
        mBtnModify = (Button) findViewById(R.id.btnModify);
        mBtnDelete = (Button) findViewById(R.id.btnDelete);

        modify();

        mImageButton.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(ContactHelper.NOME, mNameEditText.getText().toString());
        outState.putString(ContactHelper.COGNOME, mSurnameEditText.getText().toString());
        outState.putString(ContactHelper.DATA_NASCITA, mDateEditText.getText().toString());
        outState.putString(ContactHelper.EMAIL, mEmailEditText.getText().toString());
        outState.putString(ContactHelper.TELEFONO, mTelefonoEditText.getText().toString());
        outState.putString(ContactHelper.VIA, mViaEditText.getText().toString());
        outState.putString(ContactHelper.CITTA, mCittaEditText.getText().toString());
        outState.putString(ContactHelper.CIVICO, mCivicoEditText.getText().toString());
        outState.putString(ContactHelper.PROVINCIA, mProvinciaEditText.getText().toString());
        outState.putInt(ContactHelper.CAP, Integer.parseInt(mCapEditText.getText().toString()));
        outState.putFloat(ContactHelper.LAT, Float.parseFloat(mLatEditText.getText().toString()));
        outState.putFloat(ContactHelper.LNG, Float.parseFloat(mLngEditText.getText().toString()));
        outState.putBoolean(READING, mIsReading);
    }
}
