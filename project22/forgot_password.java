package com.example.project22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class forgot_password extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser user;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("Users");
    String email,password;
    int i=0;
    TextInputLayout tf_email_layout;
    TextInputEditText tf_email;
    Button btn_forgot_confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        tf_email_layout=(TextInputLayout) findViewById(R.id.email_layout);
        tf_email=(TextInputEditText) findViewById(R.id.email);
        btn_forgot_confirm=(Button)findViewById(R.id.btn_forget_confirm);


        mAuth= FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();


        btn_forgot_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tf_email.getText().length()!=0) {
                    tf_email_layout.setError(null);

                    Query check = myRef.orderByChild("Email").equalTo(tf_email.getText().toString());


                    check.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                mAuth.sendPasswordResetEmail(tf_email.getText().toString());
                                Toast.makeText(forgot_password.this, "Check your email to change password", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent();
                                startActivity(new Intent(forgot_password.this, Login.class));
                            } else
                                Toast.makeText(forgot_password.this, "not found", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });
                }
                else
                    tf_email_layout.setError("Can not be left blank");
            }

        });




    }



}


