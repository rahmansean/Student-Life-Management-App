package com.example.project22;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.jetbrains.annotations.NotNull;

public class User_profile extends AppCompatActivity {

    private Button buttonEdit, buttonSave, buttonUpload;
    private EditText editName, editId, editDept, editEmail, editSemester, editSection;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference().child("Users");

    DatabaseReference myRef2,myRef3;


    String[] array = new String[10];
    static String[] arraydata = new String[10];
    Login login = new Login();
    String email, section, name;

    String semester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Signup_activity signup_activity = new Signup_activity();
        buttonEdit = findViewById(R.id.Profile_EditBtn);
        buttonSave = findViewById(R.id.Profile_SaveBtn);

        //buttonUpload=findViewById(R.id.Profile_imgBtn);
        email=Login.getActivityInstance()[1];
        Log.d("AG", email);


        editName = findViewById(R.id.Profile_Name);
        editId = findViewById(R.id.Profile_Id);
        editDept = findViewById(R.id.Profile_Dept);
        editEmail = findViewById(R.id.Profile_Email);
        editSemester = findViewById(R.id.Profile_Sem);
        editSection = findViewById(R.id.Profile_Section);




        Query check= myRef .orderByChild("Email").equalTo(email);
        check.addChildEventListener(new ChildEventListener(){
            int i =0;
            @Override
            public void onChildAdded(@NonNull @NotNull DataSnapshot snapshot, @Nullable @org.jetbrains.annotations.Nullable String previousChildName) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    array[i] = String.valueOf(dataSnapshot.getValue());
                    Log.d("myyyyyTag", String.valueOf(array[i]));
                    i++;

                }
                // Log.d("array", array[3]);
                senddata(array);
                //arraydata=array;
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




        editName.setEnabled(false);
        editId.setEnabled(false);
        editDept.setEnabled(false);
        editEmail.setEnabled(false);
        editSemester.setEnabled(false);
        editSection.setEnabled(false);


        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editSemester.setEnabled(true);
                editSection.setEnabled(true);

            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSemesterChanged();
            }


        });







    }

    private void senddata(String[] arr) {


        editName.setText(arr[3]);
        editId.setText(arr[2]);
        editDept.setText(arr[0]);
        editEmail.setText(arr[1]);
        editSemester.setText(arr[5]);
        editSection.setText(arr[4]);
        semester =arr[5];
        section = arr[4];

        myRef2 = database.getReference().child("Users").child(arr[2]).child("Semester");
        myRef3 = database.getReference().child("Users").child(arr[2]).child("Section");

    }


    private void isSemesterChanged() {
        if (editSemester.getText().toString().isEmpty() || editSection.getText().toString().isEmpty()) {
            Toast.makeText(User_profile.this, "Fields can not be left blank", Toast.LENGTH_SHORT).show();

        }
        else if ((editSemester.getText().toString().equals("1.1") || editSemester.getText().toString().equals("1.2") || editSemester.getText().toString().equals("2.1") || editSemester.getText().toString().equals("2.2") ||
                editSemester.getText().toString().equals("3.2")||  editSemester.getText().toString().equals("4.1")||
        editSemester.getText().toString().equals("3.1")  || editSemester.getText().toString().equals("4.2")) && (editSection.getText().toString().equals("A") || editSection.getText().toString().equals("B") || editSection.getText().toString().equals("C"))) {

            editSemester.setEnabled(false);
            editSection.setEnabled(false);

            //myRef.child(Login.getActivityInstance()[2]).child("Semester").setValue(editSemester.getEditableText().toString());
            //myRef.child(Login.getActivityInstance()[2]).child("Section").setValue(editSection.getEditableText().toString());

            myRef2.setValue(editSemester.getEditableText().toString());
            myRef3.setValue(editSection.getEditableText().toString());

            //Login.getActivityInstance()[5] = editSemester.getEditableText().toString();
            //Login.getActivityInstance()[4] = editSection.getEditableText().toString();

            editSemester.setText(editSemester.getEditableText().toString());
            editSection.setText(editSection.getEditableText().toString());
            //arraydata[5]=editSemester.getEditableText().toString();
            //arraydata[4]= editSection.getEditableText().toString();

            Toast.makeText(User_profile.this, "Data has been updated", Toast.LENGTH_SHORT).show();

        }

        else {
            Toast.makeText(User_profile.this, "Invalid Semester or Section", Toast.LENGTH_SHORT).show();
        }

    }
}
