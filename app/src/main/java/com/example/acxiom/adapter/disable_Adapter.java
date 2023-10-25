package com.example.acxiom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.acxiom.R;
import com.example.acxiom.datamodel.disable_dataModel;
import com.example.acxiom.datamodel.enable_dataModel;
import com.example.acxiom.viewHolder.disable_ViewHolder;
import com.example.acxiom.viewHolder.enable_ViewHolder;

import java.util.ArrayList;

public class disable_Adapter extends RecyclerView.Adapter<disable_ViewHolder> {
    Context context;
    ArrayList<disable_dataModel> userArraylist;

    public disable_Adapter(Context context, ArrayList<disable_dataModel> userArraylist) {
        this.context = context;
        this.userArraylist = userArraylist;

    }
    @NonNull
    @Override
    public disable_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new disable_ViewHolder(LayoutInflater.from(context).inflate(R.layout.disable_layout,parent,false)) ;
    }

    @Override
    public void onBindViewHolder(@NonNull disable_ViewHolder holder, int position) {
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
