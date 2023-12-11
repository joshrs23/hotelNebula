package com.example.hotelnebula;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DataSnapshot;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity implements  View.OnClickListener{

    EditText loginUsername, loginPass;
    Button btnLogin,btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
    }

    private void initialize(){

        // Text Fields
        loginUsername = findViewById(R.id.usernamelogin);
        loginPass = findViewById(R.id.passwordlogin);
        // Buttons
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);
        // Onclick
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.btnLogin)
            checkUser();
        if (id == R.id.btnRegister)
            goToRegister();
    }

    private void goToRegister() {
        Intent intent =new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void checkUser(){
        String usernameLogin = loginUsername.getText().toString().trim();
        String passwordLogin = loginPass.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkuserdata = reference.orderByChild("username").equalTo(usernameLogin);

        checkuserdata.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                        String passFromDB = userSnapshot.child("password").getValue(String.class);

                        if(passFromDB.equals(passwordLogin)){

                            String namedb = userSnapshot.child("name").getValue(String.class);

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("name", namedb);
                            Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                            finish();
                            return;
                        }
                    }
                    Toast.makeText(LoginActivity.this, "Your password is wrong", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(LoginActivity.this, "The person does not exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}