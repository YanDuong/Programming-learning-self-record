package com.example.freshfood.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName="account")
public class Account {

    @ColumnInfo(name="account_id")
    @PrimaryKey
    private long accountId;

    @ColumnInfo(name="phone_number")
    private String phoneNumber;

    @ColumnInfo(name="password")
    private String password;

    @ColumnInfo(name="role")
    private int role;
}
