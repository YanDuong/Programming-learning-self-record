package com.example.freshfood.fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.freshfood.Callback;
import com.example.freshfood.R;
import com.example.freshfood.Utils.Utils;
import com.example.freshfood.adapter.FoodAdapter;
import com.example.freshfood.model.Food;
import com.example.freshfood.model.FoodType;
import com.example.freshfood.model.Store;
import com.example.freshfood.view.DetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements Callback {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private List<Food> vegetables, fruits, seafoodList;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        populateItem();
        mapDataToView(view);
    }

    private void mapDataToView(View view) {
        RecyclerView vegetableRecyclerView = view.findViewById(R.id.vegetableRecycleView);
        RecyclerView fruitsRecyclerView = view.findViewById(R.id.fruitsRecyclerView);
        RecyclerView seaFoodRecyclerView = view.findViewById(R.id.seaFoodRecyclerView);


        vegetableRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        fruitsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        seaFoodRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));

        FoodAdapter vegetableAdapter = new FoodAdapter(getActivity(), vegetables);
        FoodAdapter fruitsAdapter = new FoodAdapter(getActivity(), fruits);
        FoodAdapter seafoodAdapter = new FoodAdapter(getActivity(), seafoodList);

        vegetableAdapter.setCallback(this);
        fruitsAdapter.setCallback(this);
        seafoodAdapter.setCallback(this);

        vegetableRecyclerView.setAdapter(vegetableAdapter);
        vegetableRecyclerView.setHasFixedSize(true);
        SnapHelper vegetableHelper =new LinearSnapHelper();
        vegetableHelper.attachToRecyclerView(vegetableRecyclerView);

        fruitsRecyclerView.setAdapter(fruitsAdapter);
        fruitsRecyclerView.setHasFixedSize(true);
        SnapHelper fruitsHelper = new LinearSnapHelper();
        fruitsHelper.attachToRecyclerView(fruitsRecyclerView);

        seaFoodRecyclerView.setAdapter(seafoodAdapter);
        seaFoodRecyclerView.setHasFixedSize(true);
        SnapHelper seafoodHelper = new LinearSnapHelper();
        seafoodHelper.attachToRecyclerView(seaFoodRecyclerView);

    }

    @Override
    public void onItemClick(Food item) {
        Intent intent = DetailActivity.newIntent(getActivity(), item);
        startActivity(intent);
    }

    private void populateItem() {
        Store storeA = new Store(1, "Store A", "Nguyen The A", "200 Nguyen Trai Thanh Xuan Ha Noi");
        Store storeB = new Store(2, "Store B", "Nguyen The b", "201 Nguyen Trai Thanh Xuan Ha Noi");
        Store storeC = new Store(3, "Store C", "Nguyen The C", "202 Nguyen Trai Thanh Xuan Ha Noi");
        Store storeD = new Store(4, "Store D", "Nguyen The D", "203 Nguyen Trai Thanh Xuan Ha Noi");
        vegetables = new ArrayList<>();
        vegetables.add(new Food(storeA, "Peas", "5 kg", FoodType.VEGETABLE, 100, R.drawable.peas, true, "0%"));
        vegetables.add(new Food(storeB, "Carrot", "5 kg", FoodType.VEGETABLE, 100, R.drawable.carrot, false, "20%"));
        vegetables.add(new Food(storeC, "Tomato", "5 kg", FoodType.VEGETABLE, 100, R.drawable.tomato, false, "0%"));
        vegetables.add(new Food(storeB, "Mushroom", "5 kg", FoodType.VEGETABLE, 100, R.drawable.mushroom, false, "45%"));
        vegetables.add(new Food(storeB, "Recipe", "5 kg", FoodType.VEGETABLE, 100, R.drawable.recipe, false, "20%"));
        vegetables.add(new Food(storeB, "Lettuce", "5 kg", FoodType.VEGETABLE, 100, R.drawable.lettuce, false, "0%"));


        fruits = new ArrayList<>();
        fruits.add(new Food(storeA, "Apple", "5kg", FoodType.FRUIT, 50, R.drawable.apple, false, "10%"));
        fruits.add(new Food(storeA, "Tomato", "10kg", FoodType.FRUIT, 50, R.drawable.tomato, true, "0%"));
        fruits.add(new Food(storeC, "Apple", "10kg", FoodType.FRUIT, 50, R.drawable.apple, true, "30%"));
        fruits.add(new Food(storeC, "Orange", "10kg", FoodType.FRUIT, 50, R.drawable.orange, true, "30%"));
        fruits.add(new Food(storeC, "Mango", "10kg", FoodType.FRUIT, 50, R.drawable.mango, true, "25%"));
        fruits.add(new Food(storeD, "Grape", "10kg", FoodType.FRUIT, 50, R.drawable.grape, true, "30%"));


        seafoodList =new ArrayList<>();
        seafoodList.add(new Food(storeA, "Crab", "5kg", FoodType.SEAFOOD, 50, R.drawable.crab, false, "15%"));
        seafoodList.add(new Food(storeB, "Salmon", "10kg", FoodType.SEAFOOD, 50, R.drawable.salmon, true, "35%"));
        seafoodList.add(new Food(storeB, "Crayfish", "10kg", FoodType.SEAFOOD, 50, R.drawable.crayfish, true, "0%"));
        seafoodList.add(new Food(storeC, "Prawn", "10kg", FoodType.SEAFOOD, 50, R.drawable.prawn, true, "25%"));
        seafoodList.add(new Food(storeD, "Squid", "10kg", FoodType.SEAFOOD, 50, R.drawable.squid, true, "0%"));
        seafoodList.add(new Food(storeD, "Tuna", "10kg", FoodType.SEAFOOD, 50, R.drawable.tuna, true, "0%"));

    }

//static class DataDownloader extends AsyncTask<Void, Void, Void> {
//private List<Food> vegetables, fruits, seafoodList;
//
//       DataDownloader() {
//
//      }
//
//      public void setVegetables(List<Food> vegetables) {
//          this.vegetables = vegetables;
//      }
//
//      public void setFruits(List<Food> fruits) {
//          this.fruits = fruits;
//      }
//
//      public void setSeafoodList(List<Food> seafoodList) {
//          this.seafoodList = seafoodList;
//      }
//
//    @Override
//    protected Void doInBackground(Void... voids) {
//        Store storeA = new Store(1, "Store A", "Nguyen The A", "200 Nguyen Trai Thanh Xuan Ha Noi");
//        Store storeB = new Store(2, "Store B", "Nguyen The b", "201 Nguyen Trai Thanh Xuan Ha Noi");
//        Store storeC = new Store(3, "Store C", "Nguyen The C", "202 Nguyen Trai Thanh Xuan Ha Noi");
//        Store storeD = new Store(4, "Store D", "Nguyen The D", "203 Nguyen Trai Thanh Xuan Ha Noi");
//        List<Store> stores = new ArrayList<>();
//
//        storeA.insertNewFood(new Food(storeA, "Peas", "5 kg", FoodType.VEGETABLE, 100, R.drawable.peas, true, "12%"));
//        storeA.insertNewFood(new Food(storeA, "Apple", "5kg", FoodType.FRUIT, 50, R.drawable.apple, false, "10%"));
//        storeA.insertNewFood(new Food(storeA, "Crab", "5kg", FoodType.SEAFOOD, 50, R.drawable.crab, false, "15%"));
//        storeA.insertNewFood(new Food(storeA, "Tomato", "10kg", FoodType.FRUIT, 50, R.drawable.tomato, true, null));
//
//        storeB.insertNewFood(new Food(storeB, "Salmon", "10kg", FoodType.SEAFOOD, 50, R.drawable.salmon, true, "35%"));
//        storeB.insertNewFood(new Food(storeB, "Carrot", "5 kg", FoodType.VEGETABLE, 100, R.drawable.carrot, false, "20%"));
//        storeB.insertNewFood(new Food(storeB, "Crayfish", "10kg", FoodType.SEAFOOD, 50, R.drawable.crayfish, true, null));
//
//        storeC.insertNewFood(new Food(storeC, "Apple", "10kg", FoodType.FRUIT, 50, R.drawable.apple, true, "30%"));
//        storeC.insertNewFood(new Food(storeC, "Orange", "10kg", FoodType.FRUIT, 50, R.drawable.orange, true, "30%"));
//        storeC.insertNewFood(new Food(storeC, "Mango", "10kg", FoodType.FRUIT, 50, R.drawable.mango, true, "25%"));
//        storeC.insertNewFood(new Food(storeC, "Prawn", "10kg", FoodType.SEAFOOD, 50, R.drawable.prawn, true, "25%"));
//
//        storeD.insertNewFood(new Food(storeD, "Grape", "10kg", FoodType.FRUIT, 50, R.drawable.grape, true, "30%"));
//        storeD.insertNewFood(new Food(storeD, "Squid", "10kg", FoodType.SEAFOOD, 50, R.drawable.squid, true, null));
//        storeD.insertNewFood(new Food(storeD, "Tuna", "10kg", FoodType.SEAFOOD, 50, R.drawable.tuna, true, null));
//        stores.add(storeA);
//        stores.add(storeB);
//        stores.add(storeC);
//        stores.add(storeD);
//        vegetables = new ArrayList<>();
//        fruits = new ArrayList<>();
//        seafoodList = new ArrayList<>();
//
//        for (int i = 0; i < stores.size(); i++) {
//            List<Food> foods = stores.get(i).getFoodList();
//            for (int j = 0; j < foods.size(); j++) {
//                Food food = foods.get(j);
//                if (food.getFoodType() == FoodType.VEGETABLE) {
//                    vegetables.add(food);
//                }
//                if (food.getFoodType() == FoodType.FRUIT) {
//                    fruits.add(food);
//                }
//                if (food.getFoodType() == FoodType.SEAFOOD) {
//                    seafoodList.add(food);
//                }
//            }
//        }
//return null;
//    }}
//

}








