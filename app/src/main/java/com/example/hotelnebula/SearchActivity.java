package com.example.hotelnebula;

import androidx.appcompat.app.AppCompatActivity;
import Model.Menu;
import android.os.Bundle;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_search);
            Menu.setupBottomNavigationBar(this);
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();

        }
    }
}