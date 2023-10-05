package com.example.project22;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Cg_show_Adapter extends RecyclerView.Adapter<Cg_show_Myviewholder > {


    Context context;
    ArrayList<CgShow_model> list;
    //String roll = Login.getActivityInstance()[2];

    public Cg_show_Adapter(Context context, ArrayList<CgShow_model> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @NotNull
    @Override
    public Cg_show_Myviewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.retrieved_cg_show_esult,parent,false);
        return new Cg_show_Myviewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull Cg_show_Myviewholder holder, int position) {

        CgShow_model cgShow_model= list.get(position);
        if(cgShow_model.getSemester().equals("1_1"))
        {
            holder.semester_cg_show.setText("1.1");
        }
        else if(cgShow_model.getSemester().equals("1_2"))
        {
            holder.semester_cg_show.setText("1.2");
        }

        else if(cgShow_model.getSemester().equals("2_1"))
        {
            holder.semester_cg_show.setText("2.1");
        }

        else if(cgShow_model.getSemester().equals("2_2"))
        {
            holder.semester_cg_show.setText("2.2");
        }
        else if(cgShow_model.getSemester().equals("3_1"))
        {
            holder.semester_cg_show.setText("3.1");
        }

        else if(cgShow_model.getSemester().equals("3_1"))
        {
            holder.semester_cg_show.setText("3.1");
        }

        else if(cgShow_model.getSemester().equals("4_1"))
        {
            holder.semester_cg_show.setText("4.1");
        }
        else if(cgShow_model.getSemester().equals("4_2"))
        {
            holder.semester_cg_show.setText("4.2");
        }


        //holder.semester_cg_show.setText(cgShow_model.getSemester());
        holder.cg_result.setText(cgShow_model.getResult());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
