package com.example.acxiom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.acxiom.adapter.delete_Adapter;
import com.example.acxiom.adapter.enable_Adapter;
import com.example.acxiom.datamodel.delete_dataModel;
import com.example.acxiom.datamodel.enable_dataModel;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class enable_reminder extends AppCompatActivity {
    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    TextView msg1;
    ArrayList<enable_dataModel> userArrayList;
    enable_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enable_reminder);
        firestore=FirebaseFirestore.getInstance();
        msg1=findViewById(R.id.msg);
        recyclerView=findViewById(R.id.enable_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(enable_reminder.this));
        userArrayList=new ArrayList<enable_dataModel>();
        adapter=new enable_Adapter(enable_reminder.this,userArrayList);
        recyclerView.setAdapter(adapter);
        EvenchangeListener();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(enable_reminder.this, Dashboard.class);
        startActivity(intent);
    }

    private void EvenchangeListener() {

        firestore.collection("enable").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("firebase error", error.getMessage());
                    return;
                }
                for (DocumentChange dc: value.getDocumentChanges())
                {
                    if(dc.getType()==DocumentChange.Type.ADDED) {
                        userArrayList.add(dc.getDocument().toObject(enable_dataModel.class));
                    }

                    if (!userArrayList.isEmpty())
                        msg1.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                }
            }
        });

    }
}