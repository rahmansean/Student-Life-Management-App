package com.example.project22;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class teachers_adapter extends RecyclerView.Adapter<teachers_adapter.MyviewHolder>{

    Context context;
    ArrayList<teacher_recyclerview_modelclass> list;

    public teachers_adapter(Context context, ArrayList<teacher_recyclerview_modelclass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public teachers_adapter.MyviewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_of_teacher_recyclerview,parent,false);

        teachers_adapter.MyviewHolder holder =new teachers_adapter.MyviewHolder(view);
        return holder;





    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull teachers_adapter.MyviewHolder holder, int position) {
        teacher_recyclerview_modelclass t = list.get(position);
        holder.tv_name.setText(t.getTname()+" ");
        holder.tv_phone.setText( t.getTphone()+" ");
        holder.tv_email.setText(t.getTemail()+" ");





        holder.tv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Intent.ACTION_DIAL);



                intent.setData(Uri.parse("tel:"+list.get(position).getTphone()));

                v.getContext().startActivity(intent);

            }
        });


        holder.tv_email.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("IntentReset")
            @Override
            public void onClick(View v) {

                String recipients= t.getTemail().toString();
                Intent email_intent= new Intent(Intent.ACTION_SEND);
                //intent.putExtra(Intent.EXTRA_EMAIL,recipients);
                email_intent.putExtra(Intent.EXTRA_EMAIL,new String[]{recipients}); //nultiple mail
                email_intent.setType("messages/rfc822");  //only email clients will open
                v.getContext().startActivity(Intent.createChooser(email_intent,"Send Email")); //chooser open

                //intent.setData(Uri.parse("tel:"+list.get(position).getTemail()));
                //v.getContext().startActivity(new Intent(Intent.ACTION_SENDTO,
                // Uri.parse(list.get(position).getTemail())));
                //Intent intent = new Intent(Intent.ACTION_SEND);


/*
                intent.setData(Uri.parse("tel:"+list.get(position).getTemail()));


*/
            }
        });




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {

        TextInputLayout tv_name_layout,tv_email_layout,tv_phone_layout;
        TextView tv_name,tv_email,tv_phone;
        Button btn_email,btn_phone;
        TextView t;

        public MyviewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            //tf_teacher_name_layout=(TextInputLayout) itemView.findViewById(R.id.tf_teacher_name_layout);
            // tf_teacher_name=(TextInputEditText)itemView.findViewById(R.id.  tf_teacher_name);

            //tv_name_layout= ( TextInputLayout ) itemView.findViewById(R.id.tv_name_layout);
            //tv_email_layout= ( TextInputLayout  ) itemView.findViewById(R.id.tv_name_layout);
            //tv_phone_layout= (  TextInputLayout ) itemView.findViewById(R.id.tv_phone_layout);

            // t=(TextView)itemView.findViewById(R.id.Textview2);
            tv_name= ( TextView) itemView.findViewById(R.id.tv_name);
            tv_email= (  TextView ) itemView.findViewById(R.id.tv_email);
            tv_phone = (  TextView ) itemView.findViewById(R.id.tv_phone);
            //btn_email=(Button)itemView.findViewById(R.id.btn_email);
            //btn_phone=(Button)itemView.findViewById(R.id.btn_phone);

        }
    }



/*
    public teachers_adapter(Context context, ArrayList<teacher_recyclerview_modelclass> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public Myadapter.MyviewHolde onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_of_teacher_recyclerview,parent,false);

        Myadapter.MyviewHolder holder =new Myadapter.MyviewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Myadapter.MyviewHolde holder, int position) {

        teacher_recyclerview_modelclass t = list.get(position);




    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public static class MyviewHolde extends RecyclerView.ViewHolder{


        TextInputLayout tf_teacher_name_layout;

        TextInputEditText tf_teacher_name;
        Button btn_email,btn_phone;



        public MyviewHolde(@NonNull View itemView) {
            super(itemView);

            tf_teacher_name_layout=(TextInputLayout) itemView.findViewById(R.id.tf_teacher_name_layout);
            tf_teacher_name=(TextInputEditText)itemView.findViewById(R.id.  tf_teacher_name);
            btn_email=(Button)itemView.findViewById(R.id.btn_email);
            btn_phone=(Button)itemView.findViewById(R.id.btn_phone);


        }




    }

*/
}