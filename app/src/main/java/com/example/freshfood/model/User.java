package com.example.freshfood.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

import com.google.gson.annotations.Expose;
@Entity(tableName="user")
public class User {


    @ColumnInfo(name="user_id")
    private long userId;

    @Expose
    @ColumnInfo(name="account")
    private Account account;

    @Expose
    @ColumnInfo(name="user_name")
    private String userName;

    @Expose
    @ColumnInfo(name="email")
    private String email;

    @Expose
    @ColumnInfo(name="location")
    private String location;

    @Expose
    @ColumnInfo(name="created_at")
    private String createdAt;

}
