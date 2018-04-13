package com.example.sec.myapplication00;

/**
 * Created by SEC on 2018-03-22.
 */

public class ContactDao {
    private String Name;
    private int Phone;
    private String Email;

    public ContactDao(String name, int phone, String email) {
        Name = name;
        Phone = phone;
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPhone() {
        return Phone;
    }

    public void setPhone(int phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
