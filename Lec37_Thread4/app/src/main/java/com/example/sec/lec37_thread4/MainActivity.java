package com.example.sec.lec37_thread4;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String KEY_MSG = "key_current_progress";

    // Handler 클래스를 상속받는 클래스를 정의하고 생성
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // TODO: Message에서 데이터를 읽어서 ProgressBar와 TextView를 업데이트
            Bundle data = msg.getData();
            int progress = data.getInt(KEY_MSG);
            progressBar.setProgress(progress);
            textView.setText(progress + "%");
        }
    };

    private ProgressBar progressBar;
    private TextView textView;
    private Thread progThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);
    }

    public void startProgress(View view) {

        progThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int progress = 0;
                    while (progress <= 100) {
                        // Handler의 Message를 사용해서 progress 정보를 보냄
                        // progress를 증가 -> 잠깐 대기
                        Message msg = handler.obtainMessage();
                        Bundle data = new Bundle();
                        data.putInt(KEY_MSG, progress);
                        msg.setData(data);
                        handler.sendMessage(msg);

                        progress += 2;

                        synchronized (this) {
                            wait(100); // 100 ms = 0.1초
                        }

                    } // end while
                } catch (InterruptedException e) {
                    // TODO: 쓰레드를 interrupt해서 종료시킬 때
                }
            } // end run()
        });
        progThread.start();
    }

    public void CancelProgress(View view) {
        if (progThread != null) {
            progThread.interrupt();
        }
    }
}







