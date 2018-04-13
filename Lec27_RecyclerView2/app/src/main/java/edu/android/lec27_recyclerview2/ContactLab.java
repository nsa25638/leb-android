package edu.android.lec27_recyclerview2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2018-03-22.
 * Contact의 리스트(ArrayList)를 관리하는 DAO 클래스
 */

public class ContactLab {
    private static final int[] IMAGE_IDS = {
            R.drawable.p1, R.drawable.p2, R.drawable.p3,
            R.drawable.p4, R.drawable.p5, R.drawable.p6,
            R.drawable.p7, R.drawable.p8, R.drawable.p9,
            R.drawable.android_1_5_cupcake,
            R.drawable.android_1_6_donut,
            R.drawable.android_2_0_eclair,
            R.drawable.android_2_2_froyo,
            R.drawable.android_2_3_ginerbread,
            R.drawable.android_3_0_honeycomb
    };

    private List<Contact> contactList;

    public List<Contact> getContactList() {
        return contactList;
    }

    private static ContactLab instance = null;

    private ContactLab() {
        contactList = new ArrayList<>();
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

    public static ContactLab getInstance() {
        if (instance == null) {
            instance = new ContactLab();
        }
        return instance;
    }
}

















