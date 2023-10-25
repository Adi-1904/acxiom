package com.example.acxiom;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class updatesetReminder extends AppCompatActivity {
    private FirebaseFirestore firestore;
    private int year, month, day; // Declare these variables
    RelativeLayout dateEditText;
    private TextView date;
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
        setContentView(R.layout.activity_updateset_reminder);
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
        Intent intent=getIntent();
        date.setText(intent.getStringExtra("date"));
        descriptionEditText.setText(intent.getStringExtra("description"));
        emailEditText.setText(intent.getStringExtra("email"));
        contactEditText.setText(intent.getStringExtra("sms"));
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                strdate=date.getText().toString();
                description = descriptionEditText.getText().toString();
                reason = subjectSpinner.getSelectedItem().toString();
                email = emailEditText.getText().toString();
                contactno = contactEditText.getText().toString();
                if (description != null && reason != null && email != null && contactno != null) {
                    data.put("date", strdate);
                    data.put("reason", reason);
                    data.put("description", description);
                    data.put("email", email);
                    data.put("contactno", contactno);
                    data.put("enable","1");

                    DocumentReference documentRef = firestore.collection("reminder").document(strdate);
                    documentRef.update(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(updatesetReminder.this, "Successfully updated", Toast.LENGTH_SHORT).show();
                        }
                    });
                    DocumentReference docdel=firestore.collection("disable").document(strdate);
                    DocumentReference doc=firestore.collection("enable").document(strdate);
                    doc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful())
                            {
                                DocumentSnapshot snapshot= task.getResult();
                                if(snapshot.exists())
                                {
                                    doc.update(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(updatesetReminder.this, "updated in enable", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                else {
                                    docdel.update(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(updatesetReminder.this, "updated in disable", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        }
                    });
//                    doc.set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void unused) {
//                            Intent intent1=new Intent(updatesetReminder.this, Dashboard.class);
//                            startActivity(intent1);
//                            finish();
//                        }
//                    });


                }
                else
                    Toast.makeText(updatesetReminder.this, "Fill All data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}