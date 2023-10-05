package com.example.project22;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;

public class Show_task extends AppCompatActivity {

    RecyclerView recyclerView;
    Task_Adapter task_adapter;

    ArrayList<Task_Model> list;

    String email=Login.getActivityInstance()[1];

    String[] array = new String[20];
    String key,roll;

    DatabaseReference databaseReference1,databaseReference2;
    private FloatingActionButton floatingActionButton_task;
    TextView newSubject,newTitle,newDetails, newTime,newDate;
    Button newSave,newCancel;
    ImageButton newdateBtn,newTimeBtn;
    int hour, minute;
    DatePickerDialog datePickerDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);

        recyclerView = findViewById(R.id.recyclerview_show_task);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);


        searchdata();

        //databaseReference1 = FirebaseDatabase.getInstance().getReference("Task").child(roll);

        list = new ArrayList<>();
        task_adapter = new Task_Adapter(this, list);
        recyclerView.setAdapter(task_adapter);

        floatingActionButton_task=findViewById(R.id.floatingbutton_task);

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
                    Log.d("Show_task", String.valueOf(array[i]));
                    i++;


                }
                roll=array[2];
                Log.d( "Show_task_roll:",roll);
                show(roll);
                create(roll);
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

    public void create(String rollHere)
    {
        floatingActionButton_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Show_task.this, "Clicked", Toast.LENGTH_SHORT).show();
                key=roll;
                addTask();
            }
        });
    }

    public  void show(String rollHere)
    {

        databaseReference1 = FirebaseDatabase.getInstance().getReference("Task").child(rollHere);
        //Retrieve firebase data
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {

                list.clear();
                //Fetch all data
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        //get data inside this object
                        Task_Model task_model = dataSnapshot.getValue(Task_Model.class);
                        list.add(task_model);



                    }
                    Toast.makeText(Show_task.this, "Tasks Data Loaded", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Show_task.this, "No Valid Data Exists", Toast.LENGTH_SHORT).show();
                    System.out.println("Not valid");
                }

                task_adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
    }


    private void addTask() {

        AlertDialog.Builder myDialog=new AlertDialog.Builder(this);

        LayoutInflater inflater=LayoutInflater.from(this);

        View myView=inflater.inflate(R.layout.user_new_task_file,null);
        myDialog.setView(myView);

        AlertDialog dialog=myDialog.create();
        dialog.setCancelable(false);

        newSubject=myView.findViewById(R.id.new_subject);
        newDate=myView.findViewById(R.id.new_dateshow);
        newTime=myView.findViewById(R.id.new_timeshow);
        newTitle=myView.findViewById(R.id.new_title);
        newDetails=myView.findViewById(R.id.new_detail);

        newSave=myView.findViewById(R.id.new_savebutton);
        newCancel=myView.findViewById(R.id.new_cancelbutton);

        newdateBtn=myView.findViewById(R.id.new_datebutton);
        newTimeBtn=myView.findViewById(R.id.new_timebutton);

        newdateBtn.setOnClickListener(this::openDatePicker);
        newTimeBtn.setOnClickListener(this::popTimePicker);



        newCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        newSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String subject=newSubject.getText().toString().trim();
                String  date=newDate.getText().toString().trim();
                String  time=newTime.getText().toString().trim();
                String  title=newTitle.getText().toString().trim();
                String  details=newDetails.getText().toString().trim();


                if(TextUtils.isEmpty(subject))
                {
                    Toast.makeText(Show_task.this, "Field is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(date))
                {
                    Toast.makeText(Show_task.this, "Field is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(time))
                {
                    Toast.makeText(Show_task.this, "Field is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(title))
                {
                    Toast.makeText(Show_task.this, "Field is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(details))
                {
                    Toast.makeText(Show_task.this, "Field is empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {

                    DatabaseReference databaseReference3 = FirebaseDatabase.getInstance().getReference().child("Task");
                    Task_Model task_model = new Task_Model(key, subject, date, time, title, details);
                    databaseReference3.child(key).child(title).setValue(task_model);
                    Toast.makeText(Show_task.this, "Successfull", Toast.LENGTH_SHORT).show();
                    newSave.setEnabled(false);
                    dialog.dismiss();
                }

            }
        });


        dialog.show();

    }

    private void popTimePicker(View view) {

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                newTime.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));

            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, false);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    private void openDatePicker(View view) {
        DatePicker datePicker = new DatePicker(this);
        int currentDay = datePicker.getDayOfMonth();
        int currentMonth = (datePicker.getMonth()) + 1; //0+1
        int currenYear = datePicker.getYear();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                newDate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, currenYear, currentMonth, currentDay);

        datePickerDialog.show();

    }


}