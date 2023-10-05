package com.example.project22;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Routine_semester extends AppCompatActivity {


    String email = Login.getActivityInstance()[1];
    String section,semester;

    RecyclerView recyclerView;
    Routine_semester_adapter adapter;

    ArrayList<Routine_semester_model> list;

    //Firebase
    DatabaseReference databaseReference,databaseReference1;
    String[] array = new String[20];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_semester);

        recyclerView=findViewById(R.id.recyclerview_Routine);

        searchdata();

        list=new ArrayList<>();
        adapter=new Routine_semester_adapter(this,list);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchbar,menu);
        MenuItem item=menu.findItem(R.id.search);
        SearchView searchView=(SearchView) item.getActionView();
        searchView.setQueryHint("Search Day");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                processsearch(s);
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


    //Search bar part
    public  void processsearch(String s)
    {

        ArrayList<Routine_semester_model> mylist=new ArrayList<>();
        for(Routine_semester_model object:list) {
            if(object.getTextDay().toLowerCase().contains(s.toLowerCase()))
            {
                mylist.add((object));
            }

        }

        Routine_semester_adapter routine_semester_adapter=new Routine_semester_adapter(this,mylist);

        recyclerView.setAdapter(routine_semester_adapter);

    }

    private void searchdata(){

        databaseReference1=FirebaseDatabase.getInstance().getReference("Users");
        Query check = databaseReference1.orderByChild("Email").equalTo(email);

        check.addChildEventListener(new ChildEventListener() {
            int i = 0;

            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    array[i] = String.valueOf(dataSnapshot.getValue());
                    Log.d("Routine_semester", String.valueOf(array[i]));
                    i++;

                }
                section=array[4];
                semester=array[5];
                Log.d( "Routine_semester_sec:",section);
                Log.d( "Routine_semester_sec:",semester);
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


        if(semester.equals("1.1"))
        {
            semester="11";
        }
        else if(semester.equals("1.2"))
        {
            semester="12";
        }
        else if(semester.equals("2.1"))
        {
            semester="21";
        }
        else if(semester.equals("2.2"))
        {
            semester="22";
        }
        else if(semester.equals("3.1"))
        {
            semester="33";
        }
        else
        {
            semester="00";
        }

        databaseReference= FirebaseDatabase.getInstance().getReference("Routine").child(semester).child(section);

        //Retrieve firebase data
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                list.clear();
                //Fetch all data
                if(snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        //get data inside this object
                        Routine_semester_model routine_semester_model = dataSnapshot.getValue(Routine_semester_model.class);
                        list.add(routine_semester_model);

                    }
                    Toast.makeText(Routine_semester.this, "Data Loaded", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Routine_semester.this, "No Valid Data Exists", Toast.LENGTH_SHORT).show();
                    System.out.println("Not valid");
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });



    }

}
