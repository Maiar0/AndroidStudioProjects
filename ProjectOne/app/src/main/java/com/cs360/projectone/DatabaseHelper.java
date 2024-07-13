package com.cs360.projectone;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String DATABASE_NAME = "warehouse.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        UserDatabaseHelper.onCreate(db);
        InventoryDatabaseHelper.onCreate(db);
        populateInitialData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        UserDatabaseHelper.onUpgrade(db, oldVersion, newVersion);
        InventoryDatabaseHelper.onUpgrade(db, oldVersion, newVersion);
        onCreate(db);
    }

    private void populateInitialData(SQLiteDatabase db) {
        // Insert default users
        db.execSQL("INSERT INTO users (id, username, password) VALUES ('1', 'user1', 'password1')");
        db.execSQL("INSERT INTO users (id, username, password) VALUES ('2', 'user2', 'password2')");

        // Insert default inventory items
        db.execSQL("INSERT INTO items (id, name, quantity, location) VALUES ('001', 'Drills', 12, 'A3B')");
        db.execSQL("INSERT INTO items (id, name, quantity, location) VALUES ('002', 'Glue', 32, 'A2A')");
        db.execSQL("INSERT INTO items (id, name, quantity, location) VALUES ('003', 'Hammers', 12, 'A4C')");
        db.execSQL("INSERT INTO items (id, name, quantity, location) VALUES ('004', 'Nails', 2500000, 'A5D')");
        db.execSQL("INSERT INTO items (id, name, quantity, location) VALUES ('005', 'Screws', 2500000, 'A1E')");
        db.execSQL("INSERT INTO items (id, name, quantity, location) VALUES ('006', 'Tape', 25, 'A6F')");
    }
}
