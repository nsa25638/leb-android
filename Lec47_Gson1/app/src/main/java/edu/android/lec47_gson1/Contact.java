package edu.android.lec47_gson1;

public class Contact {
    private int id;
    private String name;
    private String phone;

    public Contact(int id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "아이디: " + id + "\n"
                + "이름: " + name + "\n"
                + "전화번호: " + phone;
    }
}










