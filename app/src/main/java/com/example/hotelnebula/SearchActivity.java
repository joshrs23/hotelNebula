package com.example.hotelnebula;

import androidx.appcompat.app.AppCompatActivity;
import Model.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class SearchActivity extends AppCompatActivity {

    ImageView imgGrandRoom, imgCozyRoom, imgPentHouse, imgSuiteRoom, imgSingleRoom, imgDoubleRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setContentView(R.layout.activity_search);
            Menu.setupBottomNavigationBar(this);
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();

        }

        initialize();

    }

    private void initialize() {
        imgGrandRoom = findViewById(R.id.img_grandRoom);
        imgCozyRoom = findViewById(R.id.img_cozyRoom);
        imgPentHouse = findViewById(R.id.img_pentHouseRoom);
        imgSuiteRoom = findViewById(R.id.img_suiteRoom);
        imgSingleRoom = findViewById(R.id.img_singleRoom);
        imgDoubleRoom = findViewById(R.id.img_doubleRoom);

        imgGrandRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("Grand", v);
            }
        });
        imgCozyRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("Cozy", v);
            }
        });
        imgPentHouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("Pent", v);
            }
        });
        imgSuiteRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("Suite", v);
            }
        });
        imgSingleRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("Single", v);
            }
        });
        imgDoubleRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendData("Double", v);
            }
        });
    }

    private void sendData(String roomType, View v) {
        //TODO change the second parameter to aldo's show available view
        //Intent intent = new Intent(this, UserInfoActivity.class);
        //intent.putExtra("RoomType", roomType);
        //startActivity(intent);

        Snackbar.make(v,roomType, Toast.LENGTH_LONG).show();
    }
}