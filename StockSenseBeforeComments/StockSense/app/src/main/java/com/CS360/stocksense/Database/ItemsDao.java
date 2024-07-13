package com.CS360.stocksense.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Delete;
import java.util.List;

@Dao
public interface ItemsDao {

    @Insert
    void insert(Items item);

    @Update
    void update(Items item);

    @Delete
    void delete(Items item);

    @Query("SELECT * FROM items")
    List<Items> getAllItems();

    @Query("SELECT * FROM items WHERE id = :id")
    Items getItemById(int id);

    @Query("SELECT (quantity < alertLevel) FROM items WHERE id = :itemId")
    boolean isLowInventory(int itemId);

    @Query("UPDATE items SET alertLevel = :alertLevel WHERE id = :id")
    void updateAlertLevel(int id, int alertLevel);
}
