package edu.android.lec27_recyclerview2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "edu.android";
    private int vhCreateCount = 0;
    private int vhBindCount = 0;

    private List<Contact> dataset;
    private RecyclerView recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // RecyclerView에서 사용할 데이터 초기화
        dataset = ContactLab.getInstance().getContactList();

        // RecyclerView를 찾음
        recycler = findViewById(R.id.recycler);

        // 옵션 설정 -> 성능 향상
        recycler.setHasFixedSize(true);

        // LayoutManger 설정
        recycler.setLayoutManager(new LinearLayoutManager(this));

        // RecyclerView.Adapter를 생성
        ContactAdapter adapter = new ContactAdapter();
        // Adapter를 RecyclerView에 설정
        recycler.setAdapter(adapter);
    }

    class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            vhCreateCount++;
            Log.i(TAG, "onCreateViewHolder: " + vhCreateCount);

            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
            View view = inflater.inflate(R.layout.recycler_item, parent, false);
            ViewHolder holder = new ViewHolder(view);

            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            vhBindCount++;
            Log.i(TAG, "onBindViewHolder: " + vhBindCount);

            final Contact contact = dataset.get(position);
            holder.imageView.setImageResource(contact.getPhotoId());
            holder.textView.setText(contact.getName());

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String msg = String.format("%s, %s, %s",
                            contact.getName(), contact.getPhone(), contact.getEmail());
                    Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();

                }
            });
        }

        @Override
        public int getItemCount() {
            return dataset.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            View itemView;
            ImageView imageView;
            TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);

                this.itemView = itemView;
                imageView = itemView.findViewById(R.id.imageView);
                textView = itemView.findViewById(R.id.textView);

//                itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(MainActivity.this,
//                                textView.getText().toString(),
//                                Toast.LENGTH_SHORT).show();
//                    }
//                });
            }
        } // end class ViewHolder

    } // end class ContactAdapter

} // end class MainActivity













