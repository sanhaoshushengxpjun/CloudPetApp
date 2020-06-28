package com.example.pet.user_data;

import cn.bmob.v3.BmobObject;

public class Product extends BmobObject {
    private String name;
    private String type;
    private double price;
    private int image_resId;
    private int pro_list_paynum;
    private String shopname;
    private String site;


    public Product(String name, String type, double price, int image_resId, int pro_list_paynum, String shopname, String site) {
        this.name = name;
        this.type = type;
        this.price = price;
        this.image_resId = image_resId;
        this.pro_list_paynum = pro_list_paynum;
        this.shopname = shopname;
        this.site = site;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getImage_resId() {
        return image_resId;
    }

    public void setImage_resId(int image_resId) {
        this.image_resId = image_resId;
    }

    public int getPro_list_paynum() {
        return pro_list_paynum;
    }

    public void setPro_list_paynum(int pro_list_paynum) {
        this.pro_list_paynum = pro_list_paynum;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
