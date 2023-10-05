package com.example.project22;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class teachers_activity extends AppCompatActivity {
    RecyclerView recyclerView1;
    ArrayList<teacher_recyclerview_modelclass> arrlist;
    //teacher_recyclerview_modelclass[] array= new teacher_recyclerview_modelclass[100];
    teachers_adapter teachers_adapter ;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef,dataref ;
    String semester,section,email;
    String[] array = new String[20];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);
        recyclerView1 = findViewById(R.id.recyclerview1);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        arrlist = new ArrayList<>();

        teachers_adapter = new teachers_adapter(this, arrlist);
        recyclerView1.setAdapter(teachers_adapter);
        //section = Login.getActivityInstance()[4];

        myRef = database.getReference().child("Teacher");
        dataref = database.getReference().child("Users");


        email = Login.getActivityInstance()[1];


        searchdata();



        //arrlist.add(new teacher_recyclerview_modelclass("United Hospital","01564576543","46464"));





    }



    private void searchdata(){


        Query check = dataref.orderByChild("Email").equalTo(email);

        check.addChildEventListener(new ChildEventListener() {
            int i = 0;

            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    array[i] = String.valueOf(dataSnapshot.getValue());
                    Log.d("myTagnew", String.valueOf(array[i]));
                    i++;


                }
                section=array[4];
                semester=array[5];
                Log.d( "onCreate:",section);
                Log.d( "semester",semester);
                show(semester,section);


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

    private void show(String semester,String section) {

        String dbsem=null;

        if (semester.equals("1.1")) {
            dbsem ="s1_1";
        } else if (semester.equals("1.2")) {
            dbsem = "s1_2";
        } else if (semester.equals("2.1")) {
            dbsem = "s2_1";
        } else if (semester.equals("2.2")) {
            dbsem = "s2_2";
        } else if (semester.equals("3.1")) {
            dbsem = "s3_1";
        } else if (semester.equals("3.2")) {
            dbsem = "s3_2";
        } else if (semester.equals("4.1")) {
            dbsem = "s4_1";
        } else if (semester.equals("4.2")) {
            dbsem = "s4_2";
        }

        Log.d( "onCreate:",dbsem);


        myRef.child(dbsem).child(section).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
if(snapshot.exists()) {
    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
        teacher_recyclerview_modelclass trm = dataSnapshot.getValue(teacher_recyclerview_modelclass.class);
        arrlist.add(trm);
        //array=arrlist.toArray(array);

    }

    teachers_adapter.notifyDataSetChanged();
}
else Toast.makeText(teachers_activity.this, "No Valid Data Exists", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });



    }



}