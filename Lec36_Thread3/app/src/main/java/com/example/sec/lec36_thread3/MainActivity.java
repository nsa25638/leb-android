package com.example.sec.lec36_thread3;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_MSG = "key_current_time";
    // Handler를 상속받는 클래스를 작성
    private Handler handler = new Handler() {
        // Ctrl + O: override할 수 있는 메소드
        @Override
        public void handleMessage(Message msg) {
            // 타이머 클래스가 보내준 메시지(msg)를 확인해서 UI를 업데이트
            Bundle data = msg.getData();
            String curlime = data.getString(KEY_MSG);
            textView.setText(curlime);

        }
    }; // 익명 클래스


    private TextView textView;
    private Thread th; // 타이머 쓰레드

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
    }

    public void showToast(View view) {
        Toast.makeText(this, "토스트 메세지", Toast.LENGTH_SHORT).show();
    }

    public void startTimer(View view) {
        // Thread 생성
        // -> run() 메소드에서 무한루프
        // -> 무한루프 안에서 1초에 한번씩 TextView에 현재 시간을 씀
        // Thread 시작(start())
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        SimpleDateFormat sdf =
                                new SimpleDateFormat("yyyy/MM/dd MM:mm:ss");
                        String curTime = sdf.format(new Date());
                        textView.setText(curTime);
                        // 메인 쓰레드(UI Threadm 즉 NainActivity거 어난 더룬 쑤래두애서
                        // UI를 변경하려고 하면(TextVuew 변경, ImageView 변경, ...)
                        // ART은 어플을 강제 종료 시킴
                        // 메인 쓰레드가 아닌 별도의 쓰레드에서는 메인에게 변경할 UI 내용을
                        // HandLer ㅡㄹ래스를 사용해서 메시지를 보내면 됨

                        // 1) HandLer 객체에서 Message 객체를 가져옴
                        Message msg = handler.obtainMessage();
                        // 2) 메시지 객체에 데이터를 저장하기 위한 Bundle 객체를 생성
                        Bundle data = new Bundle();
                        // 3) Bundle 객체에 보낼 데이터를 저장
                        data.putString(KEY_MSG, curTime);
                        // 4) BUndle에 저장한 데이터를 Message 객체에 넣어줌
                        msg.setData(data);
                        // 5) HindLer 객체를 어플에서 메세지를 보냄
                        handler.sendMessage(msg);

                        synchronized (this) {
                            wait(1000);
                        }

                    } catch (InterruptedException e) {
                        Message msg = handler.obtainMessage();
                        Bundle data = new Bundle();
                        data.putString(KEY_MSG, "타이머 종료됨");
                        msg.setData(data);
                        handler.sendMessage(msg);
                        break; //  무한루프 종료
                    }

                } // 무한루프

            } // end run()
        });
        th.start();
    }

    public void stopTimer(View view) {
        // 생성된 타이머 쓰레드를 종료(interrupt)
        if (th != null) {
            th.interrupt();
        }
    }
}










