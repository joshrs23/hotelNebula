package com.example.hotelnebula;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity implements  View.OnClickListener{

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initialize();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id==R.id.btnLogin) goToLogin();
    }
    private void initialize() {
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }
    private void goToLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}