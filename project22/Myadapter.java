package com.example.project22;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class Myadapter extends RecyclerView.Adapter <Myadapter.MyviewHolder>{

    Context context;
    //String data[];
    ArrayList<data> list;
    String sub[];

    double[] inputed_grade = new double[50];
    double[]  inputed_credit = new double[50];


    public Myadapter(Context context, ArrayList<data> list) {
        this.context = context;
        this.list = list;
    }



    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_of_recyclerview,parent,false);

        MyviewHolder holder =new  MyviewHolder(view);
        return holder;




    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull  MyviewHolder holder, @SuppressLint("RecyclerView") int position) {


        data d = list.get(position);

        //holder.subject.setText(d.getSubject()+""+d.getCredit()+"");
        holder.credit.setText(d.getSubject()+""+"("
                +d.getCredit()+""+")");
        inputed_credit[position] = Double.parseDouble(String.valueOf(d.getCredit()));

        Log.d("myTag", String.valueOf(inputed_credit[position]));



        holder.subject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                //KOTO PAISI EKTA SUBJ EH
                //Log.d("myTag", String.valueOf(inputed_grade[position]));
                //inputed_grade[position] = Double.parseDouble(s.toString());
                if(s.length()>0)
                {
                    if(s.charAt(0)=='.')
                    {
                        s="0"+s;
                    }
                    inputed_grade[position] = Double.parseDouble(s.toString());
                    Log.d("myTag>0", String.valueOf(inputed_grade[position]));
                }

                else
                {
                    inputed_grade[position]=0.0;
                    Log.d("myTag<0", String.valueOf(inputed_grade[position]));
                }




            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();

    }



    public static class MyviewHolder extends RecyclerView.ViewHolder{



        TextInputLayout subject_layout,credit_layout;

        TextInputEditText subject,credit;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            subject_layout=(TextInputLayout) itemView.findViewById(R.id.subject_layout);

            credit_layout=(TextInputLayout) itemView.findViewById(R.id.credit_layout);

            subject=(TextInputEditText)itemView.findViewById(R.id.subject);
            credit=(TextInputEditText)itemView.findViewById(R.id.credit);


        }




    }



}


