package com.example.acxiom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Dashboard extends AppCompatActivity {
    Button set,modify,disable,enable,delete,viewall,logout;
    FirebaseAuth auth;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        set=findViewById(R.id.set_reminder);
        modify=findViewById(R.id.modify_reminder);
        disable=findViewById(R.id.disable_reminder);
        enable=findViewById(R.id.Enable_Reminder);
        delete=findViewById(R.id.delete_reminder);
        viewall=findViewById(R.id.view_your_Reminder);
        logout=findViewById(R.id.logout);
        auth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        Intent intent = getIntent();


        String email = intent.getStringExtra("email");

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this,set_reminder.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this,modify_reminder.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
        disable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this, disable_reminder.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
        enable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this,enable_reminder.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this,delete_reminder.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
        viewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Dashboard.this,viewall_reminder.class);
                intent.putExtra("email", email);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent1=new Intent(Dashboard.this,Login.class);
                startActivity(intent1);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(Dashboard.this,Login.class);
        startActivity(intent);
        finish();
    }
}