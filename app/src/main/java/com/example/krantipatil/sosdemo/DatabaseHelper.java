package com.example.krantipatil.sosdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kranti Patil on 18-01-2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "contacts_manager";

    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "contacts";


    public static final String KEY_NAME = "name";

    public static final String KEY_PHONE_NUMBER = "Phone_number";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_NAME + " TEXT,"
                + KEY_PHONE_NUMBER + " TEXT" + ")";

        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addContact(ContactModel contactModel) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_NAME, contactModel.getName());
        contentValues.put(KEY_PHONE_NUMBER, contactModel.getPhoneNumber());

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    public List<ContactModel> getAllContacts() {

        ArrayList<ContactModel> contactModels = new ArrayList<>();
        String SELECT_CONTACTS = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_CONTACTS, null);
        if (cursor.moveToFirst()) {
            do {
                ContactModel contactModel = new ContactModel();
                contactModel.setName(cursor.getString(0));
                contactModel.setPhoneNumber(cursor.getString(1));

                contactModels.add(contactModel);
            } while (cursor.moveToNext());
        }

        return contactModels;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("Select * from " + TABLE_NAME, null);
        return result;
    }
    public int getContactCount() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String SELECT_QUERY = "SELECT * FROM " + TABLE_NAME;

        Cursor cursor = sqLiteDatabase.rawQuery(SELECT_QUERY, null);


        return cursor.getCount();

    }

}
