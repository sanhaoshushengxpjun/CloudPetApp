package com.example.pet.user_data;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobGeoPoint;

//Created on 2018/11/22 18:01

public class User extends BmobUser {
   //昵称
    private String Realname;
    //年龄
    private Integer age;
    //性别
    private Integer gender;
    //用户当前位置
    private BmobGeoPoint address;
    //头像
    private BmobFile head_icon;

    public String getRealname() {
        return Realname;
    }

    public User setRealname(String nickname) {
        this.Realname = nickname;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public User setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Integer getGender() {
        return gender;
    }

    public User setGender(Integer gender) {
        this.gender = gender;
        return this;
    }

    public BmobGeoPoint getAddress() {
        return address;
    }

    public User setAddress(BmobGeoPoint address) {
        this.address = address;
        return this;
    }

    public BmobFile getHead_icon() {
        return head_icon;
    }

    public User setHead_icon(BmobFile head_icon) {
        this.head_icon = head_icon;
        return this;
    }
}

