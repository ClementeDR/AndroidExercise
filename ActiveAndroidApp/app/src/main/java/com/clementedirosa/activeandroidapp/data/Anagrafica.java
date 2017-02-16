package com.clementedirosa.activeandroidapp.data;

import android.provider.BaseColumns;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by clementedirosa on 15/02/2017.
 */

@Table(name = ContactHelper.TABLE_NAME, id = BaseColumns._ID)
public class Anagrafica extends Model {
    @Column(name = ContactHelper.NOME)
    public String name;

    @Column(name = ContactHelper.COGNOME)
    public String cognome;

    @Column(name = ContactHelper.DATA_NASCITA)
    public String data_nascita;

    @Column(name = ContactHelper.EMAIL)
    public String email;

    @Column(name = ContactHelper.TELEFONO)
    public String telefono;

    @Column(name = ContactHelper.VIA)
    public String via;

    @Column(name = ContactHelper.CIVICO)
    public String civico;

    @Column(name = ContactHelper.CITTA)
    public String citta;

    @Column(name = ContactHelper.CAP)
    public int cap;

    @Column(name = ContactHelper.PROVINCIA)
    public String provincia;

    @Column(name = ContactHelper.LAT)
    public float lat;

    @Column(name = ContactHelper.LNG)
    public float lng;

    public Anagrafica(){
        super();
    }


}
