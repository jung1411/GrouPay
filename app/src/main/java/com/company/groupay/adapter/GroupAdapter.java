package com.company.groupay.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.company.groupay.GroupExpenses;
import com.company.groupay.R;
import com.company.groupay.models.Group;

import org.w3c.dom.Text;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupHolder> {

    private List<Group> groups;
    private Context context;

    public GroupAdapter(List<Group> groups, Context context) {
        this.groups = groups;
        this.context = context;
    }

    @NonNull
    @Override
    public GroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GroupHolder(LayoutInflater.from(context).inflate(R.layout.events,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull GroupHolder holder, int position) {
        final Group group = groups.get(position);
        holder.price.setText("$"+group.getGroup_cost());
        holder.count.setText(group.getGroup_number());
        holder.name.setText(group.getGroup_name());
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context,GroupExpenses.class).putExtra("NAME",group.getGroup_name()).putExtra("COUNT",group.getGroup_number())
                        .putExtra("COST",Double.valueOf(group.getGroup_cost())));
            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, GroupExpenses.class).putExtra("NAME",group.getGroup_name()).putExtra("COUNT",group.getGroup_number())
                        .putExtra("COST",Double.valueOf(group.getGroup_cost())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    class GroupHolder extends RecyclerView.ViewHolder{
        private TextView name,count,price;
        CardView card;
        LinearLayout layout;
        GroupHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_event_name);
            count = itemView.findViewById(R.id.txt_event_person_count);
            price = itemView.findViewById(R.id.txt_event_total_cost);

            card = itemView.findViewById(R.id.card_click);
            layout = itemView.findViewById(R.id.layout_click);
        }
    }

}
