package com.example.sec.lec48_gson2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lec48";
    private static final String FILE_NAME = "contact.json";

    private TextView textView, textView2;
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
    }

    public void onClickWriteJson(View view) {
        // Contact 객체를 생성 -> JSON Object 포맷 문자열로 변환
        List<Contact.Phone> phones = new ArrayList<>();
        Contact.Phone p1 = new Contact.Phone("모바일", "010-1111-1111");
        Contact.Phone p2 = new Contact.Phone("집", "02-1234-1111");
        phones.add(p1);
        phones.add(p2);

        Contact contact = new Contact(1, "오쌤", phones, "jake@itwill.com");

        String jsonString = gson.toJson(contact);
        textView.setText(jsonString);

        writeToFile(FILE_NAME, jsonString);
    }

    private void writeToFile(String fileName, String jsonString) {
        OutputStream out = null;
        OutputStreamWriter writer = null;
        BufferedWriter bw = null;
        try {
            out = openFileOutput(fileName, MODE_PRIVATE);
            writer = new OutputStreamWriter(out, "UTF-8");
            bw = new BufferedWriter(writer);
            bw.write(jsonString);

            Toast.makeText(this, "파일 생성 성공", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            try {
                bw.close();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    public void onClickReadJson(View view) {
        String jsonString = readFromFile(FILE_NAME);

        Contact contact = gson.fromJson(jsonString, Contact.class);
    }

    private String readFromFile(String fileName) {
        StringBuilder builder = new StringBuilder();
        InputStream in = null;
        InputStreamReader reader = null;
        BufferedReader br = null;
        try {
            in = openFileInput(fileName);
            reader = new InputStreamReader(in, "UTF-8");
            br = new BufferedReader(reader);
            String line = br.readLine();
            while (line != null) {
                builder.append(line);
                line = br.readLine();
            }

        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }

        return builder.toString();
    }
}












