package com.example.hotelnebula;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

public class SearchSpecificActivity extends AppCompatActivity {

    private TextView selectedValueTextView;
    SeekBar optionsSeekBar;
    Button arrivalDateButton, departureDateButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_specific);
        initialize();

    }
    private void initialize() {
        optionsSeekBar = findViewById(R.id.optionsSeekBar);
        selectedValueTextView = findViewById(R.id.selectedValueTextView);

        optionsSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int selectedValue = progress + 1;
                selectedValueTextView.setText("Selected: " + selectedValue);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        arrivalDateButton = findViewById(R.id.arrivalDateButton);
        departureDateButton = findViewById(R.id.departureDateButton);

        arrivalDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(arrivalDateButton);
            }
        });

        departureDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker(departureDateButton);
            }
        });
    }

    private void showDatePicker(final Button dateButton) {
        MaterialDatePicker<Long> datePicker = MaterialDatePicker.Builder.datePicker().build();

        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener<Long>() {
            @Override
            public void onPositiveButtonClick(Long selection) {
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

                String selectedDate = sdf.format(new Date(selection));
                dateButton.setText(selectedDate);

            }
        });

        datePicker.show(getSupportFragmentManager(), datePicker.toString());
    }
}
