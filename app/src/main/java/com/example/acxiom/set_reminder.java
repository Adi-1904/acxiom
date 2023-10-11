package com.example.acxiom;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class set_reminder extends AppCompatActivity {
    private FirebaseFirestore firestore;
    private int year, month, day; // Declare these variables

    private TextView dateEditText,date;
    private Spinner subjectSpinner;
    private EditText descriptionEditText;
    private EditText emailEditText;
    private EditText contactEditText;
    private EditText smsEditText;
    String strdate,description,email,contactno,smsno,reason;
    private CheckBox checkBox7Days;
    private CheckBox checkBox5Days;
    private CheckBox checkBox3Days;
    private CheckBox checkBox2Days;
    Button set;
    Map<String, Object> data = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_reminder);

        firestore = FirebaseFirestore.getInstance();

        // Initialize UI elements
        dateEditText=findViewById(R.id.textViewSelectDate);
        date=findViewById(R.id.textViewDate);
        subjectSpinner = findViewById(R.id.spinnerSubjects);
        descriptionEditText = findViewById(R.id.editTextDescription);
        emailEditText = findViewById(R.id.editTextEmail);
        contactEditText = findViewById(R.id.editTextContact);
        smsEditText = findViewById(R.id.editTextSMS);
        checkBox7Days = findViewById(R.id.checkBox7Days);
        checkBox5Days = findViewById(R.id.checkBox5Days);
        checkBox3Days = findViewById(R.id.checkBox3Days);
        checkBox2Days = findViewById(R.id.checkBox2Days);
        set=findViewById(R.id.set_reminder);
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                            description=descriptionEditText.getText().toString();
                            reason=subjectSpinner.getSelectedItem().toString();
                            email=emailEditText.getText().toString();
                            contactno=contactEditText.getText().toString();
                            data.put("date",strdate);
                            data.put("reason",reason);
                            data.put("description",description);
                            data.put("email",email);
                            data.put("contactno",contactno);

                        DocumentReference documentRef = firestore.collection("reminder").document(strdate);
                        documentRef.set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Intent intent = new Intent(set_reminder.this, Dashboard.class);
                                startActivity(intent);
                            }

                });
            }
        });

    }

    private void showDatePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDayOfMonth) {
                // Update the year, month, and dayOfMonth variables
                year = selectedYear;
                month = selectedMonth;
                day = selectedDayOfMonth;

                String selectedDate = year + "-" + (month + 1) + "-" + day;
                date.setText(selectedDate);
                strdate=date.getText().toString();
            }
        }, year, month, day);

        datePickerDialog.show();
    }
}