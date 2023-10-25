package com.example.acxiom.viewHolder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acxiom.R;
import com.google.firebase.firestore.FirebaseFirestore;

public class update_ViewHolder extends RecyclerView.ViewHolder {
    public TextView date;
    public TextView description;
    public TextView email;
    public TextView sms;
    public TextView topic;
    public RelativeLayout relativeLayout;
    FirebaseFirestore firestore;
    String user;
    public update_ViewHolder(@NonNull View itemView) {
        super(itemView);
        firestore=FirebaseFirestore.getInstance();
        date=itemView.findViewById(R.id.date);
        description=itemView.findViewById(R.id.description);
        email=itemView.findViewById(R.id.email);
        sms=itemView.findViewById(R.id.contact);
        topic=itemView.findViewById(R.id.reason);
        relativeLayout=itemView.findViewById(R.id.main_layout);
    }
}
