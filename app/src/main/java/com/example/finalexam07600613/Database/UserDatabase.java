package com.example.finalexam07600613.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserDatabaseEntity.class},exportSchema = false,version = 1)
public abstract class UserDatabase extends RoomDatabase {
    private static String DB_NAME = "user.db";
    private static UserDatabase instance;

    public abstract UserDatabaseDao UserDatabaseDao();

    public static synchronized UserDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room
                    .databaseBuilder(
                            context.getApplicationContext(),
                            UserDatabase.class,
                            DB_NAME
                    ).build();
        }
        return instance;
    }
}
