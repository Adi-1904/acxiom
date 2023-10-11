package com.example.acxiom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {
    EditText et_email,et_password;
    TextView create_account;
    String email,password;
    Button login;

    FirebaseAuth auth;
    FirebaseFirestore data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et_email=findViewById(R.id.Register_email);
        et_password=findViewById(R.id.Register_password);
        create_account=findViewById(R.id.Login);
        login=findViewById(R.id.register_btn);
        auth=FirebaseAuth.getInstance();
        data=FirebaseFirestore.getInstance();
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(register.this,register.class);
                startActivity(intent);

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=et_email.getText().toString();
                password=et_password.getText().toString();
                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String userid=auth.getCurrentUser().getUid();
                        Map<String,Object> details=new HashMap<>();
                        details.put("email",email);
                        data.collection("Reminder").document(userid).set(details).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                               Intent intent=new Intent(register.this,Dashboard.class);
                                intent.putExtra("email", email);
                               startActivity(intent);
                            }
                        });
                    }
                });

            }
        });



    }
}