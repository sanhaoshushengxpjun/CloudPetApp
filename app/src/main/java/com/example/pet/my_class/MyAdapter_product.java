package com.example.pet.my_class;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.pet.MainActivity;
import com.example.pet.R;
import com.example.pet.user_data.Comment;
import com.example.pet.user_data.Product;

import java.util.List;

public class MyAdapter_product extends BaseAdapter {
    private List<Product> list ;
    //布局加载器：将xml转为View对象RelativeLayout
    private LayoutInflater mInflater;
    public MyAdapter_product(Context context , List<Product> list){
        this.list = list;
        //初始化布局加载器
        mInflater = LayoutInflater.from(context);
    }
    //配置显示的总item数量
    @Override
    public int getCount() {
        return list == null?0:list.size();
    }
    //按照位置获取数据对象
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }
    //根据位置获取item的id
    @Override
    public long getItemId(int position) {
        return position ;
    }
    //每个item的显示效果
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //加载每个item的布局对象（将item_user_layout转为RelativeLayout对象）
        @SuppressLint({"ViewHolder", "InflateParams"})
        View layout = mInflater.inflate(R.layout.product_list_item,null);

        ImageView pro_list_image = layout.findViewById(R.id.pro_list_image);
        TextView pro_list_name = layout.findViewById(R.id.pro_list_name);
        TextView pro_list_type = layout.findViewById(R.id.pro_list_type);
        TextView pro_list_price = layout.findViewById(R.id.pro_list_price);
        TextView pro_list_paynum = layout.findViewById(R.id.pro_list_paynum);
        TextView pro_list_shopname = layout.findViewById(R.id.pro_list_shopname);
        TextView pro_list_site = layout.findViewById(R.id.pro_list_site);
        //获取数据显示内容(数据绑定，将数据显示到布局中)
        Product u = list.get(position);

        pro_list_image.setImageResource(u.getImage_resId());
        pro_list_name.setText(u.getName());
        pro_list_type.setText(u.getType());
        pro_list_price.setText("￥"+u.getPrice());
        pro_list_paynum.setText(u.getPro_list_paynum()+"人付款");
        pro_list_shopname.setText(u.getShopname());
        pro_list_site.setText(u.getSite());


        return layout;
    }
}


