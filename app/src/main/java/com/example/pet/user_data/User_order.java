package com.example.pet.user_data;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Keep;

/**
 * @Author: GWJ
 * @Date: 2019-11-05
 * @Explain:
 */

@Entity
public class User_order {
    @Id
    private long id;
    private String name;
    private String type;
    private double price;
    private int count;
    private int order_image_resId;
    @Keep
    public User_order(long id, String name, String type, double price, int count,int order_image_resId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.price = price;
        this.count = count;
        this.order_image_resId = order_image_resId;
    }
    @Keep
    public User_order() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getCount() {
        return this.count;
    }
    public void setCount(int count) {
        this.count = count;
    }


    public int getOrder_image_resId() {
        return order_image_resId;
    }

    public void setOrder_image_resId(int order_image_resId) {
        this.order_image_resId = order_image_resId;
    }
}
