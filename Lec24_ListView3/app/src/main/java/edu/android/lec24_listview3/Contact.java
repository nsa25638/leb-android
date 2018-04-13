package edu.android.lec24_listview3;

/**
 * Created by user on 2018-03-21.
 * 아이템 하나에 대한 정보를 저장하는 데이터 클래스(Model 클래스)
 */

public class Contact {
    private String name;
    private String phone;
    private String email;
    private int photoId;

    public Contact(String name, String phone, String email, int photoId) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.photoId = photoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }
}













