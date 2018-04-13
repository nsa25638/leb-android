package edu.android.lec47_gson1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
    private static final String TAG = "lec47";
    private static final String OBJECT_FILE = "contact.json";
    private static final String ARRAY_FILE = "contacts.json";

    private TextView textView, textView2;
    // Java 객체 <---> JSON 기능을 갖는 Gson 변수를 선언
    private Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
    }

    public void onClickWriteObject(View view) {
        // Contact 인스턴스를 생성(Java 객체를 생성)
        Contact contact = new Contact(1, "오쌤", "010-1111-1111");

        // Java 객체(contact)를 JSON 형태의 문자열로 변환: Java --> JSON
        String jsonString = gson.toJson(contact);

        // 변환된 문자열을 textView에 씀
        textView.setText(jsonString);

        // JSON 문자열을 파일에 씀
        writeToFile(OBJECT_FILE, jsonString);
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

            Toast.makeText(this, "JSON 객체 파일 생성 성공", Toast.LENGTH_SHORT).show();
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

    public void onClickReadObject(View view) {
        // 파일에 저장된 JSON 포맷으로 되어있는 문자열을 읽음
        String jsonString = readFromFile(OBJECT_FILE);

        // JSON 포맷의 문자열을 Java 객체(contact)로 변환: JSON --> Java
        Contact contact = gson.fromJson(jsonString, Contact.class);

        textView2.setText(contact.toString());
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

    public void onClickWriteArray(View view) {
        // Contact를 저장하는 ArrayList를 생성, 10개 저장
        List<Contact> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            Contact contact = new Contact(i, "Name " + i, "Phone " + i);
            list.add(contact);
        }
        // ArrayList<Contact> 객체 --> JSON Array 포맷의 문자열
        String jsonString = gson.toJson(list);
        textView.setText(jsonString);

        // JSON 포맷 문자열을 파일에 씀
        writeToFile(ARRAY_FILE, jsonString);
    }

    public void onClickReadArray(View view) {
        // JSON Array 포맷으로 파일에 저장된 문자열을 읽음
        String jsonString = readFromFile(ARRAY_FILE);

        // JSON Array 포맷 --> ArrayList<Contact>
        TypeToken<ArrayList<Contact>> token =
                new TypeToken<ArrayList<Contact>>() {};

        gson.fromJson(jsonString, ArrayList.class);

    }
}








