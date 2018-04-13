package edu.android.lec33_sqlite3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static edu.android.lec33_sqlite3.MainActivity.TAG;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 1;
    private static final String SQL_CREATE_TABLE =
            "create table contacts(_id integer primary key autoincrement)";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Log.i(TAG, "DbHelper 생성자 호출");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "DbHelper onCreate 호출");
        try {
            db.execSQL(SQL_CREATE_TABLE);
            Log.i(TAG, "테이블 생성 성공");
        } catch (Exception e) {
            Log.i(TAG, "테이블 생성 실패: " + e.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String msg = String.format("onUpgrade(old: %d, new: %d) 호출",
                oldVersion, newVersion);
        Log.i(TAG, msg);

        // TODO:
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String msg = String.format("onDowngrade(old: %d, new: %d) 호출",
                oldVersion, newVersion);
        Log.i(TAG, msg);

        // TODO:
    }
}











