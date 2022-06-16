package com.example.cp_admin_pannel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adaptor extends RecyclerView.Adapter<Adaptor.MyViewHolder> {
    Context context;
    ArrayList<report_retrieve> report_retrieves;
    public Adaptor(Context c,ArrayList<report_retrieve> r){
        context =c;
        report_retrieves=r;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cv_murder,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.date.setText(report_retrieves.get(position).getEdt_date());
        holder.detail.setText(report_retrieves.get(position).getEdtdeatail());
        holder.location.setText(report_retrieves.get(position).getEdtlocation());
        Picasso.get().load(report_retrieves.get(position).getImgincident()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return report_retrieves.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView date,location,detail;
        ImageView img;
        public MyViewHolder(View itemView){
            super(itemView);
            date=(TextView)itemView.findViewById(R.id.date_cv);
            location=(TextView)itemView.findViewById(R.id.loc_cv);
            detail=(TextView)itemView.findViewById(R.id.detail_cv);
            img=(ImageView)itemView.findViewById(R.id.incident);
        }
    }

}
