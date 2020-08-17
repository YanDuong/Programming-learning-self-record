package com.example.freshfood.model.db;

import androidx.room.Database;

import com.example.freshfood.model.Food;
import com.example.freshfood.model.User;

import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.core.Observable;

public class AppDbHelper implements DBHelper {
    private AppDatabase appDb;
    public AppDbHelper(AppDatabase appDb) {
        this.appDb = appDb;
    }
    public Observable<Boolean> insertUser(final User user) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                appDb.userDao().insert(user);
                return true;
            }
        });
    }

    public Observable<Boolean> insertUserList(final List<User> users) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                appDb.userDao().insertAll(users);
                return true;
            }
        });
    }

    public Observable<User> getUser(long userId) {
        return appDb.userDao().getById(userId);
    }


    public Observable<List<User>> getUserList() {
        return appDb.userDao().getAll();
    }


    public Observable<Boolean> insertFood(final Food food) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                appDb.foodDao().insert(food);
                return true;
            }
        });
    }

    public Observable<Boolean> insertFoodList(final List<Food> foods) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                appDb.foodDao().insertAll(foods);
                return true;
            }
        });
    }

 public  Observable<Food> getFood(long foodId) {
        return appDb.foodDao().getById(foodId);
    }

    public Observable<List<Food>> getFoodList() {
        return appDb.foodDao().getAll();
    }
}
