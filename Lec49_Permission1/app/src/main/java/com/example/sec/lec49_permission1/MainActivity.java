package com.example.sec.lec49_permission1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sec.lec49_permission1.R;

public class MainActivity extends AppCompatActivity {

    private EditText editTelno, editSmsText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTelno = findViewById(R.id.editTelNo);
        editSmsText = findViewById(R.id.editSmsText);
    }

    public void useIntent(View view) {
        // 폰에 있는 SMS를 사용할 수 있는 Native App을 사용해서 SMS를 보내기
        // -> AndroidManifest.xml 파일에서 권한을 요청할 필요가 없음
        // -> 암시적인 인텐트(implicit intent)를 사용

        String telNo = editTelno.getText().toString();
        Uri uri = Uri.parse("smsto:" + telNo);

        Intent intent = new Intent(Intent.ACTION_SENDTO, uri); // 암시적 인텐트

        String smsText = editSmsText.getText().toString();
        intent.putExtra("sms_body", smsText);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "SMS를 사용할 수 있는 앱이 없습니다.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void iseSmsMethod(View view) {
        // 안드로이드 플렛폼에서 제공하는 SMS 메소드를 직접 사용
        // -> 권한 요청을 해야만 SMS 메소드를 사용할 수 있음

        String telNo = editTelno.getText().toString();
        String smstext = editSmsText.getText().toString();

        // 안드로이드 OS 상에서 실행중인 SmsManager를 가져옴
        SmsManager smsManager = SmsManager.getDefault();
        // SmsManager가 가지고 있는 send...()를 이용해서 SNS룰 보냄
        smsManager.sendTextMessage(telNo, null, smstext, null, null);

        Toast.makeText(this, "SMS 보내기 성공", Toast.LENGTH_SHORT).show();
    }
}


