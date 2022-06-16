package com.example.cp_admin_pannel;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class actionviewadapter extends RecyclerView.ViewHolder {
    TextView date,detail,loc,time;
    public actionviewadapter(View itemView){
        super(itemView);
        date=itemView.findViewById(R.id.date_action);
        time=itemView.findViewById(R.id.time_action);
        detail=itemView.findViewById(R.id.detail_action);
        loc=itemView.findViewById(R.id.loc_Action);
    }
}
