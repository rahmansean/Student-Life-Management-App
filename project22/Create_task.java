package com.example.project22;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
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
import java.util.Locale;

public class Create_task extends AppCompatActivity {

    TextView taskTitle, taskDetail, taskDateShow, taskTimeShow;
    Button taskSave,taskCancel;
    DatePickerDialog datePickerDialog;
    ImageButton dateButton, timeButton;
    int hour, minute;
    EditText taskSubject;

    //String roll = Login.getActivityInstance()[2];
    String email=Login.getActivityInstance()[1];

    String[] array = new String[20];
    String key,roll;

    DatabaseReference databaseReference,databaseReference2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);


        taskSubject = findViewById(R.id.task_subject);
        taskTitle = findViewById(R.id.task_title);
        taskDetail = findViewById(R.id.task_detail);
        taskDateShow = findViewById(R.id.task_dateshow);
        taskTimeShow = findViewById(R.id.task_timeshow);

        taskSave = findViewById(R.id.task_savebutton);
        dateButton = findViewById(R.id.task_datebutton);
        timeButton = findViewById(R.id.task_timebutton);
        taskCancel=findViewById(R.id.task_cancelbutton);

        //dateButton.setText(getTodaysDate());


        taskSubject.setText(getIntent().getStringExtra("textSubject"));

        dateButton.setOnClickListener(this::openDatePicker);
        timeButton.setOnClickListener(this::popTimePicker);

        searchdata();


        databaseReference = FirebaseDatabase.getInstance().getReference().child("Task");

        /*taskSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addTask();
            }
        });*/
        taskCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(Create_task.this,Routine_semester.class);
                startActivity(intent);
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
                    Log.d("Create_task", String.valueOf(array[i]));
                    i++;

                }
                roll=array[2];

                Log.d( "Create_Task_roll:",roll);
                Task_save(roll);

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


    private void Task_save(String roll) {
        taskSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key=roll;
                addTask();
            }
        });
    }


    public void openDatePicker(View view) {

        DatePicker datePicker = new DatePicker(this);
        int currentDay = datePicker.getDayOfMonth();
        int currentMonth = (datePicker.getMonth()) ; //0+1
        int currenYear = datePicker.getYear();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                taskDateShow.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        }, currenYear, currentMonth, currentDay);

        datePickerDialog.show();


    }

    public void popTimePicker(View view) {

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                taskTimeShow.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));

            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, false);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    private void addTask() {

        String subject, date, time, title, details;
        subject = taskSubject.getText().toString().trim();
        date = taskDateShow.getText().toString().trim();
        time = taskTimeShow.getText().toString().trim();
        title = taskTitle.getText().toString().trim();
        details = taskDetail.getText().toString().trim();


        if (TextUtils.isEmpty(subject)) {
            Toast.makeText(Create_task.this, "Field is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(date)) {
            Toast.makeText(Create_task.this, "Field is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(time)) {
            Toast.makeText(Create_task.this, "Field is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(title)) {
            Toast.makeText(Create_task.this, "Field is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(details)) {
            Toast.makeText(Create_task.this, "Field is empty", Toast.LENGTH_SHORT).show();
            return;
        } else {

            //key = roll;

            Task_Model task_model = new Task_Model(key, subject, date, time, title, details);
            databaseReference.child(key).child(title).setValue(task_model);
            Toast.makeText(Create_task.this, "Successfull", Toast.LENGTH_SHORT).show();


        }
        taskSave.setEnabled(false);


    }
}



