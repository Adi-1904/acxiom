package com.example.acxiom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.acxiom.adapter.TicketBook_Adapter;
import com.example.acxiom.adapter.delete_Adapter;
import com.example.acxiom.datamodel.Booking_dataModel;
import com.example.acxiom.datamodel.delete_dataModel;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class delete_reminder extends AppCompatActivity {
    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    ArrayList<delete_dataModel> userArrayList;
    delete_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_reminder);
        firestore=FirebaseFirestore.getInstance();
        recyclerView=findViewById(R.id.delete_RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(delete_reminder.this));
        userArrayList=new ArrayList<delete_dataModel>();
        adapter=new delete_Adapter(delete_reminder.this,userArrayList);
        recyclerView.setAdapter(adapter);
        EvenchangeListener();
    }
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent=new Intent(delete_reminder.this, Dashboard.class);
        startActivity(intent);
    }
    private void EvenchangeListener() {

        firestore.collection("reminder").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.e("firebase error", error.getMessage());
                    return;
                }
                for (DocumentChange dc: value.getDocumentChanges())
                {
                    if(dc.getType()==DocumentChange.Type.ADDED) {
                        userArrayList.add(dc.getDocument().toObject(delete_dataModel.class));
                    }

                    adapter.notifyDataSetChanged();
                }
            }
        });

    }


}