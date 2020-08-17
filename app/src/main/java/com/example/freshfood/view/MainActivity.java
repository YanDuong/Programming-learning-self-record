package com.example.freshfood.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.drm.DrmStore;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.freshfood.R;
import com.example.freshfood.fragments.CartFragment;
import com.example.freshfood.fragments.FavouriteFragment;
import com.example.freshfood.fragments.HomeFragment;
import com.example.freshfood.fragments.StoreFragment;
import com.example.freshfood.model.Food;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import static com.example.freshfood.view.CartActivity.CART_ITEM_LIST;
import static com.example.freshfood.view.DetailActivity.ITEM_CART;

public class MainActivity extends AppCompatActivity {
    private ActionBar actionBar;
    private ArrayList<Food> foods;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(mBottomNavigationView);
        if(getIntent() != null) {
            foods = getIntent().getParcelableArrayListExtra(CART_ITEM_LIST);
        }
        actionBar.setTitle(R.string.home);
        loadFragment(new HomeFragment());


    }


    private BottomNavigationView.OnNavigationItemSelectedListener mBottomNavigationView = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch(item.getItemId()) {
                case R.id.navigation_homepage:
                    actionBar.setTitle("Home");
                    loadFragment(new HomeFragment());
                    return true;
                case R.id.navigation_cart:
                    actionBar.setTitle("Carts");
                    loadFragment(CartFragment.newInstance(foods));
                    return true;
                case R.id.navigation_store:
                    actionBar.setTitle("Store");
                    loadFragment(new StoreFragment());
                    return true;
                case R.id.navigation_favorite:
                    loadFragment(new FavouriteFragment());
                    return true;
            }
            return true;
        }


    };
    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainContainer, fragment)
                .addToBackStack(null)
                .commit();
    }

    public static Intent newIntent(Context context, Food item) {
        Intent intent =new Intent(context, MainActivity.class);
        Bundle args = new Bundle();
        args.putParcelable(ITEM_CART, item);
        intent.putExtras(args);
        return intent;
    }



}
