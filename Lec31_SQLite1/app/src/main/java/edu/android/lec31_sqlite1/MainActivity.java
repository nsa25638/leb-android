package edu.android.lec31_sqlite1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "edu.android";

    private ContactDao dao;
    private EditText editId, editName, editPhone, editEmail;
//    private Button btnInsert, btnUpdate, btnDelete, btnSelectAll, btnSelectById;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "MainActivity onCreate");
        dao = ContactDao.getInstance(getApplicationContext());

        editId = findViewById(R.id.editId);
        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        editEmail = findViewById(R.id.editEmail);
        textResult = findViewById(R.id.textResult);
    }

    // 버튼의 onClick 이벤트 리스너 메소드
    public void insertContact(View view) {
        String name = editName.getText().toString();
        String phone = editPhone.getText().toString();
        String email = editEmail.getText().toString();
        Contact contact = new Contact(0, name, phone, email);
        long result = dao.insert(contact);

        textResult.setText("insert 결과: " + result);
    }

    public void selectAll(View view) {
        List<Contact> list = dao.select();
        StringBuffer buffer = new StringBuffer();
        for (Contact c : list) {
            buffer.append(c.toString()).append("\n");
        }
        textResult.setText(buffer.toString());
    }

    public void selectByid(View view) {
        int id = Integer.parseInt(editId.getText().toString());
        Contact c = dao.select(id);
        if (c != null) {
            editName.setText(c.getCname());
            editPhone.setText(c.getPhone());
            editEmail.setText(c.getEmail());
        } else {
            textResult.setText("해당 아이디는 연락처가 없습니다");
        }
    }
}






