package com.example.krantipatil.sosdemo;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static boolean checker = true;
    MediaPlayer mediaPlayer;
    AudioManager am;
    private Button call, message, siren;
    private ImageView sos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        init();
    }

    private void init() {
        sos = findViewById(R.id.sos_img_button);
        siren = findViewById(R.id.siren_button);
        message = findViewById(R.id.message_button);
        call = findViewById(R.id.call_button);
        sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main2Activity.this, "sos", Toast.LENGTH_SHORT).show();
            }
        });
        siren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checker == true) {
                    am = (AudioManager) (getApplicationContext().getSystemService(Context.AUDIO_SERVICE));
                    mediaPlayer = MediaPlayer.create(Main2Activity.this, R.raw.siren);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                    Toast.makeText(Main2Activity.this, "siren on", Toast.LENGTH_SHORT).show();
                    checker = false;
                } else {
                    checker = true;
                    mediaPlayer.stop();
                    Toast.makeText(Main2Activity.this, "siren off", Toast.LENGTH_SHORT).show();
                }
            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main2Activity.this, "meassage", Toast.LENGTH_SHORT).show();
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Main2Activity.this, "call", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile)
        {
            Intent profile = new Intent(Main2Activity.this,profile.class);
            startActivity(profile);
        }
        else if (id == R.id.nav_add_contact)
        {
            Intent contact = new Intent(Main2Activity.this,Emeregency_contacts.class);
            startActivity(contact);

        }
        else if (id == R.id.nav_add_locations)
        {
            Intent location = new Intent(Main2Activity.this,Add_location.class);
            startActivity(location);

        } else if (id == R.id.nav_logout)
        {
            Intent logout = new Intent(Main2Activity.this,home.class);
            startActivity(logout);

        }

        else if (id == R.id.nav_share)
        {

        } else if (id == R.id.nav_send)
        {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
