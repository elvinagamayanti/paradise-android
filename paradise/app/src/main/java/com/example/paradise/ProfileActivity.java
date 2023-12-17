package com.example.paradise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView name_p, email_p;
    Button logout_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        name_p = findViewById(R.id.name_p);
        email_p = findViewById(R.id.email_p);

        String name = getIntent().getStringExtra("name");
        String email = getIntent().getStringExtra("email");

        name_p.setText("Nama : " + name);
        email_p.setText("Email : " + email);

        logout_btn = findViewById(R.id.logout_btn);
        logout_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logUserOut();
            }
        });
    }

    public void logUserOut(){
        name_p.setText(null);
        email_p.setText(null);

        Intent goToHome = new Intent(ProfileActivity.this, MainActivity.class);
        startActivity(goToHome);
        finish();
    }
}