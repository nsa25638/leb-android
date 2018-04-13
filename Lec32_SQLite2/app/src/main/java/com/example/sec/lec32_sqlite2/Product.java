package com.example.sec.lec32_sqlite2;

import android.provider.BaseColumns;

/**
 * Created by user on 2018-03-27.
 * 데이터 (Model) 클래스 - 데이터베이스의 테이블
 */

public class Product {
    // 테이블의 이름, 테이블의 컬럼 이름들을 상수로 정의
    public static abstract class ProductEntity implements BaseColumns {
        // 테이블 이름
        public static final String TABLE_NAME = "products";
        // 테이블의 컬럼들의 이름
        public static final String COL_PNAME = "pname";
        public static final String COL_PRICE = "price";
        public static final String COL_DESC = "description";
    }

    private int productId; // primary key
    private String productName;
    private int price;
    private String description;

    public Product(int productId, String productName, int price, String description) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.description = description;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    @Override
    public String toString() {
        return String.format("Product{id: %d, name: %s, price: %d, desc: %s}",
                productId, productName, price, description);
    }
}











