package edu.android.lec25_listview4;

/**
 * Created by user on 2018-03-21.
 * 아이템 하나의 정보를 저장하는 데이터 클래스(Model 클래스)
 */

public class Product {
    private String productName;
    private int price;
    private String description;
    private int photoId;

    public Product(String productName, int price, String description, int photoId) {
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.photoId = photoId;
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










