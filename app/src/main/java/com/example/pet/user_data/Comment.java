package com.example.pet.user_data;


import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobObject;

public class Comment extends BmobObject {
    private String user_name;
    private int user_icon_resid;
    private String doc_content;
    private int[] image_list;
    private int share_count;
    private int comment_count;
    private int like_count;


    public Comment(String user_name, int user_icon_resid, String doc_content, int[] image_list, int share_count, int comment_count, int like_count) {
        this.user_name = user_name;
        this.user_icon_resid = user_icon_resid;
        this.doc_content = doc_content;
        this.image_list = image_list;
        this.share_count = share_count;
        this.comment_count = comment_count;
        this.like_count = like_count;
    }

    public int getuser_icon_resid() {
        return user_icon_resid;
    }

    public void setuser_icon_resid(int user_icon) {
        this.user_icon_resid = user_icon_resid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getDoc_content() {
        return doc_content;
    }

    public void setDoc_content(String doc_content) {
        this.doc_content = doc_content;
    }

    public int[] getImage_list() {
        return image_list;
    }

    public void setImage_list(int[] image_list) {
        this.image_list = image_list;
    }

    public int getShare_count() {
        return share_count;
    }

    public void setShare_count(int share_count) {
        this.share_count = share_count;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }
}
