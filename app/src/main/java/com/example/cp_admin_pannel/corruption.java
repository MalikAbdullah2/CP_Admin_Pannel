package com.example.cp_admin_pannel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class corruption extends AppCompatActivity {
RecyclerView corrRecycler;
DatabaseReference databaseReference,usersref;
FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corruption);
        corrRecycler=findViewById(R.id.recyclerView_corruption);
        firebaseAuth=FirebaseAuth.getInstance();
        String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
        corrRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Corruption Reports");
        usersref=FirebaseDatabase.getInstance().getReference("Users ");

    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerOptions<report_retrieve>options=new FirebaseRecyclerOptions.Builder<report_retrieve>()
                .setQuery(databaseReference,report_retrieve.class).build();
        FirebaseRecyclerAdapter<report_retrieve,CurroptionViewHolder> adapter=new FirebaseRecyclerAdapter<report_retrieve, CurroptionViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final CurroptionViewHolder curroptionViewHolder, int i, @NonNull final report_retrieve report_retrieve) {
               String userId=getRef(i).getKey();
                usersref.child(userId).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        curroptionViewHolder.date .setText(report_retrieve.getEdt_date());
                        curroptionViewHolder.time.setText(report_retrieve.getEdttime());
                        curroptionViewHolder.detail.setText(report_retrieve.getEdtdeatail());
                        curroptionViewHolder.loc.setText(report_retrieve.getEdtlocation());
                        Picasso.get().load(report_retrieve.getImgincident()).into(curroptionViewHolder.img);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }

            @NonNull
            @Override
            public CurroptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_murder,parent,false);
                return new CurroptionViewHolder(view);
            }
        };
        corrRecycler.setAdapter(adapter);
        adapter.startListening();
    }
    public static class CurroptionViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView date,time,detail,loc;

        public CurroptionViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.incident);
            date=itemView.findViewById(R.id.date_cv);
            time=itemView.findViewById(R.id.time_cv);
            loc=itemView.findViewById(R.id.loc_cv);
            detail=itemView.findViewById(R.id.detail_cv);
        }
    }
}
