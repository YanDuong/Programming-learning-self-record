package com.example.freshfood.model.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.freshfood.model.Account;
import com.example.freshfood.model.Food;
import com.example.freshfood.model.Store;
import com.example.freshfood.model.User;
import com.example.freshfood.model.dao.FoodDao;
import com.example.freshfood.model.dao.UserDao;

@Database(entities={Food.class, User.class, Store.class, Account.class }, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    abstract FoodDao foodDao();
    abstract UserDao userDao();

}
