package edu.android.lec31_sqlite1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static edu.android.lec31_sqlite1.Contact.ContactEntity.*;

/**
 * Created by user on 2018-03-27.
 * DAO(Data Access Object): 데이터베이스의 테이블을 접근
 * select, insert, update, delete 기능들을 수행하는 클래스
 * ContactDbHelper(SQLiteOpenHelper) 클래스가 제공하는 데이터베이스 객체의
 * 메소드를 사용
 */

public class ContactDao {
    // SQLite 기능을 사용할 수 있는 클래스
    private SQLiteDatabase database;

    private static ContactDao instance = null;

    private ContactDao(Context context) {
        ContactDbHelper helper = new ContactDbHelper(context);
        database = helper.getWritableDatabase();
    }

    public static ContactDao getInstance(Context context) {
        if (instance == null) {
            instance = new ContactDao(context);
        }
        return instance;
    }

    public long insert(Contact contact) {
        long result = 0;

        ContentValues values = new ContentValues();
        values.put(COL_CNAME, contact.getCname());
        values.put(COL_PHONE, contact.getPhone());
        values.put(COL_EMAIL, contact.getEmail());

        // insert into contacts(cname) values(null)
        // insert into contacts(cname, phone, email) values(?, ?, ?)
        result = database.insert(
                TABLE_NAME,         // insert를 할 테이블 이름
                null, // 비어있는 ContentValues를 사용할 때만, "cname"
                values);    // 테이블에 insert할 key(컬럼이름)-value(값)
        // insert() 메소드의 리턴값: 삽입된 행 번호(row id)
        // insert가 실패한 경우에는 -1을 리턴

        return result;
    }

    public List<Contact> select() {
        List<Contact> list = new ArrayList<>();
        // select * from contacts
        // where 검색조건
        // group by 컬럼이름
        // having 그룹내에서 필터링할 조건
        // order by 정렬할 컬럼이름

        String[] columns = {_ID, COL_CNAME, COL_PHONE, COL_EMAIL};
        Cursor cursor = database.query(
                TABLE_NAME,         // select할 테이블 이름
                columns,            // select할 컬럼의 이름들(String 배열), 테이블에서 모든 컬럼을 검색하고자 할 때는 null을 사용해도 됨
                null,       // where절의 내용
                null,   // where절에 사용되는 값들
                null,       // group by에 사용될 컬럼
                null,       // having에 사용될 조건
                _ID);               // order by에 사용될 컬럼

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String email = cursor.getString(3);
            Contact contact = new Contact(id, name, phone, email);
            list.add(contact);
        }

        cursor.close();

        return list;
    }

    public Contact select(int id) {
        Contact contact = null;
        // select * from contacts where _id = ?

        String selection = _ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };
        Cursor cursor = database.query(TABLE_NAME, // select할 테이블 이름
                null, // 검색할 컬럼 이름들, null이면 모든 컬럼 검색
                selection, // where 구문의 내용
                selectionArgs, // where 구문을 채울 수 있는 값들
                null,
                null,
                null);
        if (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String phone = cursor.getString(2);
            String email = cursor.getString(3);
            contact = new Contact(id, name, phone, email);
        }

        cursor.close();

        return contact;
    }

    public int update(Contact contact) {
        int result = 0;
        // update cibtacts set cname = ?. phone = ?, email = ?
        // where_id = ?

        ContentValues values = new ContentValues();
        values.put(COL_CNAME, contact.getCname());
        values.put(COL_PHONE, contact.getPhone());
        values.put(COL_EMAIL, contact.getEmail());

        String whereClause = _ID + " =?";
        String[] whereArgs = { String.valueOf(contact.getCid()) };

        database.update(TABLE_NAME, values, whereClause, whereArgs);

        return result;
    }

    public int delete(int id) {
        int result = 0;
        // delete from contacts where _id = ?
        String whereClause = _ID + " = ?";
        String[] whereArgs = { String.valueOf(id) };

        database.delete(TABLE_NAME, whereClause, whereArgs);

        return result;
    }
}







