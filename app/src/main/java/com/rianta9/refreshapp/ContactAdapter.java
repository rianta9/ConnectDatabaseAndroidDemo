package com.rianta9.refreshapp;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{
    private ArrayList<Contact> list;
    private MyOnItemClickListener myOnItemClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgContact;
        private TextView lblContactName;
        private TextView lblContactPhone;

        public ImageView getImgContact() {
            return imgContact;
        }

        public TextView getLblContactName() {
            return lblContactName;
        }

        public TextView getLblContactPhone() {
            return lblContactPhone;
        }

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgContact = itemView.findViewById(R.id.img_contact);
            lblContactName = itemView.findViewById(R.id.lbl_contact_name);
            lblContactPhone = itemView.findViewById(R.id.lbl_contact_phone);


        }


    }

    public ContactAdapter(ArrayList<Contact> list){
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Tạo layout để định nghĩa UI của item trong listView
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item_layout, parent, false);
        return new ViewHolder(itemView);
    }

    public void setOnItemClickListener(MyOnItemClickListener myOnItemClickListener){
        this.myOnItemClickListener = myOnItemClickListener;
    }

    // Thay thế nội dung của một view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Lấy phần tử từ vị trí position và replace nội dung của view với phần tử đó
        Contact contact = list.get(position);
        holder.getImgContact().setTextDirection(contact.getContactAvatar());
        holder.getLblContactName().setText(contact.getContactName());
        holder.getLblContactPhone().setText(contact.getPhoneNumber());


        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                myOnItemClickListener.onClick(list.get(position), position);
            }
        });
    }

    // return số lượng phần tử của arraylist
    @Override
    public int getItemCount() {
        return list.size();
    }

}
