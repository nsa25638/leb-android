package com.example.sec.lec48_gson2;

import java.util.List;

public class Contact {
    private int id;
    private String name;
    private List<Phone> phones;
    private String email;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("아이디: ").append(id).append("\n")
                .append("이름: ").append(name).append("\n");
        builder.append("전화번호 리스트:\n");
        for (Phone p : phones) {
            builder.append("\t타입: ").append(p.phoneType).append("\n")
                    .append("\n번호: ").append(p.phoneNo).append("\n");
        }
        builder.append("이메일: ").append(email);

        return builder.toString();
    }

    public static class Phone {
        private String phoneType;
        private String phoneNo;

        public Phone(String phoneType, String phoneNo) {
            this.phoneType = phoneType;
            this.phoneNo = phoneNo;
        }
    }

    public Contact(int id, String name, List<Phone> phones, String email) {
        this.id = id;
        this.name = name;
        this.phones = phones;
        this.email = email;
    }
} // end class Contact
