package com.example.project22;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.jetbrains.annotations.NotNull;

public class Login extends AppCompatActivity {


    CheckBox remember;
    static String[] INSTANCE;
    static String semester;
    static String email;


    FirebaseAuth mAuth;
    TextInputLayout tf_login_email_layout,tf_login_password_layout;
    TextInputEditText tf_login_email;
    TextInputEditText tf_login_password;
    Button btn_signin,btn_login_createaccount,btn_login_forgotpassword;
    FirebaseUser user;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("Users");

    String name,section,depertment;
    static String[] arr = new String[60];




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth= FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        remember=(CheckBox)findViewById(R.id.remember);
        tf_login_email_layout = (TextInputLayout) findViewById(R.id.tf_login_email_layout);
        tf_login_password_layout = (TextInputLayout) findViewById(R.id.tf_login_password_layout);
        tf_login_email = (TextInputEditText) findViewById(R.id.tf_login_email);
        tf_login_password = (TextInputEditText) findViewById(R.id.tf_login_password);
        btn_signin = (Button) findViewById(R.id.btn_signin);
        btn_login_createaccount = (Button) findViewById(R.id.btn_login_createaccount);
        btn_login_forgotpassword=(Button)findViewById((R.id.btn_login_forgotpassword)) ;
        SharedPreferences preferences= getSharedPreferences("checkbox",MODE_PRIVATE);

        String check= preferences.getString("remember",""); //sean changed

        String username= preferences.getString("username","");
        String password = preferences.getString("password","");
        //String sem = preferences.getString("semester","");
        //String id=preferences.getString("id","");


        if(check.equals("true"))
        {

            Log.d("username", username);
            arr[1]=username;

            INSTANCE= arr;
            Intent intent = new Intent(Login.this, Home.class);
            startActivity(intent);

        }
        else if(check.equals("false"))
        {
            INSTANCE= arr;
            Toast.makeText(Login.this, "please sign in", Toast.LENGTH_SHORT).show();
        }


        btn_signin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                loginuser(tf_login_email.getText().toString() ,tf_login_password.getText().toString());
                INSTANCE= arr;

                //Sean added 1 octo for navigation name,email
                SharedPreferences sharedPreferences=getSharedPreferences("user_email", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("user_email_key",tf_login_email.getText().toString());
                editor.commit();



                //User_profile user_profile= new User_profile(tf_login_email.getText().toString());
                //12:30 22 sept added
                //Intent intent = new Intent(Login.this, Home.class);
                //startActivity(intent);


            }


        });


        btn_login_createaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                startActivity(new Intent(Login.this, Signup_activity.class));

            }
        });

        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(buttonView.isChecked())
                {
                    SharedPreferences preferences =getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor= preferences.edit();
                    editor.putString("remember","true");
                    editor.putString("username",tf_login_email.getText().toString());
                    editor.putString("password",tf_login_password.getText().toString());
                    editor.apply();

                }
                else if(!buttonView.isChecked())
                {
                    SharedPreferences preferences =getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor= preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();
                }
            }
        });

        btn_login_forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Login.this, forgot_password.class);
                startActivity(intent);

            }
        });

    }


    //12:30 22 sept added //commented out

    public void loginuser(String username,String passsword)
    {


        email=tf_login_email.getText().toString();


        if (tf_login_email.getText().length()==0 && tf_login_password.getText().length()==0) {

            tf_login_email_layout.setError("Field can not be left blank ");
            tf_login_password_layout.setError("Field can not be left blank ");

        } else if (tf_login_email.getText().length()==0) {
            tf_login_email_layout.setError("Field can not be left blank ");
            tf_login_password_layout.setError(null);


        } else if (tf_login_password.getText().length()==0) {
            tf_login_password_layout.setError("Field can not be left blank ");
            tf_login_email_layout.setError(null);
        }



        else
        {
            tf_login_password_layout.setError(null);
            tf_login_email_layout.setError(null);



            //Sean Code
            mAuth.signInWithEmailAndPassword(tf_login_email.getText().toString(),tf_login_password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {


                    if(task.isSuccessful()) {

                        if(user.isEmailVerified())
                        {
                            //Toast.makeText(Login.this, "sign in", Toast.LENGTH_SHORT).show();


                            searchdata();


                            Intent intent = new Intent(Login.this, Home.class);

                            //Sean added 1 ocotber
                            //intent.putExtra("email",tf_login_email.getText().toString());
                            //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                            startActivity(intent);


                        }
                    }
                    else
                        Toast.makeText(Login.this,"Invalid Email or Password",Toast.LENGTH_SHORT).show();
                }

            });

        }
    }

    private void searchdata() {


        Query check= myRef .orderByChild("Email").equalTo(tf_login_email.getText().toString());

        check.addChildEventListener(new ChildEventListener() {
            int i =0;
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {

                    arr[i]= String.valueOf(dataSnapshot.getValue());
                    Log.d("myTag", String.valueOf(arr[i]));
                    i++;

                }


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





    public static String[] getActivityInstance()
    {
        return INSTANCE;
    }

    public static String getSemester()
    {
        return semester;
    }


    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Application?");
        alertDialogBuilder.setMessage("Click yes to exit!")
                .setCancelable(false).setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        moveTaskToBack(true);

                        SharedPreferences preferences =getSharedPreferences("checkbox",MODE_PRIVATE);
                        SharedPreferences.Editor editor= preferences.edit();
                        editor.putString("remember","false");
                        editor.apply();
                        //finish();
                        //android.os.Process.killProcess(android.os.Process.myPid());
                        //System.exit(0);



                    }

                })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }






}





