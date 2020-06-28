package com.example.pet.ui.shop;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pet.R;
import com.example.pet.my_class.MyAdapter_order;
import com.example.pet.user_data.User_order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class shopping extends Fragment implements View.OnClickListener, MyAdapter_order.RefreshPriceInterface {
    private LinearLayout top_bar;
    private ListView listview;
    private CheckBox all_chekbox;
    private TextView price;
    private TextView delete;
    private TextView tv_go_to_pay;

    private List<User_order> goodsList  = new ArrayList<>();;
    private List<HashMap<String,String>> listmap=new ArrayList<>();
    private MyAdapter_order adapter;

    private double totalPrice = 0.00;
    private int totalCount = 0;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        initView(view);
        return view;

    }
    private void initView(View v) {
        top_bar = (LinearLayout) v.findViewById(R.id.top_bar);
        listview = (ListView) v.findViewById(R.id.listview);
        all_chekbox = (CheckBox) v.findViewById(R.id.all_chekbox);
        price = (TextView) v.findViewById(R.id.tv_total_price);
        delete = (TextView) v.findViewById(R.id.tv_delete);
        tv_go_to_pay = (TextView) v.findViewById(R.id.tv_go_to_pay);

        all_chekbox.setOnClickListener(this);
        delete.setOnClickListener(this);
        tv_go_to_pay.setOnClickListener(this);

        initDate();
        adapter = new MyAdapter_order(getActivity(), listmap);
        listview.setAdapter(adapter);
        adapter.setRefreshPriceInterface(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.all_chekbox:
                AllTheSelected();
                break;
            case R.id.tv_delete:
                checkDelete(adapter.getPitchOnMap());
                break;
            case R.id.tv_go_to_pay:
                if(totalCount<=0){
                    Toast.makeText(getActivity(),"请选择要付款的商品~",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(),"付款成功",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    /**
     * 数据
     */
    private void initDate() {
//        //数据源
//        for (int i = 0; i < 10; i++) {
//            //向数据库存放数据
//            User_order user_order = new User_order((long) i,
//                    "购物车里的第" + (i + 1) + "件商品",
//                    (i + 20) + "码",
//                    "10",
//                    "10");
//            goodsList.add(user_order);
//        }
        User_order user_order1 = new User_order((long)1,"宠物狗狗玩具球耐咬幼犬小狗磨牙","标配",5.8,2,R.drawable.order_list_image2);
        User_order user_order2 = new User_order((long)2,"珍知乐猫粮幼猫成猫增肥","5KG",78.9,1,R.drawable.order_list_image1);
        User_order user_order3 = new User_order((long)3,"豆腐猫砂除臭无尘猫沙大袋10公斤","两袋共10KG",15.8,1,R.drawable.order_list_image3);
        User_order user_order4 = new User_order((long)4,"猫咪航空箱便携外出外出托运箱空运箱运输箱航空箱","标配",269,1,R.drawable.order_list_image4);
        User_order user_order5 = new User_order((long)5,"西西猫砂10公斤除臭结团猫沙膨润土低尘猫砂20斤10kg","10KG",15.9,2,R.drawable.order_list_image5);
        goodsList.add(user_order1);
        goodsList.add(user_order2);
        goodsList.add(user_order3);
        goodsList.add(user_order4);
        goodsList.add(user_order5);
        //从数据库中把数据放到集合中
        //把结合中的数据放到HashMap集合中
        for(int i=0;i<goodsList.size();i++){
            HashMap<String,String> map=new HashMap<>();
            map.put("id",goodsList.get(i).getId()+"");
            map.put("name",goodsList.get(i).getName());
            map.put("type",(goodsList.get(i).getType()));
            map.put("price",goodsList.get(i).getPrice()+"");
            map.put("count",goodsList.get(i).getCount()+"");
            map.put("image",goodsList.get(i).getOrder_image_resId()+"");
            listmap.add(map);
        }
    }

    @Override
    public void refreshPrice(HashMap<String, Integer> pitchOnMap) {
        priceControl(pitchOnMap);
    }

    /**
     * 控制价格展示总价
     */
    private void priceControl(Map<String, Integer> pitchOnMap){
        totalCount = 0;
        totalPrice = 0.00;
        for(int i=0;i<listmap.size();i++){
            if(pitchOnMap.get(listmap.get(i).get("id"))==1){
                totalCount=totalCount+Integer.valueOf(listmap.get(i).get("count"));
                double goodsPrice=Integer.valueOf(listmap.get(i).get("count"))*Double.valueOf(listmap.get(i).get("price"));
                totalPrice=totalPrice+goodsPrice;
            }
        }
        price.setText(" ¥ "+totalPrice);
        tv_go_to_pay.setText("付款("+totalCount+")");
    }

    /**
     * 删除 控制价格展示总价
     * @param map
     */
    private void checkDelete(Map<String,Integer> map){
        List<HashMap<String,String>> waitDeleteList=new ArrayList<>();
        Map<String,Integer> waitDeleteMap =new HashMap<>();
        for(int i=0;i<listmap.size();i++){
            if(map.get(listmap.get(i).get("id"))==1){
                waitDeleteList.add(listmap.get(i));
                waitDeleteMap.put(listmap.get(i).get("id"),map.get(listmap.get(i).get("id")));
            }
        }
        listmap.removeAll(waitDeleteList);
        map.remove(waitDeleteMap);
        priceControl(map);
        adapter.notifyDataSetChanged();
    }
    /**
     *全选或反选
     */
    private void AllTheSelected(){
        HashMap<String,Integer> map=adapter.getPitchOnMap();
        boolean isCheck=false;
        boolean isUnCheck=false;
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();

            if(Integer.valueOf(entry.getValue().toString())==1){
                isCheck=true;
            }else{
                isUnCheck=true;
            }
        }
        if(isCheck==true&&isUnCheck==false){//已经全选,做反选
            for(int i=0;i<listmap.size();i++){
                map.put(listmap.get(i).get("id"),0);
            }
            all_chekbox.setChecked(false);
        }else if(isCheck==true && isUnCheck==true){//部分选择,做全选
            for(int i=0;i<listmap.size();i++){
                map.put(listmap.get(i).get("id"),1);
            }
            all_chekbox.setChecked(true);
        }else if(isCheck==false && isUnCheck==true){//一个没选,做全选
            for(int i=0;i<listmap.size();i++){
                map.put(listmap.get(i).get("id"),1);
            }
            all_chekbox.setChecked(true);
        }
        priceControl(map);
        adapter.setPitchOnMap(map);
        adapter.notifyDataSetChanged();
    }
}
