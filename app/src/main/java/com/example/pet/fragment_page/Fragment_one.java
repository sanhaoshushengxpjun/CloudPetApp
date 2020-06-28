package com.example.pet.fragment_page;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.pet.R;
import com.example.pet.my_class.MyAdapter_product;
import com.example.pet.user_data.Comment;
import com.example.pet.user_data.Product;

import java.util.ArrayList;
import java.util.List;


public class Fragment_one extends Fragment {
     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         // Inflate the layout for this fragment.
         View root = inflater.inflate(R.layout.fragment_one, container, false);
         ListView pro_list1 = root.findViewById(R.id.pro_list1);
         Product product1 = new Product("狗粮通用型40斤装中大型成犬幼犬大袋","20KG装",54.8,R.drawable.product_food_image1,994,"魔宠旗舰店","深圳");
         Product product2 = new Product("狗粮幼犬粮泰迪金毛比熊小型犬5通用型蛋黄奶糕狗粮","买一送一",36.2,R.drawable.product_food_image2,210,"","广州");
         Product product3 = new Product("凯锐思 成猫专用猫粮成年","5KG",49,R.drawable.product_food_image3,1190,"凯锐思旗舰店","浙江");
         Product product4 = new Product("凯锐思狗粮 小型犬幼犬成犬通用型粮","5KG",49,R.drawable.product_food_image4,1980,"凯锐思旗舰店","浙江");
         Product product5 = new Product("猫粮5kg 幼猫成猫10斤主粮","5KG",49.8,R.drawable.product_food_image5,4512,"宠物家具专卖","太原");
         Product product6 = new Product("猫粮10斤装5kg天然深海洋三文鱼散装猫食","标配",19.9,R.drawable.product_food_image6,994,"魔宠旗舰店","深圳");
         Product product7 = new Product("猫特勒猫粮成猫幼猫通用鱼肉海洋鱼味","标配",28.9,R.drawable.product_food_image7,453,"魔宠旗舰店","常山");
         Product product8 = new Product("美人喵全价猫粮成猫幼猫增肥","标配",49,R.drawable.product_food_image8,4567,"魔宠旗舰店","乌鲁木齐");
         Product product9 = new Product("泰国皇室进口猫粮暹罗英短蓝猫成猫猫咪天然粮","标配",89,R.drawable.product_food_image9,45,"魔宠旗舰店","吉林");
         Product product10 = new Product("伟嘉猫粮成猫专用维嘉海洋鱼味增肥","标配",59.9,R.drawable.product_food_image10,1235,"魔宠旗舰店","吉林");
         Product product11 = new Product("优趣猫粮成猫幼猫粮10kg","标配",169,R.drawable.product_food_image11,88,"魔宠旗舰店","深圳");
         Product product12 = new Product("幼猫猫粮增肥发腮小猫离乳期奶糕","标配",36,R.drawable.product_food_image12,15,"魔宠旗舰店","贵州");
         Product product13 = new Product("珍知乐猫粮幼猫成猫增肥","标配",78.9,R.drawable.product_food_image13,3678,"魔宠旗舰店","晋中");

         List<Product> productList = new ArrayList<>();
         productList.add(product1);
         productList.add(product2);
         productList.add(product3);
         productList.add(product4);
         productList.add(product5);
         productList.add(product6);
         productList.add(product7);
         productList.add(product8);
         productList.add(product9);
         productList.add(product10);
         productList.add(product11);
         productList.add(product12);
         productList.add(product13);

         MyAdapter_product adapter = new MyAdapter_product(getContext(),productList);
         pro_list1.setAdapter(adapter);

         return root;
     }
 }