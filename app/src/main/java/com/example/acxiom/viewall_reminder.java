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
import com.example.acxiom.datamodel.Booking_dataModel;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class viewall_reminder extends AppCompatActivity {
    FirebaseFirestore firestore;
    TextView msg1;
    RecyclerView recyclerView;
    ArrayList<Booking_dataModel> userArrayList;
    TicketBook_Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewall_reminder);
        msg1=findViewById(R.id.msg);
        recyclerView=findViewById(R.id.Ticket_RecyclerView);
        firestore=FirebaseFirestore.getInstance();
        recyclerView.setLayoutManager(new LinearLayoutManager(viewall_reminder.this));
        userArrayList=new ArrayList<Booking_dataModel>();
        adapter=new TicketBook_Adapter(viewall_reminder.this,userArrayList);
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
                        userArrayList.add(dc.getDocument().toObject(Booking_dataModel.class));
                    }
                    if (!userArrayList.isEmpty())
                        msg1.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }
}