package com.example.sec.lec32_sqlite2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import static com.example.sec.lec32_sqlite2.Product.ProductEntity.*;

/**
 * Created by user on 2018-03-27.
 * SQLiteDatabase 클래스의 insert, update, delete, query를 사용해서 기능들을 작성
 */

public class ProductDao {
    private SQLiteDatabase database;
    private static ProductDao instance = null;

    private ProductDao(Context context) {
        ProductDbHelper helper = new ProductDbHelper(context);
        database = helper.getWritableDatabase();
    }

    public static ProductDao getInstance(Context context) {
        if (instance == null) {
            instance = new ProductDao(context);
        }
        return instance;
    }

    public long insert(Product product) {
        // insert into products (pname, price, description) values(?, ?, ?)
        long result = 0;

        ContentValues values = new ContentValues();
        values.put(COL_PNAME, product.getProductName());
        values.put(COL_PRICE, product.getPrice());
        values.put(COL_DESC, product.getDescription());

        result = database.insert(TABLE_NAME, null, values);

        return result;
    }

    // products 테이블의 전체 레코드 검색
    public List<Product> select() {
        List<Product> list = new ArrayList<>();

        // select * from products order by _id
        Cursor cursor = database.query(
                TABLE_NAME,     // 검색할 테이블 이름
                null,   // 검색할 컬럼 이름들의 배열, null인 경우 모든 컬럼 검색
                null,       // where 구문
                null,  // where 구문을 완성시키는 값들
                null,   // group by 구문
                null,   // having 구문
                _ID);           // order by 구문에서 사용할 컬럼 이름
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String pname = cursor.getString(1);
            int price = cursor.getInt(2);
            String desc = cursor.getString(3);
            Product product = new Product(id, pname, price, desc);
            list.add(product);
        }
        cursor.close();

        return list;
    }

    // productId(고유키)로 검색
    public Product select(int id) {
        Product product = null;

        // select * from products where _id = ?
        String selection = _ID + " = ?";
        String[] selectionArgs = { String.valueOf(id) };
        Cursor cursor = database.query(
                TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (cursor.moveToNext()) {
            String pname = cursor.getString(1);
            int price = cursor.getInt(2);
            String desc = cursor.getString(3);
            product = new Product(id, pname, price, desc);
        }
        cursor.close();

        return product;
    }

    // 상품 이름 또는 설명으로 검색
    public List<Product> select(String keyword) {
        List<Product> list = new ArrayList<>();

        // select * from products
        // where pname like '%key%' or description like '%key%'
        // order by _id
        String selection = COL_PNAME + " like ? or " + COL_DESC + " like ?";
        String[] selectionArgs = { "%" + keyword + "%", "%" + keyword + "%" };
        Cursor cursor = database.query(
                TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                _ID);
        while (cursor.moveToNext()) {
            int productId = cursor.getInt(0);
            String productName = cursor.getString(1);
            int price = cursor.getInt(2);
            String desc = cursor.getString(3);
            Product product = new Product(productId, productName, price, desc);
            list.add(product);
        }
        cursor.close();

        return list;
    }

}