package com.example.itubeapp;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class UserTB {
    public static final String USER_ID = "_id";             //主键
    public static final String USER_FNAME = "fullname";
    public static final String USER_PWD = "password";       //密码
    public static final String USER_NAME = "username";      //用户名
    public static final String USER_TABLE = "user_info";    //表名


    private Context context;
    private DBManager.DatabaseHelper mHelper;
    private SQLiteDatabase mWdb;
    private SQLiteDatabase mRdb;

    //insert，which is signup
    public long insert(User user){
        //SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_NAME,user.getUsername());
        values.put(USER_FNAME,user.getFullname());
        values.put(USER_PWD,user.getPassword());
        return mWdb.insert(USER_TABLE,null,values);
    }

    public UserTB(Context ctx){
        this.context = ctx;
    }

    public UserTB open() throws SQLException {
        mHelper = new DBManager.DatabaseHelper(context);
        mWdb = mHelper.getWritableDatabase();
        mRdb = mHelper.getReadableDatabase();
        return this;
    }

    @SuppressLint("Range")
    public List<User> selectByAccount(String account) {
        //SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = mRdb.query(USER_TABLE, null, USER_NAME + "=?", new String[]{account}, null, null, null);
        List<User> userList = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String account1 = cursor.getString(cursor.getColumnIndex(USER_NAME));
                String password = cursor.getString(cursor.getColumnIndex(USER_PWD));
                String fullname = cursor.getString(cursor.getColumnIndex(USER_FNAME));
                User user = new User();
                user.setUsername(account1);
                user.setPassword(password);
                user.setFullname(fullname);
                userList.add(user);
            }
            return userList;
        }
        return null;
    }
}
