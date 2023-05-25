package com.example.itubeapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PlaylistTB {
    public static final String PLAYLIST_ID = "_id"; // Primary key
    public static final String PLAYLIST_VIDEOURL = "videoUrl"; // video URL
    public static final String PLAYLIST_USERNAME = "username";
    public static final String PLAYLIST_TABLE = "playlist_table"; // table name

    private Context context;
    private DBManager.DatabaseHelper mHelper;
    private SQLiteDatabase mWdb;
    private SQLiteDatabase mRdb;

    public PlaylistTB(Context ctx){
        this.context = ctx;
    }

    public PlaylistTB open() throws SQLException {
        mHelper = new DBManager.DatabaseHelper(context);
        mWdb = mHelper.getWritableDatabase();
        mRdb = mHelper.getReadableDatabase();
        return this;
    }

    // Insert into the playlist.
    public long insertIntoPlaylist(Playlist playlist){
        ContentValues values = new ContentValues();
        values.put(PLAYLIST_USERNAME, playlist.getUsername());
        values.put(PLAYLIST_VIDEOURL, playlist.getVideoUrl());
        return mWdb.insert(PLAYLIST_TABLE, null, values);
    }

    @SuppressLint("Range")
    public List<Playlist> selectByUser(String username) {
        Cursor cursor = mRdb.query(PLAYLIST_TABLE, null, PLAYLIST_USERNAME + "=?", new String[]{username}, null, null, null);
        List<Playlist> playlistList = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String videoUrl = cursor.getString(cursor.getColumnIndex(PLAYLIST_VIDEOURL));
                Playlist playlist = new Playlist();
                playlist.setUsername(username);
                playlist.setVideoUrl(videoUrl);
                playlistList.add(playlist);
            }
            cursor.close();
        }
        return playlistList;
    }
}

