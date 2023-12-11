package com.example.hotelnebula;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import Model.Menu;

public class ProfileActivity extends AppCompatActivity implements  View.OnClickListener{

    TextView profilename,profileEmail,profilePass,profileusername;
    TextView titlename,titleusername;
    Button siginout,editprofile,deleteProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        try {
            setContentView(R.layout.activity_search);
            Menu.setupBottomNavigationBar(this);
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();

        }
        initialize();
    }

    private void initialize(){
        profilename = findViewById(R.id.profilename);
        profileEmail = findViewById(R.id.profileemail);
        profilePass =  findViewById(R.id.profilepass);
        profileusername = findViewById(R.id.profileusername);
        titlename = findViewById(R.id.titlename);
        titleusername =findViewById(R.id.titleusername);
        deleteProfile = findViewById(R.id.delete);
        siginout = findViewById(R.id.siginout);
        editprofile = findViewById(R.id.editprofile);

        editprofile.setOnClickListener(this);
        siginout.setOnClickListener(this);
        deleteProfile.setOnClickListener(this);
        showAllData();

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();
        if (id == R.id.editprofile)
            passUserData();
        if (id == R.id.siginout)
            singOut();
        if (id == R.id.delete)
            deleteProfile();
    }
    public void deleteProfile(){
        String usernameMain = profileusername.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkuserdata = reference.orderByChild("username").equalTo(usernameMain);
        checkuserdata.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    snapshot.child(usernameMain).getRef().removeValue();
                    Intent intent = new Intent(ProfileActivity.this,WelcomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public void singOut(){
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public Map<String, Object> getUserData(DataSnapshot dataSnapshot) {
        Map<String, Object> userData = new HashMap<>();

        // Specify the fields you want to retrieve
        String[] fields = {"name", "username", "email", "password"};

        for (String field : fields) {
            Object value = dataSnapshot.child(field).getValue();
            userData.put(field, value);
        }

        return userData;
    }
    public void showAllData(){
        Intent intent = getIntent();

        String name = intent.getStringExtra("name") != null ? intent.getStringExtra("name") : "Guest";
        String username = intent.getStringExtra("username") != null ? intent.getStringExtra("username") : "No Username Available";
        String email = intent.getStringExtra("email") != null ? intent.getStringExtra("email") : "No Email Available";
        String password = intent.getStringExtra("password") != null ? intent.getStringExtra("password") : "No Password Available";


        titlename.setText("Welcome " + name);
        titleusername.setText(username);
        profilename.setText(name);
        profileEmail.setText(email);
        profilePass.setText(password);
        profileusername.setText(username);

    }
    public void passUserData(){
        String usernameMain = profileusername.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkuserdata = reference.orderByChild("username").equalTo(usernameMain);

        checkuserdata.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String usernamedb = snapshot.child(usernameMain).child("username").getValue(String.class);
                    String passdb = snapshot.child(usernameMain).child("password").getValue(String.class);
                    String namedb = snapshot.child(usernameMain).child("name").getValue(String.class);
                    String emaildb = snapshot.child(usernameMain).child("email").getValue(String.class);

                    Intent intent =new Intent(ProfileActivity.this,EditProfileActivity.class);
                    intent.putExtra("name",namedb);
                    intent.putExtra("username",usernamedb);
                    intent.putExtra("password",passdb);
                    intent.putExtra("email",emaildb);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }


}