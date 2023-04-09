package com.example.cruid_sqllite;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="DBContactFavoris";
    public static final String TABLE_NAME="contact";
    public static final String KEY_ID="id";
    public static final String KEY_NAME="nom";
    public static final String KEY_NUMBER="number";


    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME+ "("+KEY_ID+" "+
                " INTEGER PRIMARY KEY autoincrement, "+ KEY_NAME+ " TEXT," + KEY_NUMBER+" TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+ TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
