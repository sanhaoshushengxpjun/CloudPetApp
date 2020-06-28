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

import java.util.List;

public class MyAdapter_comment extends BaseAdapter {
    private List<Comment> list ;
    //布局加载器：将xml转为View对象RelativeLayout
    private LayoutInflater mInflater;
    public MyAdapter_comment(Context context , List<Comment> list){
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
        View layout = mInflater.inflate(R.layout.fragment_doc_list_item,null);
        //初始化布局中的元素
        CircleImageView doc_icon = layout.findViewById(R.id.doc_icon);
        TextView doc_name = layout.findViewById(R.id.doc_name);
        TextView doc_connect = layout.findViewById(R.id.doc_connect);
        TextView doc_share = layout.findViewById(R.id.doc_share);
        TextView doc_comment = layout.findViewById(R.id.doc_comment);
        TextView doc_like = layout.findViewById(R.id.doc_like);

        ImageView doc_image1 = layout.findViewById(R.id.doc_image1);
        ImageView doc_image2 = layout.findViewById(R.id.doc_image2);
        ImageView doc_image3 = layout.findViewById(R.id.doc_image3);
        ImageView doc_image4 = layout.findViewById(R.id.doc_image4);
        ImageView doc_image5 = layout.findViewById(R.id.doc_image5);
        ImageView doc_image6 = layout.findViewById(R.id.doc_image6);
        //获取数据显示内容(数据绑定，将数据显示到布局中)
        Comment u = list.get(position);

        doc_icon.setImageResource(u.getuser_icon_resid());
        doc_name.setText(u.getUser_name());
        doc_connect.setText(u.getDoc_content());

        int image_count = u.getImage_list().length;
        for(int i = 0;i<image_count;i++){
            if(i == 0 ) {
                doc_image1.setVisibility(View.VISIBLE);
                doc_image1.setImageResource(u.getImage_list()[i]);
            }if(i == 1 ) {
                doc_image2.setVisibility(View.VISIBLE);
                doc_image2.setImageResource(u.getImage_list()[i]);
            }if(i == 2 ) {
                doc_image3.setVisibility(View.VISIBLE);
                doc_image3.setImageResource(u.getImage_list()[i]);
            }if(i == 3 ) {
                doc_image4.setVisibility(View.VISIBLE);
                doc_image4.setImageResource(u.getImage_list()[i]);
            }if(i == 4 ) {
                doc_image5.setVisibility(View.VISIBLE);
                doc_image5.setImageResource(u.getImage_list()[i]);
            }if(i == 5 ) {
                doc_image6.setVisibility(View.VISIBLE);
                doc_image6.setImageResource(u.getImage_list()[i]);
            }
        }

        doc_share.setText(u.getShare_count()+"");
        doc_comment.setText(u.getComment_count()+"");
        doc_like.setText(u.getLike_count()+"");

        return layout;
    }
}


