package edu.android.lec23_listview2;

/**
 * Created by user on 2018-03-21.
 * 아이템 하나에 대한 정보를 저장하는 Model 클래스
 */

public class Drink {
    private int id; // DB에서 primary key
    private String drinkName; // 음료수 이름
    private String description; // 음료수 설명
    private int price; // 음료수 가격
    private int imageId; // 음료수 이미지 리소스 아이디

    public Drink(int id, String drinkName, String description, int price, int imageId) {
        this.id = id;
        this.drinkName = drinkName;
        this.description = description;
        this.price = price;
        this.imageId = imageId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return drinkName + ", " + price + "원";
    }
}













