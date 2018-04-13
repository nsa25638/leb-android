package edu.android.lec33_sqlite3;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "edu.android";

    private Button btnGetDb, btnTableDesc;
    private TextView textView;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGetDb = findViewById(R.id.btnGetDb);
        btnTableDesc = findViewById(R.id.btnTableDesc);
        textView = findViewById(R.id.textView);

        Log.i(TAG, "MainActivity onCreate");
        DbHelper helper = new DbHelper(getApplicationContext());

        Log.i(TAG, "MainActivity getReadableDatabase() 호출 전");
        database = helper.getReadableDatabase();
        Log.i(TAG, "MainActivity getReadableDatabase() 호출 후");
    }
}







