package com.example.acxiom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.acxiom.adapter.TicketBook_Adapter;
import com.example.acxiom.adapter.update_Adapter;
import com.example.acxiom.datamodel.Booking_dataModel;
import com.example.acxiom.datamodel.update_dataModel;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class modify_reminder extends AppCompatActivity {
    FirebaseFirestore firestore;
    TextView msg1;
    RecyclerView recyclerView;
    ArrayList<update_dataModel> userArrayList;
    update_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_reminder);
        msg1=findViewById(R.id.msg);
        recyclerView=findViewById(R.id.Ticket_RecyclerView);
        firestore=FirebaseFirestore.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(modify_reminder.this));
        userArrayList=new ArrayList<update_dataModel>();
        adapter=new update_Adapter(modify_reminder.this,userArrayList);
        recyclerView.setAdapter(adapter);
        EvenchangeListener();
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
                    if(dc.getType()==DocumentChange.Type.ADDED)
                    {
                        userArrayList.add(dc.getDocument().toObject(update_dataModel.class));
                    }
                    if (!userArrayList.isEmpty())
                        msg1.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}