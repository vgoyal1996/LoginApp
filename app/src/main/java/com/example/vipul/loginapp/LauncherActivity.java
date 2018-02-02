package com.example.vipul.loginapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser()==null)
            startActivity(new Intent(LauncherActivity.this,MainActivity.class));
        else {
            startActivity(new Intent(LauncherActivity.this, UserActivity.class));
        }
        finish();
    }
}
