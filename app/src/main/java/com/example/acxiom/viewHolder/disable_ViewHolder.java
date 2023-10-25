package com.example.acxiom.viewHolder;

import static android.service.controls.ControlsProviderService.TAG;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acxiom.R;
import com.example.acxiom.enable_reminder;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class disable_ViewHolder extends RecyclerView.ViewHolder {
    public TextView date;
    public TextView description;
    public TextView email;
    public TextView sms;
    public TextView topic;
    public Button disable;

    FirebaseFirestore firestore;
    String user;
    public disable_ViewHolder(@NonNull View itemView) {
        super(itemView);
        firestore=FirebaseFirestore.getInstance();
        date=itemView.findViewById(R.id.date);
        description=itemView.findViewById(R.id.description);
        email=itemView.findViewById(R.id.email);
        sms=itemView.findViewById(R.id.contact);
        topic=itemView.findViewById(R.id.reason);
        disable=itemView.findViewById(R.id.delete);
        Map<String,Object> data=new HashMap<>();

        disable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.put("date", date.getText().toString());
                data.put("reason", topic.getText().toString());
                data.put("description", description.getText().toString());
                data.put("email", email.getText().toString());
                data.put("contactno", sms.getText().toString());
                DocumentReference doc=firestore.collection("enable").document(date.getText().toString());
                doc.set(data).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d(TAG, "onSuccess: ");
                    }
                });
                firestore.collection("disable").document(date.getText().toString()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(v.getContext(), "enabled", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(v.getContext(), enable_reminder.class);
                        v.getContext().startActivity(intent);
                    }
                });
            }
        });
    }
}
