package com.example.freshfood.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.freshfood.R;
import com.example.freshfood.adapter.CartItemAdapter;
import com.example.freshfood.model.Food;
import com.example.freshfood.view.DetailActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.freshfood.adapter.CartItemAdapter.CART_FOOD_AMOUNT;
import static com.example.freshfood.view.CartActivity.CART_ITEM_LIST;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String CART_ITEM = "cart_item";
    private ArrayList<Food> cartFoodList = new ArrayList<>();
    private CartItemAdapter cartAdapter;
    // TODO: Rename and change types of parameters


    public CartFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(ArrayList<Food> foods) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(CART_ITEM_LIST, foods);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
           cartFoodList = getArguments().getParcelableArrayList(CART_ITEM_LIST);
        }
        cartAdapter = new CartItemAdapter(getActivity(), cartFoodList);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        if(cartFoodList != null && !cartFoodList.isEmpty() ) {
            mapDataToView(view);
        } else {
            TextView tvCartState = view.findViewById(R.id.tvEmptyCart);
            CardView cartCardView = view.findViewById(R.id.cartCardView);
            cartCardView.setVisibility(View.GONE);
            tvCartState.setText(R.string.empty_cart);
        }

    }

    private void mapDataToView(View view) {
        setupRecyclerView(view);
        TextView tvTotal = view.findViewById(R.id.tvTotal);
        TextView tvValue = view.findViewById(R.id.tvPaymentValue);
        tvValue.setText(String.valueOf(getTotalAmount()));
        Button btnPayment = view.findViewById(R.id.btnPayment);
        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    private void setupRecyclerView(View view) {
        RecyclerView cartItemRecyclerView = view.findViewById(R.id.cartRecyclerView);
        cartItemRecyclerView.setHasFixedSize(true);
        cartItemRecyclerView.setAdapter(cartAdapter);
        cartItemRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));


    }

    private float getTotalAmount() {
        SharedPreferences preferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        if (preferences == null) {
            return 0;
        }
        return preferences.getFloat(CART_FOOD_AMOUNT, 0);

    }


//    @Override
//    public void addToCart(Food item) {
//        cartFoodList.add(item);
//        cartAdapter.notifyDataSetChanged();
//    }
}
