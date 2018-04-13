package com.example.sec.lec30_masterdetail3;

/**
 * Created by SEC on 2018-03-26.
 * 데이터 (Model) 클래스
 */

public class Product {
    private int pid; // primory bay
    private String productName;
    private int price;
    private String description;
    private int photoId;

    // TODO: 셍성자, getter, setter


    public Product(int pid, String productName, int price, String description, int photoId) {
        this.pid = pid;
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.photoId = photoId;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
}
