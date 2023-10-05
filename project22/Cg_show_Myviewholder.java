package com.example.project22;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class Cg_show_Myviewholder extends RecyclerView.ViewHolder {

    TextView semester_cg_show,cg_result;


    public Cg_show_Myviewholder(@NonNull @NotNull View itemView) {
        super(itemView);
        semester_cg_show=itemView.findViewById(R.id.cg_show_semester);
        cg_result=itemView.findViewById(R.id.cg_result);

    }
}
