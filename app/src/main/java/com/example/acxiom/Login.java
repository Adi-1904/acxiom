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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login extends AppCompatActivity {
    EditText et_email,et_password;
    TextView create_account;
    String email,password;
    Button login;

    FirebaseAuth auth;
    FirebaseFirestore data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_login);
        et_email=findViewById(R.id.login_email);
        et_password=findViewById(R.id.login_password);
        create_account=findViewById(R.id.register);
        login=findViewById(R.id.login_btn);
        auth=FirebaseAuth.getInstance();
        data=FirebaseFirestore.getInstance();
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,register.class);
                startActivity(intent);

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email=et_email.getText().toString();
                password=et_password.getText().toString();
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(Login.this, Dashboard.class);
                            intent.putExtra("email", email);
                            startActivity(intent);
                        } else if (task.isCanceled())
                            Toast.makeText(Login.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });


    }
}