package com.rianta9.refreshapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ContactOpenHelper extends SQLiteOpenHelper {

    public  ContactOpenHelper(Context context){
        super(context, "contacts.db", null, 1);
    }

    public static final String TABLE = "contact";
    public static final String COLUM_ID = "id";
    public static final String COLUM_CONTACT_NAME = "contactname";
    public static final String COLUM_CONTACT_PHONE = "contactphone";
    public static final String COLUM_CONTACT_IMG = "contactimg";


    // khai báo các bảng ở đây
    // Khi chương trình tạo ra bảng rồi thì những lần sau không tạo ra bảng nữa
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "create table "+TABLE+"("+COLUM_ID+" integer primary key autoincrement, "+COLUM_CONTACT_NAME+" varchar, "+COLUM_CONTACT_PHONE+" varchar, "+COLUM_CONTACT_IMG+" integer)";
        Log.e("checksqlcreatetable",CREATE_TABLE);
        db.execSQL(CREATE_TABLE);
    }

    // Thay đổi những thứ như tên bảng tên cột thêm thuộc tính...v.v
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertContact(Contact contact){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase(); // Mở database để đọc
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUM_CONTACT_NAME, contact.getContactName());
        contentValues.put(COLUM_CONTACT_PHONE, contact.getPhoneNumber());
        contentValues.put(COLUM_CONTACT_IMG, contact.getContactAvatar());
        long result = sqLiteDatabase.insert(TABLE, null, contentValues);
        return  result;
    }

    public long updateContact(Contact contact){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase(); // Mở database để đọc
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUM_CONTACT_NAME, contact.getContactName());
        contentValues.put(COLUM_CONTACT_PHONE, contact.getPhoneNumber());
        contentValues.put(COLUM_CONTACT_IMG, contact.getContactAvatar());
        long result = sqLiteDatabase.update(TABLE, contentValues, COLUM_ID+"=?", new String[]{String.valueOf(contact.getId())});
        return  result;
    }

    public ArrayList<Contact> getAll(){
        ArrayList<Contact> result = new ArrayList<>();
        String SELECT = "select * from " + TABLE;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT, null);
        if(cursor.getCount()>0){
            Log.e("SL", String.valueOf(cursor.getCount()));
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                String id = cursor.getString(cursor.getColumnIndex("id"));
                String name = cursor.getString(cursor.getColumnIndex("contactname"));
                String phone = cursor.getString(cursor.getColumnIndex("contactphone"));
                String img = cursor.getString(cursor.getColumnIndex("contactimg"));
                result.add(new Contact(Integer.valueOf(id), phone, name, Integer.valueOf(img)));
                cursor.moveToNext();
            }
        }
        return result;
    }
}
