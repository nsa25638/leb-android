package com.example.sec.lec38_asynctask1;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

// AsyncTask: Thread와 Handler의 기능을 갖고 있는 도우미 클래스(Helper class)
// 1) 내부적으로 작업 쓰레드(working thread)를 생성해서 실행함
//      -> doInBackground(): 작업 쓰레드해서 해야 할 일을 구현
// 2) 필요할 때마다 UI 쓰레드에서 호출되는 콜백 메소드들을 가지고 있음
//      -> onProgressUpdate(): UI 쓰레드에서 작업 쓰레드의 중간 결과를 업데이트할 때
//      -> onPostExecute(): UI 쓰레드에서 작업 쓰레드의 최종 결과를 업데이트할 때

// AsyncTask를 상속받는 클래스를 정의, 콜백 메소드들을 override
// AsyncTask<Params, Progress, Result>
// 1) Params:
//  메인 쓰레드에서 execute(...) 메소드를 호출할 때 매개변수로 전달되는 변수 타입
//  doInBackground(...) 메소드의 파라미터로 전달됨
// 2) Progress:
//  작업 쓰레드의 중간 결과(진행 상황)를 전달하기 위한 변수 타입
//  doInBackground() 메소드 내부에서 publishProgress(...) 메소드를 호출할 때
//  매개변수로 전달하는 변수 타입
//  onProgressUpdate(...) 메소드의 파라미터로 전달됨
// 3) Result:
//  작업 쓰레드의 최종 결과를 리턴할 때 사용되는 변수 타입
//  doInBackground() 메소드의 리턴 타입
//  onPostExecute() 메소드의 파라미터 타입

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "edu.android";

    // AsyncTask를 상속받는 내부 클래스를 정의
    class ProgressTask extends AsyncTask<Integer, Integer, Integer> {
        // 작업 쓰레드가 해야 할 일을 구현
        @Override
        protected Integer doInBackground(Integer... params) {
            Log.i(TAG, "doInBackground: param=" + params[0] + ", " + params[1]);
            int start = params[0];
            int end = params[1];
            progress = start;
            while (!isCancelled()) { // AsyncTask가 취소되지 않는 동안에
                publishProgress(progress); // 현재 진행상황 업데이트
                progress += 2; // 프로그레스바의 값을 변경(진행)
                if (progress >= end) {
                    break;
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return progress;
        }

        // 메인 쓰레드가 호출하는 콜백 메소드
        // 작업 쓰레드가 실행되기 전에 초기화 작업이 필요하면 override
        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute");
            super.onPreExecute();
        }

        // doInBackground() 메소드 내부에서 publishProgress()를 호출하면
        // 메인 쓰레드에서 호출하는 콜백 메소드 -> UI 업데이트가 가능
        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.i(TAG, "onProgressUpdate: value=" + values[0]);

            // 중간 진행 상황을 업데이트
            progressBar.setProgress(values[0]);
            textView.setText(values[0] + "%");
        }

        // doInBackground() 메소드가 종료됐을 때(작업 쓰레드가 정상 종료됐을 때),
        // 또는 작업 쓰레드가 비정상 종료가 됐을 때
        // 메인 쓰레드에서 호출하는 콜백 메소드 -> UI 업데이트가 가능
        @Override
        protected void onPostExecute(Integer result) {
            Log.i(TAG, "onPostExecute: result=" + result);

            progressBar.setProgress(result);
            textView.setText(result + "% 작업 종료됨");

            btnStart.setEnabled(true);
            btnCancel.setEnabled(false);
        }

        // AsyncTask의 cancel() 메소드를 호출하면(작업 쓰레드가 취소되면),
        // 메인 쓰레드에서 호출되는 콜백 메소드
        @Override
        protected void onCancelled(Integer result) {
            Log.i(TAG, "onCancelled: result=" + result);

            progressBar.setProgress(result);
            textView.setText(result + "%에서 취소됨");
    }
    } // end class ProgressTask

    private ProgressTask task;
    private int progress; // 프로그레스바의 진행 상황을 저장하는 변수
    private ProgressBar progressBar;
    private TextView textView;
    private Button btnStart, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);
        btnStart = findViewById(R.id.btnStart);
        btnCancel = findViewById(R.id.btnCancel);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startProgressTask();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelProgressTask();
            }
        });
    }

    private void cancelProgressTask() {
        if (task != null) {
            task.cancel(true);
        }

        btnStart.setEnabled(true);
        btnCancel.setEnabled(false);
    }

    private void startProgressTask() {
        Log.i(TAG, "Start 버튼 클릭");
        // AsyncTask를 상속받는 ProgressTask 인스턴스를 생성하고, 실행
        task = new ProgressTask();
        task.execute(0, 100);
        // execute() 메소드는 가변길이 매개변수(variable length parameter)를 갖음
        // -> execute() 메소드의 매개변수의 갯수는 변할 수 있다.

        btnStart.setEnabled(false);
        btnCancel.setEnabled(true);
    }
}

