package com.example.cruid_sqllite;

import static com.example.cruid_sqllite.DatabaseHandler.DATABASE_NAME;
import static com.example.cruid_sqllite.DatabaseHandler.DATABASE_VERSION;
import static com.example.cruid_sqllite.DatabaseHandler.KEY_ID;
import static com.example.cruid_sqllite.DatabaseHandler.KEY_NAME;
import static com.example.cruid_sqllite.DatabaseHandler.KEY_NUMBER;
import static com.example.cruid_sqllite.DatabaseHandler.TABLE_NAME;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class ContactBDD {

    private SQLiteDatabase mDb;
    private DatabaseHandler DBcontacts;

    public ContactBDD(Context context){
        DBcontacts = new DatabaseHandler(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public SQLiteDatabase openEcriture(){
        mDb = DBcontacts.getWritableDatabase();
        return  mDb;
    }

    public SQLiteDatabase openLecture(){
        mDb = DBcontacts.getReadableDatabase();
        return  mDb;
    }

    public long addContact(Contact c){
        mDb=this.openEcriture();
        ContentValues v = new ContentValues();
        v.put(KEY_NAME, c.getNom());
        v.put(KEY_NUMBER, c.getNumber());
        long i = mDb.insert(TABLE_NAME, null, v);
        return i;
    }

    public Contact searchContact(int id){
        String Query = "SELECT * FROM "+ TABLE_NAME+ " where id=?";
        mDb = this.openLecture();
        Cursor curseur = mDb.rawQuery(Query, new String[] {String.valueOf(id)});
        if (curseur.getCount() == 0){
            return null;
        }
        Contact oc=new Contact();
        if(curseur.moveToFirst()){
            oc.setNom(curseur.getString(1));
            oc.setNumber(curseur.getString(2));
        }
        curseur.close();
        mDb.close();
        return oc;
    }

    public List<Contact> getAllContact(){
        List <Contact> contactList = new ArrayList<>();
        mDb=this.DBcontacts.getReadableDatabase();
        String Query = "SELECT * FROM "+ TABLE_NAME;
        Cursor c = mDb.rawQuery(Query, null );
        if(c.moveToFirst())
            do
            {
                Contact oc = new Contact();
                oc.setId(c.getInt(0));
                oc.setNom(c.getString(1));
                oc.setNumber(c.getString(2));
                contactList.add(oc);
            }while (c.moveToNext());
        c.close();
        mDb.close();
        return  contactList;

    }

    public int updateContact(Contact c){
        mDb=this.openEcriture();
        ContentValues v = new ContentValues();
        v.put(KEY_NAME, c.getNom());
        v.put(KEY_NUMBER, c.getNumber());
        return mDb.update(TABLE_NAME, v, KEY_ID + "=?", new String[]{String.valueOf(c.getId())});

    }

    public void deleteContact(Contact c){
        mDb=this.openEcriture();
        mDb.delete(TABLE_NAME, KEY_ID+ "=?", new String[]{String.valueOf(c.getId())});
        mDb.close();
    }
}
