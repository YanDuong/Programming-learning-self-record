package com.example.freshfood.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshfood.Callback;
import com.example.freshfood.R;
import com.example.freshfood.Utils.Utils;
import com.example.freshfood.model.Food;

import org.w3c.dom.Text;

import java.util.List;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {
    private static final String NO_DISCOUNT = "0%";
private List<Food> foods;
private Callback callback;
private Context context;
private static final int NO_DISCOUNT_VIEW = 0;
private static final int DISCOUNT_VIEW = 1;
public FoodAdapter(Context context, List<Food> foods) {
    this.foods = foods;
    this.context = context;

}

public void setCallback(Callback callback) {
    this.callback = callback;
}
    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    int layoutId;
    if(viewType == DISCOUNT_VIEW) {
        layoutId = R.layout.food_discount_cardview;
        } else {
        layoutId = R.layout.food_cardview;
    }
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new FoodViewHolder(view);
    }

    public int getItemViewType(int position) {

        if(Utils.isFoodDiscount(foods.get(position).getDiscountValue())) {
            return DISCOUNT_VIEW;
        } else {
            return NO_DISCOUNT_VIEW;
        }

    }



    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
        mapDataToView(holder.getView(), position);
    }


    private void mapDataToView(View view, int position) {
        final Food item = foods.get(position);
        if(getItemViewType(position) == DISCOUNT_VIEW) {
            TextView tvDiscount = view.findViewById(R.id.discountValue);

            if (!item.getDiscountValue().equalsIgnoreCase(NO_DISCOUNT)) {
                tvDiscount.setText(item.getDiscountValue());
                tvDiscount.setVisibility(View.VISIBLE);
            }
        }
        ImageView ivFood = view.findViewById(R.id.ivFoodItem);
        TextView tvName = view.findViewById(R.id.tvFoodName);
        TextView tvStoreName = view.findViewById(R.id.tvStoreName);
        TextView tvAmount = view.findViewById(R.id.tvFoodAmount);
        TextView tvPrice = view.findViewById(R.id.tvPrice);
        TextView tvIsAvailable = view.findViewById(R.id.tvIsAvailable);

        ivFood.setImageBitmap(Utils.resizeImageFromResource(context, item.getImgSrc(), 100, 100));
        tvName.setText(item.getName());
        tvStoreName.setText(item.getStore().getStoreName());
        tvAmount.setText(item.getAmount());
        tvPrice.setText(String.valueOf(item.getPrice()));
        if(item.isAvailable()) {
            tvIsAvailable.setText(R.string.food_available);
        } else {
            tvIsAvailable.setText(R.string.food_out);
        }
        tvIsAvailable.setTextColor(item.getColorResId());

        view.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(callback != null) {
                    callback.onItemClick(item);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return foods.size();
    }

    static class FoodViewHolder extends RecyclerView.ViewHolder {
        private View view;
        FoodViewHolder(View view) {
            super(view);
            this.view = view;
        }

        public View getView() {
            return this.view;
        }

    }





}
