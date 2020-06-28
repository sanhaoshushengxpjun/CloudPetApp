package com.example.pet.fragment_page;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.pet.R;
import com.example.pet.my_class.MyAdapter_product;
import com.example.pet.user_data.Product;

import java.util.ArrayList;
import java.util.List;


public class Fragment_four extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_four, container, false);
        ListView pro_list4 = root.findViewById(R.id.pro_list4);

        List<Product> productList = new ArrayList<>();


        MyAdapter_product adapter = new MyAdapter_product(getContext(),productList);
        pro_list4.setAdapter(adapter);

        return root;
    }
}