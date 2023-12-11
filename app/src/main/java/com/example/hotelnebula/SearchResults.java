package com.example.hotelnebula;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class SearchResults extends AppCompatActivity implements View.OnClickListener{

    ImageButton btnBack;
    Button btnReserve;
    TextView tvDescription;
    ImageView ivRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        initilize();
    }

    private void initilize() {
        btnBack = findViewById(R.id.btnBack);
        btnReserve = findViewById(R.id.btnReserve);
        btnReserve.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("RoomType") && intent.hasExtra("ImageResource")) {
            String roomType = intent.getStringExtra("RoomType");
            String imageResource = intent.getStringExtra("ImageResource");
            int resID = getResources().getIdentifier(imageResource, "drawable", getPackageName());
            ivRoom.setImageResource(resID);
            //findRoom(intent);
        }
    }

//    private void findRoom(Intent intent) {
//            String arrivalDate = intent.getStringExtra("ArrivalDate");
//            String departureDate = intent.getStringExtra("DepartureDate");
//            DatabaseReference reservationsRef = FirebaseDatabase.getInstance().getReference("reservations");
//            DatabaseReference roomsRef = FirebaseDatabase.getInstance().getReference("rooms");
//
//            Query reservationsQuery = reservationsRef.orderByChild("date").startAt(arrivalDate).endAt(departureDate);
//            reservationsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    for (DataSnapshot reservationSnapshot : dataSnapshot.getChildren()) {
//                        String roomId = reservationSnapshot.child("roomId").getValue(String.class);
//                        Query availableRoomsQuery = roomsRef.orderByChild("availability").equalTo("available");
//                        availableRoomsQuery.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                boolean roomAvailable = true;
//                                String finalRoomId = "";
//                                for (DataSnapshot roomSnapshot : dataSnapshot.getChildren()) {
//                                    String availableRoomId = roomSnapshot.getKey();
//                                    if (availableRoomId.equals(roomId)) {
//                                        roomAvailable = false;
//                                        break;
//                                    }
//                                    finalRoomId = availableRoomId;
//                                }
//                                if (roomAvailable) {
//                                    tvDescription.setText(finalRoomId);
//                                } else {
//                                    tvDescription.setText("Sorry not rooms available with those parameters.");
//                                }
//                            }
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError databaseError) {
//                                Toast.makeText(SearchResults.this, databaseError.toString(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    }
//                }
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    Toast.makeText(SearchResults.this, databaseError.toString(), Toast.LENGTH_SHORT).show();
//                }
//            });
//    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.btnBack){
            finish();
        }
        if(id == R.id.btnReserve){
            reserveRoom();
        }
    }

    private void reserveRoom() {
    }
}