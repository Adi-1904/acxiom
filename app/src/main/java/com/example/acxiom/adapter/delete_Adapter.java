package com.example.acxiom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acxiom.R;
import com.example.acxiom.datamodel.Booking_dataModel;
import com.example.acxiom.datamodel.delete_dataModel;
import com.example.acxiom.delete_reminder;
import com.example.acxiom.viewHolder.delete_ViewHolder;

import java.util.ArrayList;

public class delete_Adapter extends RecyclerView.Adapter<delete_ViewHolder> {
    Context context;
    ArrayList<delete_dataModel> userArraylist;
    TextView yes,no;

    public delete_Adapter(Context context, ArrayList<delete_dataModel> userArraylist) {
        this.context = context;
        this.userArraylist = userArraylist;

    }



    @NonNull
    @Override
    public delete_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new delete_ViewHolder(LayoutInflater.from(context).inflate(R.layout.delete_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull delete_ViewHolder holder, int position) {
        holder.date.setText(String.valueOf(userArraylist.get(position).getDate()));
        holder.description.setText(String.valueOf(userArraylist.get(position).getDescription()));
        holder.email.setText(String.valueOf(userArraylist.get(position).getEmail()));
        holder.topic.setText(String.valueOf(userArraylist.get(position).getReason()));
        holder.sms.setText(String.valueOf(userArraylist.get(position).getContactno()));

    }

    @Override
    public int getItemCount() {
        return userArraylist.size();
    }
}
