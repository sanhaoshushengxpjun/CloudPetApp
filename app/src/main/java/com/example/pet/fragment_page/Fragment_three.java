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


public class Fragment_three extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_three, container, false);
        ListView pro_list3 = root.findViewById(R.id.pro_list3);
        Product product1 = new Product("宠物狗狗玩具球耐咬幼犬小狗磨牙","豪华版",5.8,R.drawable.product_toy_image1,994,"魔宠旗舰店","深圳");
        Product product2 = new Product("狗狗玩具耐咬幼犬磨牙宠物球玩具套装","买一送一",10,R.drawable.product_toy_image2,210,"","广州");
        Product product3 = new Product("猫笼家用室内猫咪三层猫舍猫窝猫笼子猫别墅清仓","经济版",229,R.drawable.product_toy_image3,1190,"凯锐思旗舰店","浙江");
        Product product4 = new Product("猫咪航空箱便携外出外出托运箱空运箱运输箱航空箱","50cm*50cm*30cm",269,R.drawable.product_toy_image4,1980,"凯锐思旗舰店","浙江");

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);

        MyAdapter_product adapter = new MyAdapter_product(getContext(),productList);
        pro_list3.setAdapter(adapter);

        return root;
    }
}