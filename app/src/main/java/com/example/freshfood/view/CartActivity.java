package com.example.freshfood.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.freshfood.R;
import com.example.freshfood.adapter.CartItemAdapter;
import com.example.freshfood.model.Food;

import java.util.ArrayList;
import java.util.List;

import static com.example.freshfood.adapter.CartItemAdapter.CART_FOOD_AMOUNT;

public class CartActivity extends AppCompatActivity {
private static final String CART_ITEM = "cart_item";
    public static final String CART_ITEM_LIST = "cart_item_list";
private ArrayList<Food> cartFoodList = new ArrayList<>();
private Food item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        setupView();
    }

    private void setupView() {
        setupRecyclerView();
        TextView tvValue = findViewById(R.id.tvPaymentValue);
        tvValue.setText(String.valueOf(getTotalPayment()));
        Button btnPayment = findViewById(R.id.btnPayment);
        btnPayment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

            }
        });

        Button btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CartActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(CART_ITEM_LIST, cartFoodList);
                intent.putExtras(bundle);
                startActivity(intent);
                CartActivity.this.finish();

            }
        });
    }
    private void setupRecyclerView() {
        RecyclerView cartItemRecyclerView = findViewById(R.id.cartRecyclerView);
        cartItemRecyclerView.setHasFixedSize(true);
        Intent intent = getIntent();
        if(intent != null) {
            item = intent.getParcelableExtra(CART_ITEM);
            cartFoodList.add(item);
        }
        cartItemRecyclerView.setAdapter(new CartItemAdapter(this, cartFoodList));
        cartItemRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


    }
    private float getTotalPayment() {
        SharedPreferences preferences = this.getPreferences(Context.MODE_PRIVATE);
        float amount = preferences.getFloat(CART_FOOD_AMOUNT, 0);
        float price = item.getPrice();
        return amount * price;

    }



    public static Intent newIntent(Context context, Food item) {
        Intent intent = new Intent(context, CartActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(CART_ITEM, item);
        intent.putExtras(bundle);
        return intent;

    }
}
