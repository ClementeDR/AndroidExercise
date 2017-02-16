package com.clementedirosa.activeandroidapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.activeandroid.Cache;
import com.activeandroid.query.Select;
import com.clementedirosa.activeandroidapp.data.Anagrafica;
import com.clementedirosa.activeandroidapp.data.ContactHelper;


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
            mCapEditText.setText("" + savedInstanceState.getInt(ContactHelper.CAP));
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
                    Cache.openDatabase().delete(ContactHelper.TABLE_NAME, ContactHelper._ID + "=" + mItemID, null);
                }
                finish();
            }
        });

        mBtnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mIsReading){
                    Anagrafica vInfo = new Select().from(Anagrafica.class).where(ContactHelper._ID + "=" + mItemID).executeSingle();
                    vInfo.name = mNameEditText.getText().toString();
                    vInfo.cognome = mSurnameEditText.getText().toString();
                    vInfo.data_nascita =  mDateEditText.getText().toString();
                    vInfo.email = mEmailEditText.getText().toString();
                    vInfo.telefono = mTelefonoEditText.getText().toString();
                    vInfo.via = mViaEditText.getText().toString();
                    vInfo.citta = mCittaEditText.getText().toString();
                    vInfo.civico = mCivicoEditText.getText().toString();
                    vInfo.provincia = mProvinciaEditText.getText().toString();
                    vInfo.cap = Integer.parseInt(mCapEditText.getText().toString());
                    vInfo.lat = Float.parseFloat(mLatEditText.getText().toString());
                    vInfo.lng = Float.parseFloat(mLngEditText.getText().toString());
                    long test = vInfo.save();
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

        Anagrafica vData = new Select().from(Anagrafica.class).where(ContactHelper._ID + "=" + aItemID).executeSingle();
          if (vData != null){
            mNameEditText.setText(vData.name);
            mSurnameEditText.setText(vData.cognome);
            mDateEditText.setText(vData.data_nascita);
            mEmailEditText.setText(vData.email);
            mViaEditText.setText(vData.via);
            mTelefonoEditText.setText(vData.telefono);
            mCittaEditText.setText(vData.citta);
            mCivicoEditText.setText(vData.civico);
            mCapEditText.setText("" + vData.cap);
            mProvinciaEditText.setText(vData.provincia);
            mLatEditText.setText("" + vData.lat);
            mLngEditText.setText("" + vData.lng);
        }
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
