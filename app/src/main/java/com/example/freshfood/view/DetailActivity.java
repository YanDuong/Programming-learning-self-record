package com.example.freshfood.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.freshfood.Utils.Utils;
import com.example.freshfood.fragments.CartFragment;
import com.example.freshfood.model.Food;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.freshfood.R;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    public static final String FOOD_DETAIL = "food_detail";
    public static final String ITEM_CART = "cart_item";
    private Food item;
    private int likeCount;
    boolean isLiked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setupView();

    }



    private void setupView() {
        Intent intent = getIntent();
        if(intent != null) {
            item = intent.getParcelableExtra(FOOD_DETAIL);
        }ImageView ivFoodItem = findViewById(R.id.ivFoodItem);
       TextView tvStoreName = findViewById(R.id.tvStoreName);
       TextView tvName = findViewById(R.id.tvFoodName);
       TextView tvFoodAmount = findViewById(R.id.tvFoodAmount);
       TextView tvPrice = findViewById(R.id.tvPrice);
       TextView tvAvailability = findViewById(R.id.tvIsAvailable);
       ImageView ivFavorite = findViewById(R.id.ivFavourite);
        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = CartActivity.newIntent(DetailActivity.this, item);
                startActivity(intent);

            }
        });
       if(Utils.isFoodDiscount(item.getDiscountValue())) {
           TextView tvDiscount = findViewById(R.id.tvDiscount);
           tvDiscount.setVisibility(View.VISIBLE);
           tvDiscount.setText(item.getDiscountValue());
       }
       ivFoodItem.setImageResource(item.getImgSrc());
       tvStoreName.setText(item.getStore().getStoreName());
       tvName.setText(item.getName());
       tvFoodAmount.setText(item.getAmount());
       tvPrice.setText(String.valueOf(item.getPrice()));
       ivFavorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_24dp, null));
       if(item.isAvailable()) {
           tvAvailability.setText(R.string.food_available);
       } else {
           tvAvailability.setText(R.string.food_out);
       }
       likeCount = item.getLikeCount();
        Drawable drawable = ivFavorite.getDrawable();
       ivFavorite.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view) {
               if(!isLiked) {
                   drawable.setTint(getResources().getColor(R.color.white, null));
                   likeCount--;

               }
               drawable.setTint(getResources().getColor(R.color.red, null));
               likeCount++;
               isLiked = true;
           }
       });




    }



    public static Intent newIntent(Context context, Food item) {
        Intent intent = new Intent(context, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(FOOD_DETAIL, item);
        intent.putExtras(bundle);
        return intent;

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
