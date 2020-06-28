package com.example.pet.my_class;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pet.R;

import java.util.HashMap;
import java.util.List;

public class MyAdapter_order extends BaseAdapter {

    private Context context;
    private List<HashMap<String, String>> list;
    private HashMap<String, Integer> pitchOnMap;
    private LayoutInflater mInflater;

    public HashMap<String, Integer> getPitchOnMap() {
        return pitchOnMap;
    }

    public void setPitchOnMap(HashMap<String, Integer> pitchOnMap) {
        this.pitchOnMap = pitchOnMap;
    }

    public MyAdapter_order(Context context, List<HashMap<String, String>> list) {
        this.context = context;
        this.list = list;
        mInflater = LayoutInflater.from(context);

        pitchOnMap = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            pitchOnMap.put(list.get(i).get("id"), 0);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
//        convertView = View.inflate(context, R.layout.order_list_main, null);
        View layout = mInflater.inflate(R.layout.order_list_item,null);
        final CheckBox checkBox;
        ImageView icon;
        final TextView name, price, num, type, reduce, add;

        checkBox = layout.findViewById(R.id.check_box);
        icon = layout.findViewById(R.id.iv_adapter_list_pic);
        name = layout.findViewById(R.id.tv_goods_name);
        price = layout.findViewById(R.id.tv_goods_price);
        type = layout.findViewById(R.id.tv_type_size);
        num = layout.findViewById(R.id.tv_num);
        reduce = layout.findViewById(R.id.tv_reduce);
        add = layout.findViewById(R.id.tv_add);

        name.setText(list.get(position).get("name"));

        price.setText("¥ " + Double.valueOf(String.format("%.2f",(Double.valueOf(list.get(position).get("price"))) * (Integer.valueOf(list.get(position).get("count"))))));
        type.setText(list.get(position).get("type"));
        num.setText(list.get(position).get("count"));
        icon.setImageResource(Integer.valueOf(list.get(position).get("image")));

        if(pitchOnMap.get(list.get(position).get("id"))== 0){
            checkBox.setChecked(false);
        }else{
            checkBox.setChecked(true);
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkBox.isChecked()){
                    pitchOnMap.put(list.get(position).get("id"),1);
                }else{
                    pitchOnMap.put(list.get(position).get("id"), 0);
                }
                mrefreshPriceInterface.refreshPrice(pitchOnMap);
            }
        });

        //商品数量减
        reduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Integer.valueOf(list.get(position).get("count")) <= 1) {
                    Toast.makeText(context, "数量不能再减啦,只能删除!", Toast.LENGTH_SHORT).show();
                } else {
                    list.get(position).put("count", (Integer.valueOf(list.get(position).get("count")) - 1) + "");
                    notifyDataSetChanged();
                }
                mrefreshPriceInterface.refreshPrice(pitchOnMap);
            }
        });
        //商品数量加
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(position).put("count", (Integer.valueOf(list.get(position).get("count")) + 1) + "");
                notifyDataSetChanged();
                mrefreshPriceInterface.refreshPrice(pitchOnMap);

            }

        });

        return layout;
    }

    /**
     * 创建接口
     */
    public interface RefreshPriceInterface {
        /**
         * 把价格展示到总价上
         * @param pitchOnMap
         */
        void refreshPrice(HashMap<String, Integer> pitchOnMap);
    }

    /**
     * 定义一个接口对象
     */
    private RefreshPriceInterface mrefreshPriceInterface;

    /**
     * 向外部暴露一个方法
     * 把价格展示到总价上
     * @param refreshPriceInterface
     */
    public void setRefreshPriceInterface(RefreshPriceInterface refreshPriceInterface) {
        mrefreshPriceInterface = refreshPriceInterface;
    }


}
