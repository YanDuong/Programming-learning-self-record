package com.example.freshfood.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.freshfood.model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;


@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<User> users);

    @Query("SELECT * FROM user WHERE user_id = :userId")
    Observable<User> getById(long userId);

    @Query("SELECT * FROM user")
    Observable<List<User>> getAll();

}
