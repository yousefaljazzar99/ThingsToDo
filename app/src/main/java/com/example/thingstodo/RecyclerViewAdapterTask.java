package com.example.thingstodo;


import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class RecyclerViewAdapterTask extends RecyclerView.Adapter<RecyclerViewAdapterTask.ViewHolder> {
    private Context context;
    private List<Task> taskList;

    public RecyclerViewAdapterTask(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterTask.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.task_row, viewGroup, false);

        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewAdapterTask.ViewHolder viewHolder, int position) {
        viewHolder.setData(taskList.get(position));
        final Task taskEntity = taskList.get(position);
        if (taskEntity.getIsChecked()) {
            viewHolder.checkBox.setChecked(true);
            viewHolder.checkBox.setPaintFlags(viewHolder.checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                taskEntity.setIsChecked(isChecked);
                viewHolder.checkBox.setSelected(isChecked);
                if (isChecked) {
                    viewHolder.checkBox.setText(taskEntity.getTaskName());
                    viewHolder.checkBox.setPaintFlags(viewHolder.checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                } else {
                    viewHolder.checkBox.setText(taskEntity.getTaskName());
                    viewHolder.checkBox.setPaintFlags(viewHolder.checkBox.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CheckBox checkBox;

        public ViewHolder(@NonNull View itemView, Context ctx) {
            super(itemView);
            context = ctx;

            checkBox = itemView.findViewById(R.id.checkBox);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Task task = taskList.get(position);
                    Intent intent = new Intent(context, ViewTaskActivity.class);
                    intent.putExtra("taskName", task.getTaskName());

                    context.startActivity(intent);
                }
            });

        }

        public void setData(Task task) {
            checkBox.setText(task.getTaskName());
            checkBox.setSelected(task.getIsChecked());
        }

    }
}
