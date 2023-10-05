package com.example.project22;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

//Keeps reference of the layout
public class Myviewholder_routine_semester extends RecyclerView.ViewHolder {

    //This reference goes to adapter class
    TextView textDay,textSubject,textTeacher, textTime;
    Button textClick;


    public Myviewholder_routine_semester(@NonNull @NotNull View itemView) {
        super(itemView);

        textDay=itemView.findViewById(R.id.routine_day);
        textSubject=itemView.findViewById(R.id.routine_subject);
        textTeacher=itemView.findViewById(R.id.routine_teachername);
        textTime=itemView.findViewById(R.id.routine_time);

        textClick=itemView.findViewById(R.id.routine_click);
    }
}
