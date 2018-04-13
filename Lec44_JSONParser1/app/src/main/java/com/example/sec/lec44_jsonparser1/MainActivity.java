package com.example.sec.lec44_jsonparser1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    // JSON 객체의 변수 이름으로 사용할 상수들을 정의
    private static final String JSON_VAR_ID = "id";
    private static final String JSON_VAR_NAME = "name";
    private static final String JSON_VAR_PHONE = "phone";
    private static final String JSON_VAR_EMAIL = "email";

    private TextView textView, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
    }

    public void writeJsonObject(View view) {
        try {
            // JSONObject 인스턴스 생성
            JSONObject jsonObj = new JSONObject();

            // JSON 객체는 name/value 쌍으로 데이터를 저장
            jsonObj.put(JSON_VAR_ID, 10000);
            jsonObj.put(JSON_VAR_NAME, "오쌤");
            jsonObj.put(JSON_VAR_PHONE, "010-1234-5678");
            jsonObj.put(JSON_VAR_EMAIL, "jake@itwill.com");

            // 생성된 JSON 객체의 내용을 TextView에서 확인
            textView.setText(jsonObj.toString());

            // JSON 객체를 파일에 씀
            writeToFile(jsonObj);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void writeToFile(JSONObject jsonObj) {
        OutputStream out = null;
        OutputStreamWriter writer = null;
        BufferedWriter bw = null;
        try {
            // openFileOutput(fileName, mode):
            // /data/data/앱패키지/files 폴더 아래에 write할 수 있는 FileOutputStream을 생성
            out = openFileOutput("", MODE_PRIVATE);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

// 프로그램 ==> OutputStream ==> 출력 장치(콘솔, 파일, 프린터, ...)
// 프로그램 ==> FileOutputStream ==> 파일
// 프로그램 ==> OutputStreamWriter ==> FileOutputStream ==> 인코딩된 문자열을 파일에 씀










