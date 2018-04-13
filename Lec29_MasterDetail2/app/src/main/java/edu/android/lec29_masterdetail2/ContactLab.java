package edu.android.lec29_masterdetail2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018-03-23.
 * 연락처(Contact)의 리스트(ArrayList)를 관리하는 DAO 클래스
 */

public class ContactLab {
    private static final int[] IMAGE_IDS = {
            R.drawable.p1, R.drawable.p2, R.drawable.p3,
            R.drawable.p4, R.drawable.p5, R.drawable.p6,
            R.drawable.p7, R.drawable.p8, R.drawable.p9
    };

    private List<Contact> contactList = new ArrayList<>();
    private static ContactLab instance = null;

    private ContactLab() {
        makeDummyData();
    }

    private void makeDummyData() {
        for (int i = 0; i < 100; i++) {
            Contact contact = new Contact(i,
                    "Name " + i,
                    "Phone " + i,
                    "Email " + i,
                    IMAGE_IDS[i % IMAGE_IDS.length]);
            contactList.add(contact);
        }
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public static ContactLab getInstance() {
        if (instance == null) {
            instance = new ContactLab();
        }
        return instance;
    }
}













