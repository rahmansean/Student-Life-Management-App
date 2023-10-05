package com.example.project22;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class Task_MyViewHolder extends RecyclerView.ViewHolder {

    TextView  subject,date,time,title,details;
    ImageButton taskdelete;

    public Task_MyViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);

        subject=itemView.findViewById(R.id.task_retrieved_subject);
        date=itemView.findViewById(R.id.task_retrieved_date);
        time=itemView.findViewById(R.id.task_retrieved_time);
        title=itemView.findViewById(R.id.task_retrieved_title);
        details=itemView.findViewById(R.id.task_retrieved_details);

        taskdelete=itemView.findViewById(R.id.task_retrieved_delete);
    }
}
