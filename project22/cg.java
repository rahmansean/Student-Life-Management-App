package com.example.project22;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class cg extends AppCompatActivity {
    String semester_db =Result_semselect.sem;
    RecyclerView recyclerView;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference myRef = database.getReference("CSE");

    Intent intent;
    ArrayList<data> list;
    data[] arr = new data[50];
    Myadapter m ;

    TextInputLayout tf_cg_show_layout,tf_grade_show_layout;

    TextInputEditText tf_cg_show,tf_grade_show;
    String email_cg=Login.getActivityInstance()[1];

    String[] array = new String[50];
    double[] gpa = new double[50];
    String roll;





    //String roll = Login.getActivityInstance()[2];

    String result,id;

    DatabaseReference myRefResult1 = database.getInstance().getReference().child("Result");
    DatabaseReference myRefResult2;
    DatabaseReference myRefUser= database.getInstance().getReference().child("Users");






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_semselect);

        Button btn_cg_calculate;
        btn_cg_calculate = (Button)findViewById(R.id.btn_cg_calculate);
        recyclerView =findViewById(R.id.recyclerview);
        tf_cg_show_layout= (TextInputLayout)findViewById(R.id.tf_cg_show_layout);
        tf_grade_show_layout=(TextInputLayout)findViewById(R.id.tf_grade_show_layout);
        tf_cg_show=( TextInputEditText)findViewById(R.id.tf_cg_show);
        tf_grade_show=(TextInputEditText)findViewById(R.id.tf_grade_show);


        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();

        m = new Myadapter(this,list);
        recyclerView.setAdapter(m);


        myRef.child(semester_db).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()) {


                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        data d = dataSnapshot.getValue(data.class);
                        list.add(d);

                        arr = list.toArray(arr);


                    }

                    m.notifyDataSetChanged();
                }
                else  Toast.makeText(cg.this, "No Valid Data Exists", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        //spinner.setVisibility(View.INVISIBLE);




        btn_cg_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String result;
                double sum1=0;
                double sum2=0;
                for(int i = 0;i < m.inputed_credit.length; i++)
                {
                    for(int j=0; j<m.inputed_credit.length; j++)
                    {
                        if (m.inputed_grade[i] >= 80)
                        {
                            gpa[i] = 4.00;
                            break;
                        }

                        else if (m.inputed_grade[i] >= 75 &&m.inputed_grade[i] < 80)
                        {
                            gpa[i] =3.75;
                            break;
                        }

                        else if (m.inputed_grade[i] >= 70  &&m.inputed_grade[i] < 75)
                        {
                            gpa[i] =3.50;
                            break;
                        }
                        else if (m.inputed_grade[i] >= 65 &&m.inputed_grade[i] < 70)
                        {
                            gpa[i] =3.25;
                            break;
                        }
                        else if (m.inputed_grade[i] >= 60 && m.inputed_grade[i] < 65)
                        {
                            gpa[i] =3.00;
                            break;
                        }
                        else if (m.inputed_grade[i] >= 55 && m.inputed_grade[i] < 60)
                        {
                            gpa[i] =2.75;
                            break;
                        }
                        else if (m.inputed_grade[i] >= 50 &&m.inputed_grade[i] < 55)
                        {
                            gpa[i] =2.50;
                            break;
                        }
                        else if (m.inputed_grade[i] >= 45 && m.inputed_grade[i] < 50)
                        {
                            gpa[i] =2.25;
                            break;
                        }
                        else if (m.inputed_grade[i] >= 40 && m.inputed_grade[i]< 45)
                        {
                            gpa[i] =2.00;
                            break;
                        }

                        else if (m.inputed_grade[i] < 40)
                        {
                            gpa[i] = 0.0;
                            break;
                        }
                    }
                }

                for (int i = 0; i < m.inputed_credit.length; i++)
                {

                    sum1 = ( m.inputed_credit[i]* gpa[i] ) + sum1;

                }

                for (int i = 0; i < m.inputed_credit.length; i++)
                {
                    sum2 =  m.inputed_credit[i] + sum2;
                }
                /*
                for(int i=0;i<m.inputed_credit.length;i++) {

                    //INPUT AR CREDIT.CREDIT FROM DATA BASE. INPUT GRADE FROM USER
                    sum1 = (float) ((m.inputed_credit[i]*m.inputed_grade[i])+sum1);

                }

                for(int i=0;i<m.inputed_credit.length;i++)
                {
                    sum2= (float) ((m.inputed_credit[i])+sum2);
                }
*/
                //FINAL RESULT.
                result = String.format("%.2f",sum1/sum2);
                Log.d("myTag Final result", String.valueOf(result));

                //store();  //Sean code
                searchdata();

                //String result1 = String.valueOf(result);


                Log.d("myTag Final result", String.valueOf(result));

                tf_cg_show.setText(result.toString());



                if(Float.parseFloat(result)>= 4.00)
                {
                    tf_grade_show.setText(null);
                    tf_grade_show.setText("A+");
                }

                else if(Float.parseFloat(result) > 3.50 && Float.parseFloat(result) <=3.75 )
                {
                    tf_grade_show.setText(null);
                    tf_grade_show.setText("A");

                }


                else if(Float.parseFloat(result) > 3.25 && Float.parseFloat(result) <=3.50 )
                {

                    tf_grade_show.setText(null);
                    tf_grade_show.setText("A-");
                }



                else if(Float.parseFloat(result) > 3.00 && Float.parseFloat(result)<=3.25 )
                {

                    tf_grade_show.setText(null);
                    tf_grade_show.setText("B+");
                }


                else if(Float.parseFloat(result)> 2.75 && Float.parseFloat(result) <=3.00 )
                {

                    tf_grade_show.setText(null);
                    tf_grade_show.setText("B");
                }


                else if(Float.parseFloat(result) > 2.50 && Float.parseFloat(result) <=2.75 )
                {

                    tf_grade_show.setText(null);
                    tf_grade_show.setText("B-");
                }


                else if(Float.parseFloat(result) > 2.25 &&Float.parseFloat(result)<=2.50 )
                {

                    tf_grade_show.setText(null);
                    tf_grade_show.setText("C+");
                }

                else if(Float.parseFloat(result) > 2.00 && Float.parseFloat(result) <=2.25 )
                {

                    tf_grade_show.setText(null);
                    tf_grade_show.setText("C");
                }


                else if(Float.parseFloat(result) > 3.50 && Float.parseFloat(result) <=3.75 )
                {

                    tf_grade_show.setText(null);
                    tf_grade_show.setText("D");
                }


                else if(Float.parseFloat(result) <= 0.00  )
                {

                    tf_grade_show.setText(null);
                    tf_grade_show.setText("F");

                }

            }
        });

    }

    @Override
    public void onBackPressed() {

        intent=new Intent(this,Home.class);
        startActivity(intent);

    }

    private void searchdata(){

        Query check = myRefUser.orderByChild("Email").equalTo(email_cg);

        check.addChildEventListener(new ChildEventListener() {
            int i = 0;

            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    array[i] = String.valueOf(dataSnapshot.getValue());
                    Log.d("Cg_create_result", String.valueOf(array[i]));
                    i++;


                }
                roll=array[2];
                Log.d( "Create_cg_roll:",roll);
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
        id=roll_here;
        myRefResult2=myRefResult1.child(id).child(semester_db);

        CgShow_model cgShow_model= new CgShow_model(id,result,semester_db);
        myRefResult2.setValue(cgShow_model);
        Toast.makeText(cg.this, "Successfull", Toast.LENGTH_SHORT).show();
    }




}