package com.example.hotelnebula;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class RegisterActivity extends AppCompatActivity {

    TextView txtFullName, txtUsername, txtEmail, txtPassword, txtConfirmPassword;
    Button btnCreateAccount, btnGoTOLogIn;
    DatabaseReference usersDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initialize();
    }

    private void initialize() {
        txtFullName = findViewById(R.id.edFullName);
        txtUsername = findViewById(R.id.edUsername);
        txtEmail = findViewById(R.id.edEmail);
        txtPassword = findViewById(R.id.edPassword);
        txtConfirmPassword = findViewById(R.id.edConfirmPassword);

        btnGoTOLogIn = findViewById(R.id.btnLogin);
        btnCreateAccount = findViewById(R.id.btnSignup);

        btnGoTOLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String id = txtFullName.getText().toString();
                    String name = txtUsername.getText().toString();
                    String age = txtEmail.getText().toString();
                    String password = txtPassword.getText().toString();
                    String confirmPassword = txtConfirmPassword.getText().toString();

                    Person person = new Person(Integer.valueOf(id), name, Integer.valueOf(age));
                    personDatabase.child(id).setValue(person);
                    Snackbar.make(view, "The person with id: " + id + " has been added successfully", Snackbar.LENGTH_LONG).show();
                    clearWidgets();
                }
                catch (Exception e ) {
                    Snackbar.make(view, e.getMessage(), Snackbar.LENGTH_LONG).show();
                }

            }
        });
    }
}