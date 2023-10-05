package com.example.project22;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Routine_semester_adapter extends RecyclerView.Adapter<Myviewholder_routine_semester> {


    Context context;
    ArrayList<Routine_semester_model> data;

    public Routine_semester_adapter(Context context,ArrayList<Routine_semester_model> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @NotNull
    @Override
    public Myviewholder_routine_semester onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.retrieved_routine_semester,parent,false);
        return new Myviewholder_routine_semester(view);

    }

    //Brings data from model class and feeds in holder
    @Override
    public void onBindViewHolder(@NonNull @NotNull Myviewholder_routine_semester holder, int position) {

        Routine_semester_model routine_semester_model=data.get(position);

        holder.textDay.setText(routine_semester_model.getTextDay());
        holder.textSubject.setText(routine_semester_model.getTextSubject());
        holder.textTeacher.setText(routine_semester_model.getTextTeacher());
        holder.textTime.setText(routine_semester_model.getTextTime());

        //Button click to create task.
        holder.textClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     Intent intent = new Intent(context, Create_task.class);
                     intent.putExtra("textSubject",routine_semester_model.getTextSubject());
                     intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                     context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
