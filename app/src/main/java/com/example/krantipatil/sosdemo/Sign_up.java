package com.example.krantipatil.sosdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.krantipatil.sosdemo.MainActivity;
import com.example.krantipatil.sosdemo.R;

public class Sign_up extends AppCompatActivity {
private Button Signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    init();
    }

    private void init() {
    Signup=findViewById(R.id.register);
    Signup.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent signupIntent=new Intent(Sign_up.this,MainActivity.class);
            startActivity(signupIntent);
        }
    });


    }
}
