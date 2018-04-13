package com.example.sec.lec32_sqlite2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProductDao dao;
    private EditText editId, editName, editPrice, editDesc;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dao = ProductDao.getInstance(getApplicationContext());

        editId = findViewById(R.id.editId);
        editName = findViewById(R.id.editNmae);
        editPrice = findViewById(R.id.editPrice);
        editDesc = findViewById(R.id.editDesc);
        textResult = findViewById(R.id.textResult);
    }

    public void insertProduct(View view) {
        String pname = editName.getText().toString();
        int price = Integer.parseInt(editPrice.getText().toString());
        String desc = editDesc.getText().toString();
        Product product = new Product(0, pname, price, desc);

        long result = dao.insert(product);
        textResult.setText("INSERT 결과: " + result);
    }

    public void selectAll(View view) {
        List<Product> list = dao.select();
        StringBuffer buffer = new StringBuffer();
        for (Product p : list) {
            buffer.append(p.toString()).append("\n");
        }
        textResult.setText(buffer.toString());
    }

    public void selectById(View view) {
        int id = Integer.parseInt(editId.getText().toString());
        Product p = dao.select(id);
        if (p != null) {
            textResult.setText(p.toString());
        } else {
            textResult.setText("해당 아이디의 상품이 없습니다...");
        }
    }

    public void selectByKeyword(View view) {
        String keyword = editName.getText().toString();
        List<Product> list = dao.select(keyword);
        StringBuffer buffer = new StringBuffer();
        for (Product p : list) {
            buffer.append(p.toString()).append("\n");
        }
        textResult.setText(buffer.toString());
    }
}





