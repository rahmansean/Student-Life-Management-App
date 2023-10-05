package com.example.project22;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.opengl.Visibility;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.jetbrains.annotations.NotNull;

public class Result_semselect extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    static public String sem;
    RadioGroup radioGroup;
    RadioButton radio_button_1, radio_button_2, radio_button_3, radio_button_4, radio_button_5, radio_button_6, radio_button_7, radio_button_8;
    Button btn_next;

    Intent intent;
    String[] array = new String[20];
    String email,semester;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef,dataref ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cg);

        dataref = database.getReference().child("Users");
        email = Login.getActivityInstance()[1];
        searchdata();


        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radio_button_1 = (RadioButton) findViewById(R.id.radio_button_1);
        radio_button_2 = (RadioButton) findViewById(R.id.radio_button_2);
        radio_button_3 = (RadioButton) findViewById(R.id.radio_button_3);
        radio_button_4 = (RadioButton) findViewById(R.id.radio_button_4);
        radio_button_5 = (RadioButton) findViewById(R.id.radio_button_5);
        radio_button_6 = (RadioButton) findViewById(R.id.radio_button_6);
        radio_button_7 = (RadioButton) findViewById(R.id.radio_button_7);
        radio_button_8 = (RadioButton) findViewById(R.id.radio_button_8);

        //Log.d("muTAG", Login.getActivityInstance()[5]);

        //test();

        radioGroup.setOnCheckedChangeListener(this);


    }
    private void searchdata() {


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
                //section=array[4];
                semester=array[5];
                //Log.d( "onCreate:",section);
                Log.d( "semester",semester);
                show(semester);


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

    private void show(String semester) {
        if (semester.equals("1.1")) {
            radio_button_1.setVisibility(View.VISIBLE);
            radio_button_2.setVisibility(View.INVISIBLE);
            radio_button_3.setVisibility(View.INVISIBLE);
            radio_button_4.setVisibility(View.INVISIBLE);
            radio_button_5.setVisibility(View.INVISIBLE);
            radio_button_6.setVisibility(View.INVISIBLE);
            radio_button_7.setVisibility(View.INVISIBLE);
            radio_button_8.setVisibility(View.INVISIBLE);
        } else if (semester.equals("1.2")) {

            radio_button_1.setVisibility(View.VISIBLE);
            radio_button_2.setVisibility(View.VISIBLE);
            radio_button_3.setVisibility(View.INVISIBLE);
            radio_button_4.setVisibility(View.INVISIBLE);
            radio_button_5.setVisibility(View.INVISIBLE);
            radio_button_6.setVisibility(View.INVISIBLE);
            radio_button_7.setVisibility(View.INVISIBLE);
            radio_button_8.setVisibility(View.INVISIBLE);
        } else if (semester.equals("2.1")) {

            radio_button_1.setVisibility(View.VISIBLE);
            radio_button_2.setVisibility(View.VISIBLE);
            radio_button_3.setVisibility(View.VISIBLE);
            radio_button_4.setVisibility(View.INVISIBLE);
            radio_button_5.setVisibility(View.INVISIBLE);
            radio_button_6.setVisibility(View.INVISIBLE);
            radio_button_7.setVisibility(View.INVISIBLE);
            radio_button_8.setVisibility(View.INVISIBLE);
        } else if (semester.equals("2.2")) {

            radio_button_1.setVisibility(View.VISIBLE);
            radio_button_2.setVisibility(View.VISIBLE);
            radio_button_3.setVisibility(View.VISIBLE);
            radio_button_4.setVisibility(View.VISIBLE);
            radio_button_5.setVisibility(View.INVISIBLE);
            radio_button_6.setVisibility(View.INVISIBLE);
            radio_button_7.setVisibility(View.INVISIBLE);
            radio_button_8.setVisibility(View.INVISIBLE);
        } else if (semester.equals("3.1")) {

            radio_button_1.setVisibility(View.VISIBLE);
            radio_button_2.setVisibility(View.VISIBLE);
            radio_button_3.setVisibility(View.VISIBLE);
            radio_button_4.setVisibility(View.VISIBLE);
            radio_button_5.setVisibility(View.VISIBLE);
            radio_button_6.setVisibility(View.INVISIBLE);
            radio_button_7.setVisibility(View.INVISIBLE);
            radio_button_8.setVisibility(View.INVISIBLE);
        } else if (semester.equals("3.2")) {

            radio_button_1.setVisibility(View.VISIBLE);
            radio_button_2.setVisibility(View.VISIBLE);
            radio_button_3.setVisibility(View.VISIBLE);
            radio_button_4.setVisibility(View.VISIBLE);
            radio_button_5.setVisibility(View.VISIBLE);
            radio_button_6.setVisibility(View.VISIBLE);
            radio_button_7.setVisibility(View.INVISIBLE);
            radio_button_8.setVisibility(View.INVISIBLE);
        } else if (semester.equals("4.1")) {

            radio_button_1.setVisibility(View.VISIBLE);
            radio_button_2.setVisibility(View.VISIBLE);
            radio_button_3.setVisibility(View.VISIBLE);
            radio_button_4.setVisibility(View.VISIBLE);
            radio_button_5.setVisibility(View.VISIBLE);
            radio_button_6.setVisibility(View.VISIBLE);
            radio_button_7.setVisibility(View.VISIBLE);
            radio_button_8.setVisibility(View.INVISIBLE);
        } else if (semester.equals("4.2")) {

            radio_button_1.setVisibility(View.VISIBLE);
            radio_button_2.setVisibility(View.VISIBLE);
            radio_button_3.setVisibility(View.VISIBLE);
            radio_button_4.setVisibility(View.VISIBLE);
            radio_button_5.setVisibility(View.VISIBLE);
            radio_button_6.setVisibility(View.VISIBLE);
            radio_button_7.setVisibility(View.VISIBLE);
            radio_button_8.setVisibility(View.VISIBLE);
        }


    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {

        switch (checkedId)
        {
            case R.id.radio_button_1:
            {
                sem = "1_1";
                intent = new Intent(Result_semselect.this, cg.class);
                startActivity(intent);
                break;

            }
            case R.id.radio_button_2:
            {
                sem = "1_2";
                intent = new Intent(Result_semselect.this, cg.class);
                startActivity(intent);
                break;
            }

            case R.id.radio_button_3:
            {

                sem = "2_1";
                intent = new Intent(Result_semselect.this, cg.class);
                startActivity(intent);
                break;
            }

            case R.id.radio_button_4:
            {
                sem = "2_2";
                intent = new Intent(Result_semselect.this, cg.class);
                startActivity(intent);
                break;
            }

            case R.id.radio_button_5:
            {
                sem = "3_1";
                intent = new Intent(Result_semselect.this, cg.class);
                startActivity(intent);
                break;
            }

            case R.id.radio_button_6:
            {
                sem = "3_2";
                intent = new Intent(Result_semselect.this, cg.class);
                startActivity(intent);
                break;
            }


            case R.id.radio_button_7:
            {
                sem = "4_1";
                intent = new Intent(Result_semselect.this, cg.class);
                startActivity(intent);
                break;
            }
            case R.id.radio_button_8:
            {
                sem = "4_2";
                intent = new Intent(Result_semselect.this, cg.class);
                startActivity(intent);
                break;
            }


        }
    }
}
