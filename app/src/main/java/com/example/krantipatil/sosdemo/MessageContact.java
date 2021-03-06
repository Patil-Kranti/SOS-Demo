package com.example.krantipatil.sosdemo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MessageContact extends AppCompatActivity {
    public static final int PICK_CONTACT = 1;
    private static final String TAG = "MessageContact";
    public Uri uriContact;
    String phoneNo = null;
    String name = null;
    ArrayAdapter<String> adapter;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter listAdapter;
    private Button btnAdd;
    private ListView listView;
    private String contactID;
    private ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_contact);
        init();
    }

    public void init() {
        btnAdd = findViewById(R.id.btnAdd);
        listView = findViewById(R.id.list);
        arrayList = new ArrayList<String>();
        databaseHelper = new DatabaseHelper(getApplicationContext());
        sqLiteDatabase = databaseHelper.getReadableDatabase();
        cursor = databaseHelper.getAllData();

        showdata();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickContact(v);
            }
        });

    }


    public void pickContact(View v) {
        Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
        startActivityForResult(contactPickerIntent, PICK_CONTACT);


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // check whether the result is ok
        if (resultCode == RESULT_OK) {
            // Check for the request code, we might be usign multiple startActivityForReslut
            switch (requestCode) {
                case PICK_CONTACT:
                    contactPicked(data);
                    addContact();

                    break;
            }
        } else {
            Log.e("MainActivity", "Failed to pick contact");
        }
    }

    public void addContact() {
        databaseHelper.addContact(new ContactModel(name, phoneNo));

        Toast.makeText(this, "Data is updated", Toast.LENGTH_SHORT).show();
    }

    public void showdata() {
        List<ContactModel> contactModels = databaseHelper.getAllContacts();
        for (int i = 0; i < contactModels.size(); i++)

            Log.d(TAG, " Name:" + contactModels.get(i).getName() +
                    " PhoneNumber:" + contactModels.get(i).getPhoneNumber());

        listAdapter = new ListAdapter(getApplicationContext(), R.layout.single_row_item);
        listView.setAdapter(listAdapter);
        if (cursor.moveToFirst()) {
            do {
                String name, number;
                name = cursor.getString(0);
                number = cursor.getString(1);
                ContactModel contactModel = new ContactModel(name, number);
                listAdapter.add(contactModel);


            } while (cursor.moveToNext());
        }
        Toast.makeText(this, "Data is showen", Toast.LENGTH_SHORT).show();


    }

    /**
     * Query the Uri and read contact details. Handle the picked contact data.
     *
     * @param data
     */
    private void contactPicked(Intent data) {
        Cursor cursor = null;
        try {

            // getData() method will have the Content Uri of the selected contact
            Uri uri = data.getData();
            //Query the content uri
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            // column index of the phone number
            int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            // column index of the contact name
            int nameIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            phoneNo = cursor.getString(phoneIndex);
            name = cursor.getString(nameIndex);
            // Set the value to the textviews

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
