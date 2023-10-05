package com.example.project22;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Cg_Show_result extends AppCompatActivity {

    RecyclerView recyclerView;
    Cg_show_Adapter cg_show_adapter;

    ArrayList<CgShow_model> list;

    DatabaseReference databaseReference1;

    String email=Login.getActivityInstance()[1];

    String[] array = new String[20];
    String roll;
    DatabaseReference databaseReference,databaseReference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cg_show_result);



        recyclerView = findViewById(R.id.cg_show_recyclerview);
        searchdata();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        //databaseReference1 = FirebaseDatabase.getInstance().getReference("Result").child(roll);


        list = new ArrayList<>();
        cg_show_adapter= new Cg_show_Adapter(this,list);
        recyclerView.setAdapter(cg_show_adapter);



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
                    Log.d("Cg_show_result", String.valueOf(array[i]));
                    i++;


                }
                roll=array[2];
                Log.d( "Show_cg_roll:",roll);
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

        databaseReference1 = FirebaseDatabase.getInstance().getReference("Result").child(roll);
        //Retrieve firebase data
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                list.clear();
                //Fetch all data
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        //get data inside this object
                        CgShow_model cgShow_model = dataSnapshot.getValue(CgShow_model.class);
                        list.add(cgShow_model);


                    }
                    Toast.makeText(Cg_Show_result.this, "Tasks Data Loaded", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Cg_Show_result.this, "No Valid Data Exists", Toast.LENGTH_SHORT).show();
                    System.out.println("Not valid");
                }

                cg_show_adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }
}