package edu.android.lec45_jsonparser2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import static edu.android.lec45_jsonparser2.Contact.ContactJsonVariables.*; // 다른것들도 사용할 것이기 떄문에

public class MainActivity extends AppCompatActivity {
    private static final String FILE_NAME = "contact";

    private TextView textView, textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
    }

    public void onClickWrite(View view) {
        JSONArray jsonArray = makeJsonArray();

        textView.setText(jsonArray.toString());

        writeJsonArray(jsonArray);
    }

    private void writeJsonArray(JSONArray jsonArray) {
        OutputStream out = null;
        OutputStreamWriter writer = null;
        BufferedWriter bw = null;
        try {
            out = openFileOutput(FILE_NAME, MODE_PRIVATE);
            writer = new OutputStreamWriter(out, "UTF-8");
            bw = new BufferedWriter(writer);
            bw.write(jsonArray.toString());

            Toast.makeText(this, "파일 생성", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private JSONArray makeJsonArray() {
        // JSON 배열(Array)을 생성
        JSONArray jsonArray = new JSONArray();

        // Contact 인스턴스를 생성
        Contact c1 = new Contact(1, "오쌤", "010-1111-1111");
        Contact c2 = new Contact(2, "아이티윌", "010-2222-2222");
        Contact c3 = new Contact(3, "홍길동", "010-3333-3333");

        // Contact를 JSONObject로 변환한 객체를 JSON 배열에 저장
        jsonArray.put(c1.toJsonObj());
        jsonArray.put(c2.toJsonObj());
        jsonArray.put(c3.toJsonObj());

        return jsonArray;
    }

    public void onClickRead(View view) {
        InputStream in = null;
        InputStreamReader reader = null;
        BufferedReader br = null;
        StringBuilder builder = new StringBuilder();
        try {
            in = openFileInput(FILE_NAME);
            reader = new InputStreamReader(in, "UTF-8");
            br = new BufferedReader(reader);
            String line =  br.readLine();
            while (line != null) {
                builder.append(line);
                line = br.readLine();
            }

            // 파일에서 읽은 문자열을 JSINArray로 변환
            JSONArray jsonArray = new JSONArray(builder.toString());

            // KSON 배열의 원소 갯수만큼 반복하면서 JSON 객체를 꺼냄
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                int id = obj.getInt(VAR_ID);
                String name = obj.getString(VAR_NAME);
                String phone = obj.getString(VAR_PHONE);

                Contact contact = new Contact(id, name, phone);
                textView2.append(contact.toString());
                textView2.append("\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}






