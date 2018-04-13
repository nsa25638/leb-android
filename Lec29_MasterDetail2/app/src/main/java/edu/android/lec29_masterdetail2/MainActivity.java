package edu.android.lec29_masterdetail2;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements ContactListFragment.ContactSelectedCallback {

    // 화면이 Fragment로만 이루어져 있는지 (Single Pane - 스마트폰)
    // 화면이 Fragment와 FrameLayout으로 이루어져 있는 지 (Two Pane - 태블릿)를
    // 저장할 수 있는 멤버 변수
    private boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 레이아웃 xml 파일에 FrameLayout(id: container)이 있는 지 체크
        View view = findViewById(R.id.container);
        if (view != null) { // FrameLayout이 있는 경우
            twoPane = true;
        } else { // FrameLayout이 없는 경우
            twoPane = false;
        }

    }

    @Override
    public void onContactSelected(int position) {
        if (twoPane) { // 태블릿인 경우 -> 프래그먼트 교체
            // ContactDetailFragment를 생성해서
            // FrameLayout(id: container)에 끼워넣음
            ContactDetailFragment fragment =
                    ContactDetailFragment.newInstance(position);
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction transaction = fm.beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.commit();

//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.container, fragment)
//                    .commit();

        } else { // 스마트폰인 경우 -> 액티비티를 실행
            // ContactListFragment가 보내준 position 정보를
            // ContactDetailActivity를 시작시키면서 다시 전달
            Toast.makeText(this,
                    "pos:" + position, Toast.LENGTH_SHORT).show();

            Intent intent = ContactDetailActivity.newIntent(this, position);
            startActivity(intent);
        }

    }
}







