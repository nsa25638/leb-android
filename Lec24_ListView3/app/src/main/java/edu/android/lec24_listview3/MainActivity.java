package edu.android.lec24_listview3;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
//    public MainActivity() {
//        super();
//    }

    // 안드로이드에서 기본 제공하는 ArrayAdapter 클래스를 상속받는 클래스를 정의
    // ArrayAdapter 클래스는 기본 생성자가 없고, 매개변수가 있는 생성자들만 있음!
    public class ContactAdapter extends ArrayAdapter<Contact> {
        private Context context;
        private List<Contact> data;

        public ContactAdapter(Context context, List<Contact> data) {
            // 부모 클래스의 매개변수가 있는 생성자를 명시적으로 호출해야만 함!
            super(context, -1, data);
            this.context = context;
            this.data = data;
        }

        // 리스트뷰의 아이템 하나에 대한 레이아웃(View)을 생성해서 리턴하는 메소드
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // 레이아웃 xml 파일의 View들을 생성할 수 있는 LayoutInflater 객체 생성
            LayoutInflater inflater = LayoutInflater.from(context);
            // 리스트뷰에 그려질 아이템 하나의 View를 생성
            View view = inflater.inflate(R.layout.list_item, parent, false);

            // 아이템 뷰에 있는 이미지뷰와 텍스트뷰의 내용을 작성
            ImageView imageView = view.findViewById(R.id.imageView);
            imageView.setImageResource(data.get(position).getPhotoId());
            TextView textView = view.findViewById(R.id.textName);
            textView.setText(data.get(position).getName());

            return view;
        }
    }

    private List<Contact> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ListView에서 사용할 데이터들을 초기화
        data = ContactLab.getInstance().getContactList();

        // ListView를 찾음
        ListView listView = findViewById(R.id.listView);

        // ListView에 설정할 Adapter 클래스의 인스턴스를 생성
        ContactAdapter adapter = new ContactAdapter(this, data);
        // Adapter를 ListView에 설정
        listView.setAdapter(adapter);

        // ListView에 이벤트 리스너를 등록
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // DetailActivity를 시작시키기 위해서 Intent를 생성
                Intent intent =
                        DetailActivity.newIntent(MainActivity.this, position);
                startActivity(intent);
            }
        });

    }
}









