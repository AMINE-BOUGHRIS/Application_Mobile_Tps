package com.example.applicationmobiletps;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class MySQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "names.db"; // Use .db extension
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS Students (_id INTEGER PRIMARY KEY AUTOINCREMENT, FirstName VARCHAR, LastName VARCHAR)"; // Add ID column

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Handle database upgrades if needed
    }
}
