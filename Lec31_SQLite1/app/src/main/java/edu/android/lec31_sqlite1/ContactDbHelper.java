package edu.android.lec31_sqlite1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static edu.android.lec31_sqlite1.Contact.ContactEntity.*;
import static edu.android.lec31_sqlite1.MainActivity.TAG;

/**
 * Created by user on 2018-03-27.
 * SQLiteOpenHelper 클래스를 상속받는 클래스
 * -> 1) 추상메소드(onCreate, onUpgrade)를 구현
 * -> 2) 생성자를 작성 - 부모 클래스의 매개변수가 있는 생성자를 직접 호출
 * 데이터베이스를 생성(create), 관리(upgrade, downgrade)하는 역할
 */

public class ContactDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "contact.db"; // 데이터베이스 이름
    private static final int DB_VERSION = 1; // 데이터베이스 버전 정보

    // create table contacts(
    //  _id integer primary key autoincrement,
    //  cname text,
    //  phone text,
    //  email text)
    private static final String CREATE_CONTACT_TABLE =
            "create table " + TABLE_NAME
            + " ("
            + _ID + " integer primary key autoincrement, "
            + COL_CNAME + " text, "
            + COL_PHONE + " text, "
            + COL_EMAIL + " text"
            + ")";

    public ContactDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.i(TAG, "ContactDbHelper 생성자");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "ContactDbHelper onCreate");
        // 데이터베이스가 사용할 테이블을 생성
        db.execSQL(CREATE_CONTACT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "ContactDbHelper onUpgrade");
    }
}









