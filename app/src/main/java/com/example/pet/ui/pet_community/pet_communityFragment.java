package com.example.pet.ui.pet_community;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.pet.R;
import com.example.pet.my_class.MyAdapter_comment;
import com.example.pet.user_data.Comment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class pet_communityFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_pet_community, container, false);

        ListView doc_list = root.findViewById(R.id.list);

        Comment comment1 = new Comment("Mmrou",
                R.drawable.ic_user,
                "免费领养狗狗\n" + "朋友家的狗狗免费领养，要出国找个负责人的主人，疫苗驱虫做好了，三个月，相当活泼可爱，温顺粘人。",
                new int[]{R.drawable.comment_1_1, R.drawable.comment_1_2, R.drawable.comment_1_3},
        2,3,4);
        Comment comment2 = new Comment("玺飞",
                R.drawable.ic_userimage,
                "颜色好看吗？",
                new int[]{R.drawable.comment_2_1, R.drawable.comment_2_2},
                66,80,114);
        Comment comment3 = new Comment("Fei",
                R.drawable.ic_user,
                "一直来自日本Hana小狗，有两岁半了，它的脑袋上就系了一双马尾，瞬间感觉萌萌哒有木有，\n" +
                        "~关注更多萌宠，快点扫一扫",
                new int[]{R.drawable.comment_3_1, R.drawable.comment_3_2, R.drawable.comment_3_3, R.drawable.comment_3_4, R.drawable.comment_3_5,},
                354,225,1100);
        Comment comment4 = new Comment("哆雪",
                R.drawable.comment_4_1,
                "瑜伽是很多女性朋友的真爱，其实好多毛茸茸的小精灵也在追求这种时尚。\n" +
                        "OK，也许他们并不是在做瑜伽，但他们的姿势和一些流行的瑜伽伸展有一些相似之处。",
                new int[]{R.drawable.comment_4_1, R.drawable.comment_4_2, R.drawable.comment_4_3,R.drawable.comment_4_4},
                2,3,4);
        Comment comment5 = new Comment("初五",
                R.drawable.comment_4_2,
                "呆萌呆萌的小猫咪，太可爱了！\n" +
                        "脸猫吃西瓜，啃了半天没吃到好心塞，哈哈！",
                new int[]{R.drawable.comment_5_1, R.drawable.comment_5_2, R.drawable.comment_5_3},
                2,3,4);
        Comment comment6 = new Comment("C+",
                R.drawable.comment_2_2,
                "可爱吗？",
                new int[]{R.drawable.comment_6_1},
                2,3,4);
        List<Comment> commentList = new ArrayList<>();
        commentList.add(comment1);
        commentList.add(comment2);
        commentList.add(comment3);
        commentList.add(comment4);
        commentList.add(comment5);
        commentList.add(comment6);


        MyAdapter_comment adapter = new MyAdapter_comment(getActivity(),commentList);
        doc_list.setAdapter(adapter);
        return root;
    }


}
