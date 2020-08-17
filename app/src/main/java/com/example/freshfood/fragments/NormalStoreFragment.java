package com.example.freshfood.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.freshfood.R;
import com.example.freshfood.adapter.StoreAdapter;
import com.example.freshfood.model.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NormalStoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NormalStoreFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private List<Store> stores;

    public NormalStoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NormalStoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NormalStoreFragment newInstance(String param1, String param2) {
        NormalStoreFragment fragment = new NormalStoreFragment();
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
        return inflater.inflate(R.layout.fragment_normal_store, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        populateStoreData();
        mapDataToView(view);
    }

    private void mapDataToView(View view) {
        RecyclerView vegetableStoreRecyclerView  = view.findViewById(R.id.storeRecyclerView);
        vegetableStoreRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        vegetableStoreRecyclerView.setAdapter(new StoreAdapter(getActivity(), stores, new StoreAdapter.Callback() {
            @Override
            public void onStoreItemClick(Store store) {

            }
        }));
    }
    private void populateStoreData() {
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
