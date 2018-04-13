package edu.android.lec31_sqlite1;

import android.provider.BaseColumns;

/**
 * Created by user on 2018-03-27.
 * 데이터 (Model) 클래스
 */

public class Contact {
    // SQLite DB에서 사용할 테이블의 이름, 컬럼들의 이름 정의
    public static abstract class ContactEntity implements BaseColumns {
        // 테이블 이름
        public static final String TABLE_NAME = "contacts";
        // 테이블의 컬럼 이름들
        public static final String COL_CNAME = "cname";
        public static final String COL_PHONE = "phone";
        public static final String COL_EMAIL = "email";
        // primary key로 사용할 컬럼의 이름은 BaseColumns가 _ID라는 이름으로
        // 가지고 있기 때문에 따로 정의하지 않아도 됨
    }

    // 멤버 변수, 필드
    private int cid; // primary key(고유키)
    private String cname;
    private String phone;
    private String email;

    // 생성자
    public Contact() {}
    public Contact(int cid, String cname, String phone, String email) {
        this.cid = cid;
        this.cname = cname;
        this.phone = phone;
        this.email = email;
    }

    // getter/setter
    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
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

    @Override
    public String toString() {
        return String.format("%d | %s | %s | %s",
                cid, cname, phone, email);
    }
}













