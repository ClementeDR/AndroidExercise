package com.example.cdr.contentprovider.data;

/**
 * Created by cdr on 14/12/16.
 */

public class Contact {
    private long mID;
    private String mName;
    private String mSurname;

    public Contact(){

    }

    public Contact(long aID, String aName, String aSurname){
        mID = aID;
        mName = aName;
        mSurname = aSurname;
    }

    public long getID() {
        return mID;
    }

    public void setID(long ID) {
        this.mID = ID;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public String getSurname() {
        return mSurname;
    }

    public void setSurname(String surname) {
        this.mSurname = surname;
    }

}
