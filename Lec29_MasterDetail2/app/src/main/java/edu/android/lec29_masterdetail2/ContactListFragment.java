package edu.android.lec29_masterdetail2;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactListFragment extends Fragment {
    interface ContactSelectedCallback {
        void onContactSelected(int position);
    }

    // 프래그먼트를 attach하고 있는 Activity의 주소를 저장
    private ContactSelectedCallback callback;
    private RecyclerView recycler;
    private List<Contact> dataset;


    public ContactListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ContactSelectedCallback) {
            callback = (ContactSelectedCallback) context;
        } else {
            new RuntimeException("반드시 ContactSelectedCallback을 구현해야 함");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);

        // 데이터 초기화
        dataset = ContactLab.getInstance().getContactList();

        // 프래그먼트의 레이아웃이 가지고 있는 RecyclerView를 찾음
        recycler = view.findViewById(R.id.contact_list);
        recycler.setHasFixedSize(true);

        // RecyclerView에 레이아웃 매니저 설정
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        // RecyclerView에 Adapter 설정
        ContactAdapter adapter = new ContactAdapter();
        recycler.setAdapter(adapter);

        return view;
    }

    class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View itemView = inflater.inflate(R.layout.contact_item, parent, false);
            ViewHolder holder = new ViewHolder(itemView);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            Contact contact = dataset.get(position);
            holder.contactPhoto.setImageResource(contact.getPhotoId());
            holder.contactName.setText(contact.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // MainActivity에게 position 정보를 전달
                    callback.onContactSelected(position);
                }
            });
        }

        @Override
        public int getItemCount() {
            return dataset.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            ImageView contactPhoto;
            TextView contactName;

            public ViewHolder(View itemView) {
                super(itemView);
                contactPhoto = itemView.findViewById(R.id.imageView);
                contactName = itemView.findViewById(R.id.textView);
            }
        } // end class ViewHolder
    } // end class ContactAdapter

} // end class ContactListFragment


