package com.example.sec.lec39_network1;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static java.net.HttpURLConnection.HTTP_OK;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "edu.android";

    private EditText editText;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
    }

    public void connectUrl(View view) {
        // ConnectivityManager를 ART에게 요청 ->
        // 인터넷을 사용할 수 있는 네트워크 자원(LTE, WiFi, ...)이 있는 지 검사
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable()) {
            Log.i(TAG, "사용 가능: " + networkInfo.getTypeName());

            // 사용가능한 네트워크가 있는 경우
            // EditText에서 URL을 읽고, URL 요청을 보내는 AsyncTask를 실행
            String url = editText.getText().toString();

            UrlRequestTask task = new UrlRequestTask();
            task.execute(url);

        } else {
            Toast.makeText(this, "사용가능한 네트워크가 없습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    // AsyncTask를 상속받는 내부 클래스 정의
    class UrlRequestTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            Log.i(TAG, "doInBackground: " + params[0]);
            String result = processUrl(params[0]);
            return result;
        }

        private String processUrl(String urlString) {
            StringBuilder builder = new StringBuilder();
            URL url = null;
            HttpURLConnection conn = null;
            InputStream in = null;
            InputStreamReader reader = null;
            BufferedReader br = null;
            try {
                // 1. URL 객체를 생성
                url = new URL(urlString);

                // 2. URL 객체를 사용해서 HttpURLConnection을 생성
                conn = (HttpURLConnection) url.openConnection();

                // 3. Connection 설정 - 선택
                conn.setConnectTimeout(10 * 1000);
                conn.setReadTimeout(10 * 1000);
                // 요청 방식(request method) 설정 - 필수
                conn.setRequestMethod("GET");

                // 연결(connection) 맺음
                conn.connect();

                // 서버에서 보내준 응답 코드(response code) 확인
                int resCode = conn.getResponseCode();
                if (resCode == HTTP_OK) { // 서버에서 정상적인 응답을 보내준 경우
                    in = conn.getInputStream();
                    reader = new InputStreamReader(in);
                    br = new BufferedReader(reader);

                    String line = br.readLine();
                    while (line != null) {
                        builder.append(line);
                        line = br.readLine();
                    }
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    br.close(); // BufferedReader 해제
                    conn.disconnect(); // Http Connection 해제
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            return builder.toString();
        }

        @Override
        protected void onPreExecute() {
            Log.i(TAG, "onPreExecute");
            super.onPreExecute();

            textView.setText(""); // 텍스트뷰 초기화
        }

        @Override
        protected void onPostExecute(String s) {
            Log.i(TAG, "onPostExecute");
            textView.setText(s); // doInBackground() 리턴한 문자열을 화면 출력
        }
    }

}









