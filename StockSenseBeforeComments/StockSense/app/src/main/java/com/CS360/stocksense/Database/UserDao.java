package com.CS360.stocksense.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    User getUser(String username, String password);

    @Query("SELECT * FROM users")
    List<User> getAllUsers();
    @Update
    void updateUser(User user);
}
