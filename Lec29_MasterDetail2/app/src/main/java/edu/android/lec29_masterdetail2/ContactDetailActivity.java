package edu.android.lec29_masterdetail2;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ContactDetailActivity extends AppCompatActivity {
    private static final String EXTRA_CONTACT_INDEX = "selected_contact_index";

    public static Intent newIntent(Context context, int index) {
        Intent intent = new Intent(context, ContactDetailActivity.class);
        intent.putExtra(EXTRA_CONTACT_INDEX, index);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        // MainActivity가 보낸 Intent를 찾아서,
        // 인텐트에 포함된 Extra 데이터(position 정보)를 찾음
        Intent intent = getIntent();
        int index = intent.getIntExtra(EXTRA_CONTACT_INDEX, 0);

        // ContactDetailActivity에 attach된 ContactDetailFragment에게
        // index 정보를 전달
        FragmentManager fm = getSupportFragmentManager();
        ContactDetailFragment fragment = (ContactDetailFragment)
                fm.findFragmentById(R.id.detailFragment);
        fragment.setIndex(index);
    }
}

