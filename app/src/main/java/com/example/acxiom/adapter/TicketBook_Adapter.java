package com.example.acxiom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.acxiom.datamodel.Booking_dataModel;
import com.example.acxiom.R;
import com.example.acxiom.viewHolder.TicketViewholder;

import java.util.ArrayList;

public class TicketBook_Adapter extends RecyclerView.Adapter<TicketViewholder> {
    Context context;
    ArrayList<Booking_dataModel> userArraylist;
    TextView yes,no;

    public TicketBook_Adapter(Context context, ArrayList<Booking_dataModel> userArraylist) {
        this.context = context;
        this.userArraylist = userArraylist;
    }

    @Override
    public TicketViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TicketViewholder(LayoutInflater.from(context).inflate(R.layout.booking_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewholder holder, int position) {
        holder.date.setText(String.valueOf(userArraylist.get(position).getDate()));
        holder.description.setText(String.valueOf(userArraylist.get(position).getDescription()));
        holder.email.setText(String.valueOf(userArraylist.get(position).getEmail()));
        holder.topic.setText(String.valueOf(userArraylist.get(position).getReason()));
        holder.sms.setText(String.valueOf(userArraylist.get(position).getContactno()));


//        holder.btn_cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(context,R.style.bottomsheetstyle);
//                bottomSheetDialog.setContentView(R.layout.dialog);
//                yes=bottomSheetDialog.findViewById(R.id.deleteyes);
//                no=bottomSheetDialog.findViewById(R.id.deleteno);
//                yes.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        holder.status.setImageResource(R.drawable.cancelled);
//                    }
//                });
//                no.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        bottomSheetDialog.dismiss();
//                    }
//                });
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return userArraylist.size();
    }
}
