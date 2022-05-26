package com.example.musicplayer.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;
    public static final String CREATE_USER = "create table Users ("
            + "id integer primary key autoincrement, "
            + "user text, "
            + "password text, "
            + "email text, "
            + "phone number)";

    public UserDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
        Toast.makeText(mContext, "DATABASE SUCCESS", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
