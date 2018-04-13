package com.example.sec.lec32_sqlite2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.sec.lec32_sqlite2.Product.ProductEntity.*;

/**
 * Created by user on 2018-03-27.
 */

public class ProductDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "product.db";
    private static final int DB_VERSION = 1;
    // 테이블 생성 SQL 문장
    // create table products (
    // _id integer primary key autoincrement,
    // pname text,
    // price integer,
    // description text)
    private static final String SQL_CREATE_PRODUCT_TABLE =
            "create table " + TABLE_NAME
                    + " ("
                    + _ID + " integer primary key autoincrement, "
                    + COL_PNAME + " text, "
                    + COL_PRICE + " integer, "
                    + COL_DESC + " text"
                    + ")";

    public ProductDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 테이블 생성
        db.execSQL(SQL_CREATE_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO
    }
}







