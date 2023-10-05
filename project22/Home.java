package com.example.project22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerl;
    private Toolbar tool;
    private NavigationView nav;
    private TextView headerName,headerEmail,hName,hTask;
    private Button htaskClick;
    //private static final String File_name="myfile";
    private CalendarView calendarView;


    String nav_name,Login_name1,Login_name2,Login_email1,nav_email,roll;
    String home_name,home_task_number;
    String[] array = new String[20];
    String[] array_count = new String[20];

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference().child("Users");

    DatabaseReference databasereference2=firebaseDatabase.getReference("Task");
    //String Login_email2=Login.getActivityInstance()[1];

    String preference_email,preference_count;
    int count=0;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tool=findViewById(R.id.toolbar);
        drawerl=findViewById(R.id.drawerId);
        nav=findViewById(R.id.navigation_View);
        headerName=  (TextView)nav.getHeaderView(0).findViewById(R.id.header_name);
        headerEmail=  (TextView)nav.getHeaderView(0).findViewById(R.id.header_email);
        hName=findViewById(R.id.home_name);
        hTask=findViewById(R.id.task_remaining);
        htaskClick=findViewById(R.id.task_click);
        calendarView=findViewById(R.id.home_calendar);




        SharedPreferences sharedPreferences=getSharedPreferences("user_email", Context.MODE_PRIVATE);
        if(sharedPreferences.contains("user_email_key"))
        {
            preference_email=sharedPreferences.getString("user_email_key","Data not found");
        }


        //passing interface of firebasecallback interface
        readData(new FirebaseCallback() {
            @Override
            public void OnCallback1(String x,String y) {

                headerEmail.setText(x);
                headerName.setText(y);
                hName.setText(y);
            }

            public void OnCallback2(String roll_check) {

                databasereference2.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        if(snapshot.exists()) {
                            for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                                if(dataSnapshot.getKey().equals(roll_check)) {
                                    count = (int) dataSnapshot.getChildrenCount();
                                    Log.d("Count", String.valueOf(count));
                                    Log.e("In child count" + dataSnapshot.getKey(), dataSnapshot.getChildrenCount() + "");
                                    Log.d(roll_check, String.valueOf(count));

                                    if(count!=0)
                                    {
                                        hTask.setText("You have "+count+" task remaining.");
                                    }
                                    else{
                                        hTask.setText("No task remaining.");
                                    }

                                }

                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });



            }
        });



        setSupportActionBar(tool);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(
                this,
                drawerl,
                tool,
                R.string.open,
                R.string.close

        );

        drawerl.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(this);

        htaskClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,Show_task.class);
                startActivity(intent);

            }
        });



    }


    private void readData(FirebaseCallback firebaseCallback)
    {
        Query check = databaseReference.orderByChild("Email").equalTo(preference_email);
        check.addValueEventListener(new ValueEventListener() {
            int i=0;
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    array[i] = String.valueOf(dataSnapshot.getValue());
                    Log.d("myNavigation-name,email", String.valueOf(array[i]));
                    i++;
                    nav_email=dataSnapshot.child("Email").getValue(String.class);
                    nav_name=dataSnapshot.child("Name").getValue(String.class);
                    roll=dataSnapshot.child("ID").getValue(String.class);
                }

                firebaseCallback.OnCallback1(nav_email,nav_name);
                firebaseCallback.OnCallback2(roll);


            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }

    private interface FirebaseCallback
    {
        void OnCallback1(String x,String y);
        void OnCallback2(String z);

    }

    @Override
    public void onBackPressed() {

        if(drawerl.isDrawerOpen(GravityCompat.START))
        {
            drawerl.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {


        Intent intent;

        switch (item.getItemId())
        {

            case R.id.Menu2:


                intent=new Intent(this,User_profile.class);
                startActivity(intent);
                break;
            case R.id.Menu3:


                //Toast.makeText(this, "Teacher's Information", Toast.LENGTH_SHORT).show();
                intent=new Intent(this,teachers_activity.class);
                startActivity(intent);
                break;

            case R.id.Menu4:

                intent=new Intent(this,Routine_semester.class);
                startActivity(intent);
                break;

            case R.id.Menu5:

                //Toast.makeText(this, "Tasks", Toast.LENGTH_SHORT).show();
                intent=new Intent(this,Show_task.class);
                startActivity(intent);
                break;
            case R.id.Menu6:

                intent=new Intent(Home.this,Result_semselect.class);
                startActivity(intent);
                break;

            case R.id.Menu7:

                SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor= preferences.edit();
                editor.putString("remember","false");
                editor.apply();

                Toast.makeText(this, "signout", Toast.LENGTH_SHORT).show();
                intent=new Intent(Home.this,Login.class);
                startActivity(intent);
                finish();
                break;

            case R.id.Menu8:

                intent=new Intent(Home.this, Cg_Show_result.class);
                startActivity(intent);
                break;

            default:
                break;
        }

        drawerl.closeDrawer(GravityCompat.START);
        return true;
    }
}