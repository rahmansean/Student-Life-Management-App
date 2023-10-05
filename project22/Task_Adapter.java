package com.example.project22;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Task_Adapter extends RecyclerView.Adapter<Task_MyViewHolder> {

    Context context;
    ArrayList<Task_Model> data;

    String email=Login.getActivityInstance()[1];
    String roll;
    DatabaseReference databaseReference1,databaseReference2;
    String[] array = new String[20];
    FirebaseDatabase firebaseDatabase;



    public Task_Adapter(Context context,ArrayList<Task_Model> data) {

        this.context = context;
        this.data = data;
    }

    @NonNull
    @NotNull
    @Override


    public Task_MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.user_task_retrieved_layout,parent,false);
        return new Task_MyViewHolder(view);
    }

    //Brings data from model class and feeds in holder
    @Override
    public void onBindViewHolder(@NonNull @NotNull Task_MyViewHolder holder, int position) {
       Task_Model task_model=data.get(position);

        holder.subject.setText(task_model.getSubject());
        holder.date.setText(task_model.getDate());
        holder.time.setText(task_model.getTime());
        holder.title.setText(task_model.getTitle());
        holder.details.setText(task_model.getDetails());

        searchdata();

        holder.taskdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder builder=new AlertDialog.Builder(holder.taskdelete.getContext());
                builder.setTitle("Delete Data");
                builder.setMessage("Delete?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String key =  data.get(position).getTitle();
                        databaseReference1.child(key).removeValue();
                        Toast.makeText(context, "Removed Successfully.", Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();


            }
        });
    }

    private void searchdata(){

        databaseReference2=FirebaseDatabase.getInstance().getReference("Users");
        Query check = databaseReference2.orderByChild("Email").equalTo(email);

        check.addChildEventListener(new ChildEventListener() {
            int i = 0;

            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    array[i] = String.valueOf(dataSnapshot.getValue());
                    Log.d("Task_adapter", String.valueOf(array[i]));
                    i++;
                }
                roll=array[2];
                Log.d( "Task_adapter_roll:",roll);
                show(roll);
            }

            @Override
            public void onChildChanged(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
            }
            @Override
            public void onChildRemoved(@NonNull @NotNull DataSnapshot snapshot) {
            }
            @Override
            public void onChildMoved(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {
            }
        });
    }

    public void show(String roll_here)
    {
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference1=firebaseDatabase.getReference().child("Task").child(roll);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
