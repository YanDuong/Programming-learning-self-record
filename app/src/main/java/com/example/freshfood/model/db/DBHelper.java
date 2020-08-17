package com.example.freshfood.model.db;

import com.example.freshfood.model.Food;
import com.example.freshfood.model.User;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface DBHelper {
    Observable<Boolean> insertUser(User user);
    Observable<Boolean> insertUserList(List<User> users);
    Observable<User> getUser(long userId);
    Observable<List<User>> getUserList();

    Observable<Boolean> insertFood(Food food);
    Observable<Boolean> insertFoodList(List<Food> foods);
    Observable<Food> getFood(long foodId);
    Observable<List<Food>> getFoodList();


}
