package com.example.pet.ui.mypet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.pet.R;
import com.example.pet.fragment_page.Fragment_four;
import com.example.pet.fragment_page.Fragment_one;
import com.example.pet.fragment_page.Fragment_three;
import com.example.pet.fragment_page.Fragment_two;
import com.example.pet.my_class.ImageLoadBanner;
import com.example.pet.my_class.TabPageAdapter;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

public class mypetFragment extends Fragment {


    private TabLayout tabLt;
    private ViewPager pager;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mypet, container, false);

        tablayout_init(root);
        banner_init(root);


        return root;
    }
    private void banner_init(View v){
        Banner banner = v.findViewById(R.id.banner);
        ArrayList<Integer> imgs = new ArrayList<>();
        imgs.add(R.drawable.t1);
        imgs.add(R.drawable.t2);
        imgs.add(R.drawable.t3);
        imgs.add(R.drawable.t4);
        ArrayList<String> title = new ArrayList<>();
        title.add("");
        title.add("宠物培训");
        title.add("爱宠有家");
        title.add("萌宠与你");

        banner.setImages(imgs);
        banner.setImageLoader(new ImageLoadBanner());
        banner.setBannerTitles(title);
        banner.setDelayTime(2500);
        banner.isAutoPlay(true);
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setBannerAnimation(Transformer.Accordion);
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        banner.start();
    }
    private void tablayout_init(View v){
        //加载组件
        tabLt = v.findViewById(R.id.tabLt);
        pager = v.findViewById(R.id.pager);


        //tab与pager绑定
        //tabLt.setupWithViewPager(pager);
        //tablayout的显示模式;
        tabLt.setTabMode(TabLayout.MODE_FIXED);




        //pager轮播集合
        List<Fragment> list = new ArrayList<Fragment>();
        list.add(new Fragment_one());
        list.add(new Fragment_two());
        list.add(new Fragment_three());
        list.add(new Fragment_four());
        String[] mTabNames=new String[]{"猫粮","猫砂","玩具","零食"};
        TabPageAdapter pageAdapter = new TabPageAdapter(getChildFragmentManager(), list,mTabNames);
        //设置ViewPager的适配器
        pager.setAdapter(pageAdapter);
        tabLt.setupWithViewPager(pager);



    }



}
