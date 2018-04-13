package com.example.sec.lec35_thread2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

    }

    public void showToast(View view) {
        Toast.makeText(this, "토스트 메세지", Toast.LENGTH_SHORT).show();
    }

    public void ChangeText(View view) {
        // Thread를 생성 -> run() 메소드에서 10초 대기 후 TextView로 변경
        // -> 쓰레드를 start()
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (this) {
                        wait(10 * 1000); // 10,000 ms = 10초
                    }
                    String text = textView.getText().toString();
                    if (text.equals(getResources().getString(R.string.msg1))) {
                        textView.setText(R.string.msg2);
                    } else {
                        textView.setText(R.string.msg1);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        th.start();
    }
}
