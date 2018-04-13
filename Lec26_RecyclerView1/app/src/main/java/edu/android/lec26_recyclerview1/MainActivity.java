package edu.android.lec26_recyclerview1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "edu.android";

    private RecyclerView recycler;
    private String[] dataset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // strings.xml 파일에 정의된 문자열 배열을 찾음
        dataset = getResources().getStringArray(R.array.items);

        // RecyclerView를 찾음
        recycler = findViewById(R.id.recycler);

        // RecyclerView의 성능을 향상시키기 위한 옵션 설정
        // -> RecyclerView가 가지고 있는 각 아이템 뷰들의 크기가 일정
        recycler.setHasFixedSize(true);

        // RecyclerView는 LayoutManager를 반드시 설정해야 함
        recycler.setLayoutManager(new LinearLayoutManager(this));

        // RecyclerView가 사용할 Adapter를 생성하고,
        // Adapter를 RecyclerView에 설정
        ItemAdapter adapter = new ItemAdapter();
        recycler.setAdapter(adapter);
    }

    // RecyclerView.Adapter<VH>를 상속받는 내부 클래스를 정의
    class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
        // 데이터 하나를 보여주는 때 사용되는 뷰 레이아웃 정보를 갖고 있는
        // ViewHolder 객체를 리턴
        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Log.i(TAG, "onCreateViewHolder");

            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            View itemView = inflater.inflate(
                    android.R.layout.simple_list_item_1,
                    parent, false);

            ItemViewHolder holder = new ItemViewHolder(itemView);
            return holder;
        }

        // ViewHolder에 View들을 세팅해야 할 시점에 ART이 호출하는 메소드
        // -> 화면에 보여줄 아이템 정보를 ViewHolder 객체에 설정
        @Override
        public void onBindViewHolder(ItemViewHolder holder, int position) {
            Log.i(TAG, "onBindViewHolder");
            holder.textView.setText(dataset[position]);
        }

        // RecyclerView에 들어갈 아이템의 갯수를 리턴
        @Override
        public int getItemCount() {
            Log.i(TAG, "getItemCount");
            // 내부 클래스는 외부 클래스(MainActivity)의 멤버 변수를 사용할 수 있음
            // MainActivity가 가지고 있는 dataset의 배열 길이를 리턴
            return dataset.length;
        }

        // ViewHolder: 아이템 하나에 대한 View를 담고 있는, 재사용(recycle)되는 그릇
        // ItemAdapter가 사용할 ViewHolder 클래스를 정의
        class ItemViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            public ItemViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView;

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this,
                                textView.getText().toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        } // end class ItemViewHolder

    } // end class ItemAdapter

} // end class MainActivity







