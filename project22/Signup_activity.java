package com.example.project22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;


public class Signup_activity extends AppCompatActivity {

    TextInputLayout tf_signup_name_layout,tf_signup_email_layout ,tf_signup_password_layout,tf_signup_confirmpassword_layout,
            tf_signup_department_layout ,tf_signup_semester_layout,tf_signup_section_layout, tf_signup_id_layout ;
    TextInputEditText tf_signup_name,tf_signup_email ,tf_signup_password,tf_signup_confirmpassword,tf_signup_department,tf_signup_semester,tf_signup_section, tf_signup_id ;
    Button btn_signup_next;
    Exception exception= new Exception();
    FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    public static double selected_semester;
    boolean right_section=false;

    boolean right =false;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        tf_signup_name_layout = (TextInputLayout) findViewById(R.id.tf_signup_name_layout);
        tf_signup_email_layout = (TextInputLayout) findViewById(R.id.tf_signup_email_layout);
        tf_signup_password_layout = (TextInputLayout) findViewById(R.id.tf_signup_password_layout);
        tf_signup_confirmpassword_layout = (TextInputLayout) findViewById(R.id.tf_signup_confirmpassword_layout);
        tf_signup_id_layout = (TextInputLayout) findViewById(R.id.tf_signup_id_layout);
        tf_signup_department_layout = (TextInputLayout) findViewById(R.id.tf_signup_department_layout);
        tf_signup_semester_layout = (TextInputLayout) findViewById(R.id.tf_signup_semester_layout);
        tf_signup_section_layout = (TextInputLayout) findViewById(R.id.tf_signup_section_layout);


        tf_signup_name = (TextInputEditText) findViewById(R.id.tf_signup_name);
        tf_signup_email = (TextInputEditText) findViewById(R.id.tf_signup_email);
        tf_signup_password = (TextInputEditText) findViewById(R.id.tf_signup_password);
        tf_signup_confirmpassword = (TextInputEditText) findViewById(R.id.tf_signup_confirmpassword);

        tf_signup_department = (TextInputEditText) findViewById(R.id.tf_signup_department);
        tf_signup_semester = (TextInputEditText) findViewById(R.id.tf_signup_semester);
        tf_signup_section = (TextInputEditText) findViewById(R.id.tf_signup_section);
        tf_signup_id = (TextInputEditText) findViewById(R.id.tf_signup_id);

        btn_signup_next = (Button) findViewById(R.id.btn_signup_next);
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        btn_signup_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

    }




    public void createUser()
    {

        //user_info use= new user_info(tf_signup_name.getText().toString(),tf_signup_email.getText().toString(),
        //      tf_signup_semester.getText().toString(),tf_signup_section.getText().toString(),tf_signup_department.getText().toString());
        boolean empty= exception.SignUpException(tf_signup_name.getText().toString() , tf_signup_email.getText().toString(),tf_signup_password.getText().toString(),tf_signup_confirmpassword.getText().toString(),tf_signup_semester.getText().toString(),tf_signup_section.getText().toString(),tf_signup_department.getText().toString(),tf_signup_id.getText().toString());

        if(empty==true)
        {


            Toast.makeText(Signup_activity.this,"ALL FIELDS MUST BE FIELD",Toast.LENGTH_SHORT).show();
        }

        else if(empty==false) {
            int invalid = exception.signup_SpecharacterException(tf_signup_name.getText().toString(), tf_signup_email.getText().toString(), tf_signup_password.getText().toString(), tf_signup_confirmpassword.getText().toString());

            if (invalid == 1) {
                tf_signup_name_layout.setError("Only A-Z,a-z,space can be used ");
                tf_signup_email_layout.setError("Only A-Z,a-z,space,@ can be used");
            }

            if (invalid == 2) {
                tf_signup_name_layout.setError("Only A-Z,a-z,space can be used");
                tf_signup_email_layout.setError(null);

            }
            if (invalid == 3) {
                tf_signup_email_layout.setError("Only A-Z,a-z,space,@ can be used");
                tf_signup_name_layout.setError(null);

            }

            if (invalid == 4) {
                tf_signup_name_layout.setError(null);
                tf_signup_email_layout.setError(null);


                if (tf_signup_password.getText().toString().equals(tf_signup_confirmpassword.getText().toString())) {

                    tf_signup_confirmpassword_layout.setError(null);


                    if ((tf_signup_semester.getText().toString().equals("1.1") || tf_signup_semester.getText().toString().equals("1.2") || tf_signup_semester.getText().toString().equals("2.1") || tf_signup_semester.getText().toString().equals("2.2") ||
                            tf_signup_semester.getText().toString().equals("3.2") || tf_signup_semester.getText().toString().equals("4.1") ||
                            tf_signup_semester.getText().toString().equals("3.1") || tf_signup_semester.getText().toString().equals("4.2")) && (tf_signup_section.getText().toString().equals("A") || tf_signup_section.getText().toString().equals("B") || tf_signup_section.getText().toString().equals("C"))) {


                        tf_signup_semester_layout.setError(null);
                        tf_signup_section_layout.setError(null);

                        mAuth.createUserWithEmailAndPassword(tf_signup_email.getText().toString()
                                , tf_signup_password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull @NotNull Task<AuthResult> task) {

                                if (task.isSuccessful()) {

                                    if (user.isEmailVerified()) {
                                        Toast.makeText(Signup_activity.this, "Already Registered", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent();
                                        startActivity(new Intent(Signup_activity.this, MainActivity.class));
                                    } else {
                                        System.out.println("Sign up done");
                                        user.sendEmailVerification();
                                        Toast.makeText(Signup_activity.this, "CHECK EMAIL TO VARIFY", Toast.LENGTH_SHORT).show();


                                        String getName = tf_signup_name.getText().toString();
                                        String getEmail = tf_signup_email.getText().toString();
                                        String getSemester = tf_signup_semester.getText().toString();
                                        String getSection = tf_signup_section.getText().toString();
                                        String getDepertment = tf_signup_department.getText().toString();
                                        String getId = tf_signup_id.getText().toString();

                                        HashMap<String, Object> hashMap = new HashMap<>();
                                        hashMap.put("Name", getName);
                                        hashMap.put("Email", getEmail);
                                        hashMap.put("Semester", getSemester);
                                        hashMap.put("Section", getSection);
                                        hashMap.put("Depertment", getDepertment);
                                        hashMap.put("ID", getId);
                                        myRef.child("Users").child(getId).setValue(hashMap);

                                        Intent intent=new Intent(Signup_activity.this,Login.class);
                                        startActivity(intent);


                                    }
                                } else
                                    Toast.makeText(Signup_activity.this, "not connected signup", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else if (!(tf_signup_semester.getText().toString().equals("1.1") || tf_signup_semester.getText().toString().equals("1.2") || tf_signup_semester.getText().toString().equals("2.1") || tf_signup_semester.getText().toString().equals("2.2") ||
                            tf_signup_semester.getText().toString().equals("3.1") || tf_signup_semester.getText().toString().equals("4.2")) && !(tf_signup_section.getText().toString().equals("A") || tf_signup_section.getText().toString().equals("B") || tf_signup_section.getText().toString().equals("C"))) {
                        tf_signup_semester_layout.setError("Invalid semester");
                        tf_signup_section_layout.setError("Invalid section");
                    } else if ((tf_signup_semester.getText().toString().equals("1.1") || tf_signup_semester.getText().toString().equals("1.2") || tf_signup_semester.getText().toString().equals("2.1") || tf_signup_semester.getText().toString().equals("2.2") ||
                            tf_signup_semester.getText().toString().equals("3.1") || tf_signup_semester.getText().toString().equals("4.2")) && !(tf_signup_section.getText().toString().equals("A") || tf_signup_section.getText().toString().equals("B") || tf_signup_section.getText().toString().equals("C"))) {
                        tf_signup_semester_layout.setError(null);
                        tf_signup_section_layout.setError("Invalid section");
                    } else if (!(tf_signup_semester.getText().toString().equals("1.1") || tf_signup_semester.getText().toString().equals("1.2") || tf_signup_semester.getText().toString().equals("2.1") || tf_signup_semester.getText().toString().equals("2.2") ||
                            tf_signup_semester.getText().toString().equals("3.1") || tf_signup_semester.getText().toString().equals("4.2")) && (tf_signup_section.getText().toString().equals("A") || tf_signup_section.getText().toString().equals("B") || tf_signup_section.getText().toString().equals("C"))) {
                        tf_signup_semester_layout.setError("Invalid semester");
                        tf_signup_section_layout.setError(null);
                    }


                } else {
                    tf_signup_confirmpassword_layout.setError("Have not matched with Password");
                }


            }



        }


    }



}



