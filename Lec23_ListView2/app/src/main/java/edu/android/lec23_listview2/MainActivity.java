package edu.android.lec23_listview2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrinkLab lab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lab = DrinkLab.getInstance();
        List<Drink> data = lab.getDrinkList();

        // 레이아웃 xml 파일에 있는 ListView를 찾음
        ListView listView = findViewById(R.id.listView);

        // 데이터를 ListView에 끼워넣어줄 수 있는 Adapter를 생성
        ArrayAdapter<Drink> adapter = new ArrayAdapter<Drink>(
                this, // 컨텍스트 - 액티비티 정보
                android.R.layout.simple_list_item_1, // 아이템 하나의 레이아웃
                data); // 아이템으로 사용할 데이터(배열, List)

        // Adapter를 ListView에 설정해줌
        listView.setAdapter(adapter);

        // ListView에 이벤트 리스너를 설정
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // 클릭한 아이템(음료수)의 이름만 Toast 메시지로 출력
                String name = lab.getDrinkList().get(position).getDrinkName();
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
















