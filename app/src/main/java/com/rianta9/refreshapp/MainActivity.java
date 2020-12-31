package com.rianta9.refreshapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ContactAdapter adapter;
    private ArrayList<Contact> list;
    private TextView lblViewContactName;
    private TextView lblViewContactPhone;
    private ContactOpenHelper contactOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        lblViewContactName = findViewById(R.id.lbl_view_contact_name);
        lblViewContactPhone = findViewById(R.id.lbl_view_contact_phone);

        //
        contactOpenHelper = new ContactOpenHelper(MainActivity.this);

        list = new ArrayList<>();
        list.add(new Contact("0389829122", "Quốc Khánh", R.drawable.user9));
        list.add(new Contact("0339829122", "Mạnh Trường", R.drawable.user9));
        list.add(new Contact("0399829122", "Quốc Anh", R.drawable.user9));
        list.add(new Contact("0313829122", "Quốc Nghĩa", R.drawable.user9));
        list.add(new Contact("0325829122", "Quốc Ngáo", R.drawable.user9));
        list.add(new Contact("0367829122", "Quốc Quần", R.drawable.user9));
        list.add(new Contact("0349929122", "Quốc Quân", R.drawable.user9));
        list.add(new Contact("0389429122", "Quốc Thuận", R.drawable.user9));
        list.add(new Contact("0389529122", "Quốc Khanh", R.drawable.user9));
        list.add(new Contact("0389329122", "Quốc Tế", R.drawable.user9));
        list.add(new Contact("0389829122", "Quốc Méo", R.drawable.user9));

        // Add vào database
        for (Contact contact:list){
            contactOpenHelper.insertContact(contact);
        }

        // Lấy dữ liệu từ database ra
        ArrayList<Contact> listContact = contactOpenHelper.getAll();

        // Cái này là để set kiểu hiển thị của listview theo chiều dọc....còn có một số kiểu khác nữa.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true); // sử dụng đối với listview có kích thước các item tương đồng để tăng độ mượt
        adapter = new ContactAdapter(listContact); // add dữ liệu vào adapter
        adapter.setOnItemClickListener(new MyOnItemClickListener() {
            @Override
            public void onClick(Contact contact, int position) {
                lblViewContactName.setText(contact.getContactName());
                lblViewContactPhone.setText(contact.getPhoneNumber());
                Toast.makeText(MainActivity.this, contact.getContactName(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        contactOpenHelper.close(); // đóng kết nối
        super.onDestroy();
    }
}