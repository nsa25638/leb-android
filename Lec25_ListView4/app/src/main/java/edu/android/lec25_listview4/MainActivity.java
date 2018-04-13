package edu.android.lec25_listview4;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    class ProductAdapter extends ArrayAdapter<Product> {
        private Context context;
        private List<Product> dataset;

        public ProductAdapter(@NonNull Context context,
                              @NonNull List<Product> objects) {
            // 부모 클래스인 ArrayAdapter<>는 기본 생성자를 갖고 있지 않음
            // -> 자식 클래스인 ProductAdapter에서는
            // 부모 클래스의 매개변수가 있는 생성자를 명시적으로 호출해야만 함!
            super(context, -1, objects);
            this.context = context;
            this.dataset = objects;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView,
                            @NonNull ViewGroup parent) {
            // 리스트 아이템 하나에 대한 뷰를 생성하고, 세팅
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.list_item, parent, false);
            // TODO
            return view;
        }
    }

    private List<Product> data;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ListView에서 사용할 데이터(ArrayList)를 초기화
        data = ProductLab.getInstance().getProductList();

        // ListView를 찾음
        listView = findViewById(R.id.listView);

        // ArrayAdapter<> 인스턴스를 생성
        ProductAdapter adapter = new ProductAdapter(this, data);
        // ArrayAdapter를 ListView에 설정
        listView.setAdapter(adapter);

        // ListView의 이벤트 리스너를 등록
    }
}
















