package com.example.krantipatil.sosdemo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.INTERNET;
import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.SEND_SMS;

public class Sign_in extends AppCompatActivity {
    public static final int RequestPermissionCode = 1;
    public EditText phonenumber, password;
    private Button logIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        phonenumber = findViewById(R.id.get_pn_sign_in);
        password = findViewById(R.id.get_password_sign_in);
        //sharedPreferences = Safely.getSharedPrefs();

        if (checkPermission()) {

            Toast.makeText(Sign_in.this, "All Permissions Granted Successfully", Toast.LENGTH_LONG).show();

        } else {

            requestPermission();
        }

        init();

    }

    private void init() {
        logIn = findViewById(R.id.log_in);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.log_in:
                        if (phonenumber.getText().toString().equalsIgnoreCase("1234") &&
                                password.getText().toString().equalsIgnoreCase("123")) {

                            //saveSharedPrefs();
                            Intent loginIntent = new Intent(Sign_in.this, Main2Activity.class);
                            startActivity(loginIntent);

                        }
                        break;

                }

            }
        });


    }
    /*private void saveSharedPrefs() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isLoggedIn",true);
        editor.commit();
    }*/

    private void requestPermission() {

        ActivityCompat.requestPermissions(Sign_in.this, new String[]
                {
                        SEND_SMS,
                        INTERNET,
                        ACCESS_COARSE_LOCATION,
                        ACCESS_FINE_LOCATION,
                        CALL_PHONE,
                        READ_CONTACTS,
                        READ_PHONE_STATE
                }, RequestPermissionCode);

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {

            case RequestPermissionCode:

                if (grantResults.length > 0) {

                    boolean smspermission = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadContactsPermission = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                    boolean internetPermission = grantResults[2] == PackageManager.PERMISSION_GRANTED;
                    boolean clocationPermission = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                    boolean flocationPermission = grantResults[4] == PackageManager.PERMISSION_GRANTED;
                    boolean callphonePermission = grantResults[5] == PackageManager.PERMISSION_GRANTED;
                    boolean ReadPhoneStatePermission = grantResults[6] == PackageManager.PERMISSION_GRANTED;


                    if (smspermission && ReadContactsPermission && ReadPhoneStatePermission && internetPermission && clocationPermission && flocationPermission && callphonePermission) {

                        Toast.makeText(Sign_in.this, "Permission Granted", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(Sign_in.this, "Permission Denied", Toast.LENGTH_LONG).show();

                    }
                }

                break;
        }
    }

    public boolean checkPermission() {

        int FirstPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), SEND_SMS);
        int SecondPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_CONTACTS);
        int ThirdPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), READ_PHONE_STATE);
        int FourthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), INTERNET);
        int FifthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_COARSE_LOCATION);
        int SixthPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), ACCESS_FINE_LOCATION);
        int SeventhPermissionResult = ContextCompat.checkSelfPermission(getApplicationContext(), CALL_PHONE);


        return FirstPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SecondPermissionResult == PackageManager.PERMISSION_GRANTED &&
                ThirdPermissionResult == PackageManager.PERMISSION_GRANTED &&
                FourthPermissionResult == PackageManager.PERMISSION_GRANTED &&
                FifthPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SixthPermissionResult == PackageManager.PERMISSION_GRANTED &&
                SeventhPermissionResult == PackageManager.PERMISSION_GRANTED;
    }


}
