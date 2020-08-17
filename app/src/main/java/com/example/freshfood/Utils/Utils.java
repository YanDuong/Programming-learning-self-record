package com.example.freshfood.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.freshfood.R;
import com.example.freshfood.model.Food;
import com.example.freshfood.model.FoodType;
import com.example.freshfood.model.Store;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static boolean isFoodDiscount(String discountString) {
        int discountValue = 0;
        int length = discountString.length();
        if (discountString== null) return false;
        if(length != 2 && length != 3) return false;
        if (length == 2) {
                discountValue = Integer.parseInt(discountString.substring(0, 1));
        }
        if (length == 3) {
            discountValue = Integer.parseInt(discountString.substring(0, 2));
        }
        return discountValue > 0;
    }

    public static Bitmap resizeImageFromResource(Context context, int resId, int newWidth, int newHeight) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true);
    }


    public static void populateItems() {
        Store storeA = new Store(1, "Store A", "Nguyen The A", "200 Nguyen Trai Thanh Xuan Ha Noi");
        Store storeB = new Store(2, "Store B", "Nguyen The b", "201 Nguyen Trai Thanh Xuan Ha Noi");
        Store storeC = new Store(3, "Store C", "Nguyen The C", "202 Nguyen Trai Thanh Xuan Ha Noi");
        Store storeD = new Store(4, "Store D", "Nguyen The D", "203 Nguyen Trai Thanh Xuan Ha Noi");
        List<Store> stores = new ArrayList<>();

        storeA.insertNewFood(new Food(storeA, "Peas", "5 kg", FoodType.VEGETABLE, 100, R.drawable.peas, true, "12%"));
        storeA.insertNewFood(new Food(storeA, "Apple", "5kg", FoodType.FRUIT, 50, R.drawable.apple, false, "10%"));
        storeA.insertNewFood(new Food(storeA, "Crab", "5kg", FoodType.SEAFOOD, 50, R.drawable.crab, false, "15%"));
        storeA.insertNewFood(new Food(storeA, "Tomato", "10kg", FoodType.FRUIT, 50, R.drawable.tomato, true, null));

        storeB.insertNewFood(new Food(storeB, "Salmon", "10kg", FoodType.SEAFOOD, 50, R.drawable.salmon, true, "35%"));
        storeB.insertNewFood(new Food(storeB, "Carrot", "5 kg", FoodType.VEGETABLE, 100, R.drawable.carrot, false, "20%"));
        storeB.insertNewFood(new Food(storeB, "Crayfish", "10kg", FoodType.SEAFOOD, 50, R.drawable.crayfish, true, null));

        storeC.insertNewFood(new Food(storeC, "Orange", "10kg", FoodType.FRUIT, 50, R.drawable.apple, true, "30%"));
        storeC.insertNewFood(new Food(storeC, "Orange", "10kg", FoodType.FRUIT, 50, R.drawable.apple, true, "30%"));
        storeC.insertNewFood(new Food(storeC, "Mango", "10kg", FoodType.FRUIT, 50, R.drawable.mango, true, "25%"));
        storeC.insertNewFood(new Food(storeC, "Prawn", "10kg", FoodType.SEAFOOD, 50, R.drawable.prawn, true, "25%"));

        storeD.insertNewFood(new Food(storeC, "Orange", "10kg", FoodType.FRUIT, 50, R.drawable.apple, true, "30%"));
        storeD.insertNewFood(new Food(storeD, "Squid", "10kg", FoodType.SEAFOOD, 50, R.drawable.squid, true, null));
        storeD.insertNewFood(new Food(storeD, "Tuna", "10kg", FoodType.SEAFOOD, 50, R.drawable.tuna, true, null));
        stores.add(storeA);
        stores.add(storeB);
        stores.add(storeC);
        stores.add(storeD);
        List<Food> vegetables = new ArrayList<>();
        List<Food> fruits = new ArrayList<>();
        List<Food> seafoodList = new ArrayList<>();
        for (int i = 0; i < stores.size(); i++) {
            for (int j = 0; j < stores.get(i).getFoodList().size(); j++) {
                Food food = stores.get(i).getFoodList().get(j);
                if (food.getFoodType() == FoodType.VEGETABLE) {
                    vegetables.add(food);
                }
                if (food.getFoodType() == FoodType.FRUIT) {
                    fruits.add(food);
                }

                if (food.getFoodType() == FoodType.SEAFOOD) {
                    seafoodList.add(food);
                }
            }
        }


    }

    public static void populateStoreData(List<Store> stores) {
        stores = new ArrayList<>();
        Store storeA = new Store(1, "Store A", "Nguyen The A", "200 Nguyen Trai Thanh Xuan Ha Noi");
        Store storeB = new Store(2, "Store B", "Nguyen The b", "201 Nguyen Trai Thanh Xuan Ha Noi");
        Store storeC = new Store(3, "Store C", "Nguyen The C", "202 Nguyen Trai Thanh Xuan Ha Noi");
        Store storeD = new Store(4, "Store D", "Nguyen The D", "203 Nguyen Trai Thanh Xuan Ha Noi");
        stores.add(storeA);
        stores.add(storeB);
        stores.add(storeC);
        stores.add(storeD);

    }



}
