package com.cs360.projectone;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class InventoryDatabaseHelper {
    private static final String TABLE_ITEMS = "items";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_QUANTITY = "quantity";
    private static final String COLUMN_LOCATION = "location";

    public static void onCreate(SQLiteDatabase db) {
        String CREATE_ITEMS_TABLE = "CREATE TABLE " + TABLE_ITEMS + " ("
                + COLUMN_ID + " TEXT PRIMARY KEY, "
                + COLUMN_NAME + " TEXT, "
                + COLUMN_QUANTITY + " INTEGER, "
                + COLUMN_LOCATION + " TEXT)";
        db.execSQL(CREATE_ITEMS_TABLE);
    }

    public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(db);
    }

    public static List<Items> getAllItems(SQLiteDatabase db) {
        List<Items> itemList = new ArrayList<>();
        Cursor cursor = db.query(TABLE_ITEMS, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_QUANTITY, COLUMN_LOCATION},
                null, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                Items item = new Items(
                        cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                        cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY)),
                        cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION))
                );
                itemList.add(item);
            }
            cursor.close();
        }
        return itemList;
    }

    public static Items getItemById(SQLiteDatabase db, String id) {
        Cursor cursor = db.query(TABLE_ITEMS, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_QUANTITY, COLUMN_LOCATION},
                COLUMN_ID + "=?", new String[]{id}, null, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            Items item = new Items(
                    cursor.getString(cursor.getColumnIndex(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTITY)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION))
            );
            cursor.close();
            return item;
        } else {
            return null;
        }
    }

    public static boolean updateItem(SQLiteDatabase db, Items item) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, item.getItemName());
        values.put(COLUMN_QUANTITY, item.getQuantity());
        values.put(COLUMN_LOCATION, item.getLocation());
        int rowsAffected = db.update(TABLE_ITEMS, values, COLUMN_ID + " = ?", new String[]{item.getId()});
        return rowsAffected > 0;
    }

    public static boolean deleteItem(SQLiteDatabase db, String id) {
        int rowsAffected = db.delete(TABLE_ITEMS, COLUMN_ID + " = ?", new String[]{id});
        return rowsAffected > 0;
    }
}
