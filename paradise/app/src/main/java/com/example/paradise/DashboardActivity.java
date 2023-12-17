package com.example.paradise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DashboardActivity extends AppCompatActivity {

    private String name;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Terima informasi dari Intent
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");
    }

    public void goToProfile(View view){
        Intent intent = new Intent(DashboardActivity.this, ProfileActivity.class);
        intent.putExtra("name", name);
        intent.putExtra("email", email);
        startActivity(intent);
        finish();
    }

    public void goToHome(View view){
        Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}