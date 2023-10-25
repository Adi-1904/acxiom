package com.example.acxiom.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acxiom.R;
import com.example.acxiom.datamodel.Booking_dataModel;
import com.example.acxiom.datamodel.update_dataModel;
import com.example.acxiom.updatesetReminder;
import com.example.acxiom.viewHolder.update_ViewHolder;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class update_Adapter extends RecyclerView.Adapter<update_ViewHolder> {
    Context context;
    ArrayList<update_dataModel> userArraylist;

    public update_Adapter(Context context, ArrayList<update_dataModel> userArraylist) {
        this.context = context;
        this.userArraylist = userArraylist;
    }

    @NonNull
    @Override
    public update_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new update_ViewHolder(LayoutInflater.from(context).inflate(R.layout.booking_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull update_ViewHolder holder, int position) {
        holder.date.setText(String.valueOf(userArraylist.get(position).getDate()));
        holder.description.setText(String.valueOf(userArraylist.get(position).getDescription()));
        holder.email.setText(String.valueOf(userArraylist.get(position).getEmail()));
        holder.topic.setText(String.valueOf(userArraylist.get(position).getReason()));
        holder.sms.setText(String.valueOf(userArraylist.get(position).getContactno()));
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currpos=position;
                Intent intent=new Intent(v.getContext(), updatesetReminder.class);
                intent.putExtra("date",String.valueOf(userArraylist.get(currpos).getDate()));
                intent.putExtra("description",String.valueOf(userArraylist.get(position).getDescription()));
                intent.putExtra("email",String.valueOf(userArraylist.get(position).getEmail()));
                intent.putExtra("topic",String.valueOf(userArraylist.get(position).getReason()));
                intent.putExtra("sms",String.valueOf(userArraylist.get(position).getContactno()));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return userArraylist.size();
    }
}
