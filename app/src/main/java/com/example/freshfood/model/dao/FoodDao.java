package com.example.freshfood.model.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.freshfood.model.Food;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;


@Dao
public interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Food food);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Food> foodList);

   @Query("SELECT * FROM food WHERE food_id = :foodId")
   Observable<Food> getById(long foodId);

   @Query("SELECT * FROM food")
    Observable<List<Food>> getAll();




}
