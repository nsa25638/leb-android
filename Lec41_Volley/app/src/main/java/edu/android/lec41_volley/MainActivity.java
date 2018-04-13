package edu.android.lec41_volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity
        implements Response.Listener<String>, Response.ErrorListener {

    private EditText editText;
    private TextView textView;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
    }

    // 버튼의 onClick 이벤트 리스너 메소드
    public void connectUrl(View view) {
        // 1) RequestQueue 인스턴스를 생성
        queue = Volley.newRequestQueue(this);

        // 2) 연결할 URL 주소 문자열
        String url = editText.getText().toString();

        // 3) StringRequest 객체를 생성하면서,
        // 요청(request)을 처리할 리스너를 등록
        StringRequest request =
                new StringRequest(
                        Request.Method.GET, // 요청 방식(request method)
                        url, // 요청 주소(request url)
                        this, // 요청에 대한 정상 응답이 도착했을 때 사용될 리스너
                        this); // 요청에 대해서 에러가 도착했을 때 사용될 리스너
    }

    @Override
    public void onResponse(String response) {

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}













