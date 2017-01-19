package com.clementedirosa.contactdetail.data;

import android.provider.BaseColumns;

/**
 * Created by clementedirosa on 19/01/2017.
 */

public class ContactHelper implements BaseColumns {
    public static final String TABLE_NAME = "Anagrafica";
    public static final String NOME = "nome";
    public static final String COGNOME = "cognome";
    public static final String DATA_NASCITA = "dataNascita";
    public static final String EMAIL = "email";
    public static final String TELEFONO = "telefono";
    public static final String VIA = "via";
    public static final String CIVICO = "civico";
    public static final String CITTA = "citta";
    public static final String CAP = "cap";
    public static final String PROVINCIA = "provincia";
    public static final String LAT = "lat";
    public static final String LNG = "long";

    public static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME +
            "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            NOME + " TEXT NOT NULL, " +
            COGNOME + " TEXT NOT NULL, " +
            DATA_NASCITA + " TEXT, " +
            EMAIL + " TEXT, " +
            TELEFONO + " TEXT, " +
            VIA + " TEXT, " +
            CIVICO + " TEXT, " +
            CITTA + " TEXT, " +
            CAP + " NUMBER, " +
            PROVINCIA + " TEXT, " +
            LAT + " REAL, " +
            LNG + " REAL " +
            ");";

    public static final String DROP_QUERY = "DROP TABLE " + TABLE_NAME;


}
