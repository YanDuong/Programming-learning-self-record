package com.example.freshfood.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshfood.R;
import com.example.freshfood.model.Food;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.CartViewHolder> {
    private List<Food> foodsCart;
    private float amountCount;
    private Context context;
    public static final String FOOD_AMOUNT_PREFERENCES = "food_amount_preferences";
    public static final String CART_FOOD_AMOUNT = "food_amount";
    public CartItemAdapter(Context context, List<Food> foodsCart) {
        this.foodsCart = foodsCart;
        this.context = context;
    }
    static class CartViewHolder extends RecyclerView.ViewHolder {
        private View view;
        CartViewHolder(View view) {
            super(view);
            this.view = view;
        }

        public View getView() {
            return view;
        }
    }

    @Override
    public int getItemCount() {
        return foodsCart.size();
    }
    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart_cardview, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, final int position) {
        mapDataToView(holder.getView(), position);
    }

    private void mapDataToView(View view, int position) {
        Food item = foodsCart.get(position);
        ImageView ivCartItem = view.findViewById(R.id.ivCartItem);
        TextView tvFoodName = view.findViewById(R.id.tvItemName);
        TextView tvStoreName = view.findViewById(R.id.tvStoreItem);
        TextView tvReview = view.findViewById(R.id.tvReview);
        TextView tvRemove = view.findViewById(R.id.tvRemove);
        Button btnSubstract = view.findViewById(R.id.btnSubstract);
        Button btnPlus = view.findViewById(R.id.btnPlus);
        TextView tvSelectedAmount = view.findViewById(R.id.tvSelectedAmount);

        ivCartItem.setImageResource(item.getImgSrc());
        tvFoodName.setText(item.getName());
        tvStoreName.setText(item.getStore().getStoreName());
        tvRemove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                foodsCart.remove(position);
                notifyDataSetChanged();
            }
        });
        btnPlus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                amountCount += 0.5;
                tvSelectedAmount.setText(String.valueOf(amountCount));
            }
        });

        btnSubstract.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                amountCount -= 0.5;
                if(amountCount < 1) {
                    amountCount = 1;
                }
                tvSelectedAmount.setText(String.valueOf(amountCount));
            }
        });

        SharedPreferences preferences = context.getSharedPreferences(FOOD_AMOUNT_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putFloat(CART_FOOD_AMOUNT, amountCount);
        editor.apply();



    }


}
