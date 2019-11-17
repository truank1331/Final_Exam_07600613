package com.example.finalexam07600613.Database;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "user")
public class UserDatabaseEntity {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    @SerializedName("username")
    public String username;

    @ColumnInfo(name = "fullname")
    @SerializedName("fullname")
    public String fullname;

    @ColumnInfo(name = "password")
    @SerializedName("password")
    public String password;

    public UserDatabaseEntity(String username,String fullname,String password){
        this.username = username;
        this.fullname = fullname;
        this.password = password;
    }
}
