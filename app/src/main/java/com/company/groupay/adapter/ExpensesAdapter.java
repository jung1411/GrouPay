package com.company.groupay.adapter;

import android.annotation.SuppressLint;
import android.app.usage.UsageEvents;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.groupay.R;
import com.company.groupay.models.Events;

import java.util.List;

public class ExpensesAdapter extends RecyclerView.Adapter<ExpensesAdapter.ExpensesHolder>{

    private Context context;
    private List<Events> events;

    public ExpensesAdapter(Context context, List<Events> events) {
        this.context = context;
        this.events = events;
    }

    @NonNull
    @Override
    public ExpensesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExpensesHolder(LayoutInflater.from(context).inflate(R.layout.group_expenses,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ExpensesHolder holder, int position) {
        Events events1 = events.get(position);
        holder.name.setText(events1.getEvent_name());
        holder.cost.setText("$"+events1.getEvent_cost());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    class ExpensesHolder extends RecyclerView.ViewHolder{
        TextView name, cost;
        public ExpensesHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_activity_name);
            cost = itemView.findViewById(R.id.txt_activity_price);
        }
    }
}
