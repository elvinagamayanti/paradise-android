package com.example.paradise;

import androidx.annotation.Nullable;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.paradise.helpers.StringHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText name, email, password;
    Button register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        register_btn = findViewById(R.id.register_btn);

        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processFormFields();
            }
        });
    }

    public void goToHome(View view){
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToLogin(View view){
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void processFormFields(){
        if(!validateName() || !validateEmail() || !validatePassword()){
            return;
        }

        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
        //The URL Posting to
        String url = "http://192.168.75.69:8080/register";

//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                if(response.equalsIgnoreCase("success")){
//                    name.setText(null);
//                    email.setText(null);
//                    password.setText(null);
//                    Toast.makeText(RegisterActivity.this, "Registrasi Berhasil!", Toast.LENGTH_LONG).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(RegisterActivity.this, "Registrasi Gagal!", Toast.LENGTH_LONG).show();
//            }
//        }){
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError{
//                Map<String, String> params = new HashMap<>();
//                params.put("name", name.getText().toString());
//                params.put("email", email.getText().toString());
//                params.put("password", password.getText().toString());
//                return params;
//            }
//        };
//        queue.add(stringRequest);

        HashMap<String, String> params = new HashMap<>();
        params.put("name", name.getText().toString());
        params.put("email", email.getText().toString());
        params.put("password", password.getText().toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.has("id")) {
                        // Registrasi berhasil
                        name.setText(null);
                        email.setText(null);
                        password.setText(null);
                        Toast.makeText(RegisterActivity.this, "Registrasi Berhasil!", Toast.LENGTH_LONG).show();
                    } else {
                        // Registrasi gagal atau respons tidak sesuai dengan yang diharapkan
                        String errorMessage = response.optString("error_message", "Registrasi Gagal!");
                        Toast.makeText(RegisterActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this, "Kesalahan dalam mengurai respons JSON", Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.networkResponse != null && error.networkResponse.data != null) {
                    String errorMessage = new String(error.networkResponse.data);
                    Toast.makeText(RegisterActivity.this, errorMessage, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Registrasi Gagal!", Toast.LENGTH_LONG).show();
                }
            }
        });

        queue.add(jsonObjectRequest);

    }

    public boolean validateName(){
        String name_n = name.getText().toString();

        if(name_n.isEmpty()){
            name.setError("Nama tidak boleh kosong!");
            return false;
        } else {
            name.setError(null);
            return true;
        }
    }

    public boolean validateEmail(){
        String email_e = email.getText().toString();

        if(email_e.isEmpty()){
            email.setError("Email tidak boleh kosong!");
            return false;
        }else if(!StringHelper.regexEmailValidationPattern(email_e)){
            email.setError("Email tidak valid!");
            return false;
        }else {
            email.setError(null);
            return true;
        }
    }

    public boolean validatePassword(){
        String password_p = password.getText().toString();

        if(password_p.isEmpty()){
            password.setError("Password tidak boleh kosong!");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }
}