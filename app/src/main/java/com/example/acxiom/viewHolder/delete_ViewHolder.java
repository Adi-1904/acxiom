package com.example.acxiom.viewHolder;

import android.content.Intent;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acxiom.Dashboard;
import com.example.acxiom.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class delete_ViewHolder extends RecyclerView.ViewHolder {
    public TextView date;
    public TextView description;
    public TextView email;
    public TextView sms;
    public TextView topic;
    public ImageView delete;


    FirebaseFirestore firestore;
    String user;
    public delete_ViewHolder(@NonNull View itemView) {
        super(itemView);
        firestore=FirebaseFirestore.getInstance();
        date=itemView.findViewById(R.id.date);
        description=itemView.findViewById(R.id.description);
        email=itemView.findViewById(R.id.email);
        sms=itemView.findViewById(R.id.contact);
        topic=itemView.findViewById(R.id.reason);
        delete=itemView.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DocumentReference documentReference=firestore.collection("enable").document(date.getText().toString());
                DocumentReference documentReference1=firestore.collection("disable").document(date.getText().toString());
                firestore.collection("reminder").document(date.getText().toString()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(v.getContext(), "deleted", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(v.getContext(), Dashboard.class);
                        v.getContext().startActivity(intent);
                    }
                });
                documentReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                });
                documentReference1.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                });

            }
        });
    }
}
