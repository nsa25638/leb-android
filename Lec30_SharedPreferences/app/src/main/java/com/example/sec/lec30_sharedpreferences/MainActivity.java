package com.example.sec.lec30_sharedpreferences;

import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_SHARED_PREF_ID = "shared_pref_id";

    private EditText editText;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveSharedPreferences();
            }
        });

        readSharedpregerences();
    }

    private void readSharedpregerences() {
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        String id = pref.getString(KEY_SHARED_PREF_ID, null);
        editText.setText(id);
    }

    private void saveSharedPreferences() {
        // SharedOreferebcesL
        // 앱이 사용하는 간단한 정보들(예: 환경 설정, 자동 로그인, ...)을
        // 파일에 저장할 떄 사용하는 클래스
        // 안드로이드 플랫폼이 관리 - 파일을 관리
        // 일반적으로는 어플이 생성한 SharedPreferences만 읽거나 갈 수 있고,
        // 다른 어플이 생성한 SharedPreferences는 읽거나 쓸 수 없음

        // 안드로이드 플랫폼이 관리해 주는 SharedOreferences 객체를 가져옴
        // getSharedorefecences(name, mode):
        //  sharedpreferences를 저장하는 파일의 이름을 저장하고, 여러개를 사용할 경우
        // getPreferences(mode):
        //  Sharedpreferences를 저장하는 파일의 이름을 저장하지 않고,
        //  하나의 파일만 사용하는 경우
        SharedPreferences pref = getPreferences(MODE_PRIVATE);
        String id = editText.getText().toString();
        boolean result = pref.edit() // SharedPreferencse 편집 시작
                .putString(KEY_SHARED_PREF_ID, id) // SharedPreferencse에 데이터 지정
                .commit(); // 지정된 내용을 파일 씀
        if (result) {
            Toast.makeText(this, "저장 성공", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "저장 실패", Toast.LENGTH_SHORT).show();
        }
    }
}
