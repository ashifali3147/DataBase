package com.example.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RCVAdapter extends RecyclerView.Adapter<RCVAdapter.MyViewHolder> {
    Context context;
    List<EMPModel> empModel;
    public RCVAdapter(Context context, List<EMPModel> empModel) {
        this.context = context;
        this.empModel = empModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.emp_id.setText(empModel.get(position).id);
        holder.emp_name.setText(empModel.get(position).name);
        holder.emp_number.setText(empModel.get(position).number);
        holder.emp_password.setText(empModel.get(position).password);
    }

    @Override
    public int getItemCount() {
        return empModel.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView emp_id, emp_name, emp_number, emp_password;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            emp_id = (TextView) itemView.findViewById(R.id.card_id);
            emp_name = (TextView) itemView.findViewById(R.id.card_name);
            emp_number = (TextView) itemView.findViewById(R.id.card_number);
            emp_password = (TextView) itemView.findViewById(R.id.card_password);
        }
    }
}
