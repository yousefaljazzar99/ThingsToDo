package com.example.thingstodo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Item> itemList;

    public RecyclerViewAdapter(Context context, List<Item> itemList){
        this.context = context;
        this.itemList = itemList;
    }
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {

        Item item = itemList.get(position);
        holder.item1.setText(item.getListName());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView item1;

        public ViewHolder(@NonNull View itemView, Context c) {
            super(itemView);
            context = c;
            item1 = itemView.findViewById(R.id.item1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Item item = itemList.get(position);
                    Intent intent = new Intent(context, TaskActivity.class);
                    intent.putExtra("ListName", item.getListName());

                    context.startActivity(intent);
                }
            });
        }
    }
}
