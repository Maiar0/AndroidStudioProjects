package com.CS360.stocksense.Database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

@Database(entities = {User.class, Items.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    // Returns UserDao instance
    public abstract UserDao userDao();

    // Returns ItemsDao instance
    public abstract ItemsDao itemsDao();

    // Singleton pattern to get the database instance
    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "stock_sense_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
