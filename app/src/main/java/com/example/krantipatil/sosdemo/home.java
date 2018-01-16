package com.example.krantipatil.sosdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.krantipatil.sosdemo.R;

public class home extends AppCompatActivity {
    private Button signUp,signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
    }

    private void init() {
        signIn=findViewById(R.id.Sign_in_h);
        signUp=findViewById(R.id.Sign_up_h);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent signinIntent = new Intent(home.this, Sign_in.class);
                startActivity(signinIntent);

            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupIntent = new Intent(home.this, Sign_up.class);
                startActivity(signupIntent);

            }
        });

    }
}
