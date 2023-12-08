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
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import model.Person;

public class LoginActivity extends AppCompatActivity {

    EditText loginusername, loginpass;
    Button loginbtn;
    TextView signuplink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
    }

    private void initialize(){

        loginusername = findViewById(R.id.usernamelogin);
        loginpass = findViewById(R.id.passwordlogin);
        loginbtn = findViewById(R.id.loginbtn);
        signuplink = findViewById(R.id.siguplink);

        signuplink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkuser();
            }
        });

    }

    public void checkuser(){
        String usernamelogin = loginusername.getText().toString().trim();
        String passwordlogin = loginpass.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        Query checkuserdata = reference.orderByChild("Username").equalTo(usernamelogin);

        checkuserdata.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String passFromDB = snapshot.child(usernamelogin).child("Password").getValue(String.class);
                    if(passFromDB.equals(passwordlogin)){
                        String usernamedb = snapshot.child(usernamelogin).child("Username").getValue(String.class);
                        String passdb = snapshot.child(usernamelogin).child("Password").getValue(String.class);
                        String namedb = snapshot.child(usernamelogin).child("Fullame").getValue(String.class);
                        String emaildb = snapshot.child(usernamelogin).child("Email").getValue(String.class);

                        Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("Fullname",namedb);
                        Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Your password is wrong", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(LoginActivity.this, "The person does not exist", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(LoginActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


}