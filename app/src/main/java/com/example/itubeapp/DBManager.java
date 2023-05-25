package com.example.itubeapp;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBManager {
    public static final String DATABASE_NAME = "ITUBEAPP database";
    public static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_USER = "create table if not exists "+ UserTB.USER_TABLE +" ("+
            UserTB.USER_ID +" integer PRIMARY KEY AUTOINCREMENT NOT NULL," +
            UserTB.USER_FNAME + " VARCHAR, " +
            UserTB.USER_NAME + " VARCHAR DEFAULT 'momo', " +
            UserTB.USER_PWD +" VARCHAR NOT NULL);";


    public static final String CREATE_TABLE_PLAYLIST = "create table if not exists "+ PlaylistTB.PLAYLIST_TABLE +" ("+
            PlaylistTB.PLAYLIST_ID +" integer PRIMARY KEY AUTOINCREMENT NOT NULL," +
            PlaylistTB.PLAYLIST_USERNAME + " VARCHAR, " +
            PlaylistTB.PLAYLIST_VIDEOURL +" VARCHAR NOT NULL, FOREIGN KEY (" +
            PlaylistTB.PLAYLIST_USERNAME +") REFERENCES "+ UserTB.USER_TABLE + " (" + UserTB.USER_NAME +"));";

    private Context context;
    private DatabaseHelper mDBHelper;
    private SQLiteDatabase mDatabase;

    public DBManager(Context ctx)
    {
        this.context = ctx;
        this.mDBHelper = new DatabaseHelper(this.context);
    }

    public static class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION); }

        @Override
        public void onCreate(SQLiteDatabase db)
        {
            db.execSQL(CREATE_TABLE_USER);// create user table
            db.execSQL(CREATE_TABLE_PLAYLIST);// playlist table
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {        }
    }

    public void open() throws SQLException {
        this.mDatabase = this.mDBHelper.getWritableDatabase();
    }

    public void close()
    {
        this.mDBHelper.close();
    }


}

