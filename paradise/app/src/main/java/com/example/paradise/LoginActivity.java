package com.example.paradise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.paradise.helpers.StringHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    Button login_btn;
    EditText email_l, password_l;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email_l = findViewById(R.id.email_l);
        password_l = findViewById(R.id.password_l);

        login_btn = findViewById(R.id.login_btn);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser();
            }
        });
    }

    public void authenticateUser(){
        if(!validateEmail() || !validatePassword()){
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        //The URL Posting to
        String url = "http://192.168.75.69:8080/login";

        HashMap<String, String> params = new HashMap<String, String>();
        params.put("email", email_l.getText().toString());
        params.put("password", password_l.getText().toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("LoginResponse", "onResponse: " + response.toString());
                try {
                    String name = (String) response.get("name");
                    String email = (String) response.get("email");
                    String accessToken = (String) response.get("accessToken");

//                    Intent goToProfile = new Intent(LoginActivity.this, ProfileActivity.class);
//                    goToProfile.putExtra("name", name);
//                    goToProfile.putExtra("email", email);
//
//                    startActivity(goToProfile);
//                    finish();

                    Intent goToDashboard = new Intent(LoginActivity.this, DashboardActivity.class);
                    goToDashboard.putExtra("name", name);
                    goToDashboard.putExtra("email", email);
                    startActivity(goToDashboard);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//                Toast.makeText(LoginActivity.this, "Login Gagal!", Toast.LENGTH_LONG).show();
                if (error instanceof AuthFailureError) {
                    // Otentikasi gagal (misalnya, email atau kata sandi salah)
                    Toast.makeText(LoginActivity.this, "Email atau kata sandi salah", Toast.LENGTH_LONG).show();
                } else {
                    // Kesalahan umum
                    Toast.makeText(LoginActivity.this, "Login Gagal! Silakan coba lagi.", Toast.LENGTH_LONG).show();
                }
            }
        });

        queue.add(jsonObjectRequest);
    }

    public void goToHome(View view){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToRegister(View view){
        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    public boolean validateEmail(){
        String email = email_l.getText().toString();

        if(email.isEmpty()){
            email_l.setError("Email tidak boleh kosong!");
            return false;
        }else if(!StringHelper.regexEmailValidationPattern(email)){
            email_l.setError("Email tidak valid!");
            return false;
        }else {
            email_l.setError(null);
            return true;
        }
    }

    public boolean validatePassword(){
        String password = password_l.getText().toString();

        if(password.isEmpty()){
            password_l.setError("Password tidak boleh kosong!");
            return false;
        } else {
            password_l.setError(null);
            return true;
        }
    }
}