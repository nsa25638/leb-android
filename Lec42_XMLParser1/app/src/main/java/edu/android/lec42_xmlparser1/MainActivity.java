package edu.android.lec42_xmlparser1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.sec.lec42_xmlparser1.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "lec42";

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
    }

    public void parseXML(View view) {
        InputStream in = null;
        InputStreamReader reader = null;
        BufferedReader br = null;
        try {
            // assets/contacts.xml 파일을 오픈
            in = getAssets().open("contacts.xml");
            reader = new InputStreamReader(in, "UTF-8");
            br = new BufferedReader(reader);

            ContactXmlPullParser contactParser = new ContactXmlPullParser();
            List<Contact> list = contactParser.getContacts(br);

            // TODO: list의 내용을 TextView에 씀


        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            try {
                br.close();
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        } // end try-catch-finally
    } // end parseXML()
}
