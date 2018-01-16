package com.example.krantipatil.sosdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Sign_in extends AppCompatActivity {
private Button logIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        init();
    }

    private void init() {
    logIn=findViewById(R.id.log_in);
    logIn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent  loginIntent = new Intent(Sign_in.this,MainActivity.class);
            startActivity(loginIntent);
        }
    });



    }

}
