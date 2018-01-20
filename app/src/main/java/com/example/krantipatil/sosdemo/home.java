package com.example.krantipatil.sosdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import static com.example.krantipatil.sosdemo.Safely.sharedPreferences;

public class home extends AppCompatActivity {
    private Button signUp, signIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = Safely.getSharedPrefs();
        //Check();

        signIn = findViewById(R.id.Sign_in_h);
        signUp = findViewById(R.id.Sign_up_h);
        init();


    }

    private void init() {

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


    private void Check() {
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false);

        //Log.d(TAG,"isLoggedIn:"+isLoggedIn);
        if (isLoggedIn) {
            Intent helloIntent = new Intent(home.this, Main2Activity.class);
            startActivity(helloIntent);

        } else {
            setContentView(R.layout.activity_home);
        }
    }
}
