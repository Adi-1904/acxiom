package com.example.acxiom.viewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acxiom.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.FirebaseFirestore;

public class TicketViewholder extends RecyclerView.ViewHolder {
    public TextView date;
    public TextView description;
    public TextView email;
    public TextView sms;
    public TextView topic;

    FirebaseFirestore firestore;
    String user;
    public TicketViewholder(@NonNull View itemView) {

        super(itemView);
        firestore=FirebaseFirestore.getInstance();
        date=itemView.findViewById(R.id.date);
        description=itemView.findViewById(R.id.description);
        email=itemView.findViewById(R.id.email);
        sms=itemView.findViewById(R.id.contact);
        topic=itemView.findViewById(R.id.reason);





    }
}
