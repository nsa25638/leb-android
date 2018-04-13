package edu.android.lec24_listview3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String EXTRA_CONTACT_INDEX = "contact_index";

    public static Intent newIntent(Context context, int index) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_CONTACT_INDEX, index);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        int index = intent.getIntExtra(EXTRA_CONTACT_INDEX, 0);
        Contact contact = ContactLab.getInstance().getContactList().get(index);

        ImageView photo = findViewById(R.id.imagePhoto);
        photo.setImageResource(contact.getPhotoId());

        TextView name = findViewById(R.id.textContactName);
        name.setText(contact.getName());

        TextView phone = findViewById(R.id.textContactPhone);
        phone.setText(contact.getPhone());

        TextView email = findViewById(R.id.textContactEmail);
        email.setText(contact.getEmail());

    }
}








