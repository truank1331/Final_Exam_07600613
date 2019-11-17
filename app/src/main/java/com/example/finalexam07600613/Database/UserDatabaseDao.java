package com.example.finalexam07600613.Database;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDatabaseDao {
    @Query("SELECT * FROM user")
    List<UserDatabaseEntity> getAll();

    @Insert
    void insert(UserDatabaseEntity entity);
}
