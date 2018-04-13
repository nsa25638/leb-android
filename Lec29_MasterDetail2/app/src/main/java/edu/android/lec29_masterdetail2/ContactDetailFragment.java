package edu.android.lec29_masterdetail2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactDetailFragment extends Fragment {

    private static final String ARG_CONTACT_INDEX = "selected_contact_index";

    private int index;
    private ImageView contactImage;
    private EditText contactName, contactPhone, contactEmail;
    private Button btn;

    public ContactDetailFragment() {
        // Required empty public constructor
    }

    // 팩토리 메소드(생성자 대신에 인스턴스를 생성하는 public static 메소드) 작성
    public static ContactDetailFragment newInstance(int index) {
        ContactDetailFragment fragment = new ContactDetailFragment();
//        fragment.setIndex(index);

        // 프래그먼트의 Arguments를 설정
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_CONTACT_INDEX, index);
        fragment.setArguments(bundle);

        return fragment;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 프래그먼트에 설정된 Arguments에서 데이터 (index)를 읽음
        Bundle args = getArguments();
        if (args != null); {
            index = args.getInt(ARG_CONTACT_INDEX);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_detail, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        contactImage = view.findViewById(R.id.detailPhoto);
        contactName = view.findViewById(R.id.detailName);
        contactPhone = view.findViewById(R.id.detailPhone);
        contactEmail = view.findViewById(R.id.detailEmail);
        btn = view.findViewById(R.id.btn);

        // 연락처 정보를 가지고 있는 ArrayList
        List<Contact> list = ContactLab.getInstance().getContactList();
        Contact contact = list.get(index);

        // 모든 View의 내용을 설정
        contactImage.setImageResource(contact.getPhotoId());
        contactName.setText(contact.getName());
        contactPhone.setText(contact.getPhone());
        contactEmail.setText(contact.getEmail());

    }
}








