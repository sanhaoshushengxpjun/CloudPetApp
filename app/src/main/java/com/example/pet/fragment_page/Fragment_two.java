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


public class Fragment_two extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_two, container, false);
        ListView pro_list2 = root.findViewById(R.id.pro_list2);
        Product product1 = new Product("豆腐猫砂除臭无尘猫沙大袋非10公斤","10kg",15.8,R.drawable.product_litter_image1,994,"魔宠旗舰店","深圳");
        Product product2 = new Product("西西猫砂10公斤除臭结团猫沙膨润土低尘猫砂20斤10kg","10KG",15.9,R.drawable.product_litter_image2,210,"","广州");

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);

        MyAdapter_product adapter = new MyAdapter_product(getContext(),productList);
        pro_list2.setAdapter(adapter);

        return root;
    }
}