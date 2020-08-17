package com.example.freshfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshfood.R;
import com.example.freshfood.Utils.Utils;
import com.example.freshfood.model.Food;
import com.example.freshfood.model.FoodType;
import com.example.freshfood.model.Store;

import java.util.ArrayList;
import java.util.List;


public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.StoreViewHolder> {
    private RecyclerView storeRecyclerView;
    private Context context;
    private List<Store> stores;
    private Callback callback;

    public StoreAdapter(Context context, List<Store> stores, Callback callback) {
        this.context = context;
        this.stores = stores;
        this.callback = callback;
    }


    static class StoreViewHolder extends RecyclerView.ViewHolder {
        private View view;
        StoreViewHolder(View view) {
            super(view);
            this.view = view;
        }

        public View getView() {
            return this.view;
        }
    }
    @NonNull
   @Override
    public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_cardview, parent, false);
        return new StoreViewHolder(view);
   }

    @Override
    public void onBindViewHolder(@NonNull StoreViewHolder holder, int position) {
        mapDataToView(holder.getView(), position);
    }

    private void mapDataToView(View view,int position) {
        Store store = stores.get(position);
        ImageView ivStore = view.findViewById(R.id.ivStore);
        ivStore.setImageBitmap(Utils.resizeImageFromResource(context, store.getImgStore(), 180, 150));
        TextView tvStoreName = view.findViewById(R.id.tvStoreName);
        ImageView ivStoreOwner = view.findViewById(R.id.ivStoreOwner);
        ivStoreOwner.setImageBitmap(Utils.resizeImageFromResource(context, store.getImgOwner(), 40, 40));
        TextView tvStoreOwner = view.findViewById(R.id.tvStoreOwner);
        TextView tvLocation = view.findViewById(R.id.tvStoreLocation);
         tvStoreName.setText(store.getStoreName());
         tvStoreOwner.setText(store.getStoreOwner());
         tvLocation.setText(store.getStoreLocation());
         view.setOnClickListener(new View.OnClickListener(){
             @Override
             public void onClick(View view) {
                 if(callback != null) {
                     callback.onStoreItemClick(store);
                 }
             }
         });


    }

    @Override
    public int getItemCount() {
        return stores.size();
    }



    public interface Callback {
        void onStoreItemClick(Store store);
    }



}
