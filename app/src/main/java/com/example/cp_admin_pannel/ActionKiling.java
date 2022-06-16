package com.example.cp_admin_pannel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ActionKiling extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<report_retrieve>arrayList;
    private FirebaseRecyclerOptions<report_retrieve>options;
    private FirebaseRecyclerAdapter<report_retrieve,actionviewadapter>adapter;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action_kiling);
        recyclerView=findViewById(R.id.recyclerView_action);
        firebaseAuth=FirebaseAuth.getInstance();
        String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference= FirebaseDatabase.getInstance().getReference("Action killing Reports");
        arrayList=new ArrayList<report_retrieve>();
        options=new FirebaseRecyclerOptions.Builder<report_retrieve>().setQuery(databaseReference.child(uid),report_retrieve.class).build();
        adapter=new FirebaseRecyclerAdapter<report_retrieve, actionviewadapter>(options) {
            @Override
            protected void onBindViewHolder(@NonNull actionviewadapter holder, int position, @NonNull report_retrieve model) {
                holder.loc.setText(model.getEdtlocation());
                holder.detail.setText(model.getEdtdeatail());
                holder.date.setText(model.getEdt_date());
                holder.time.setText(model.getEdttime());

            }

            @Override
            public void startListening()
            {
                super.startListening();
            }


            @NonNull
            @Override
            public actionviewadapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new actionviewadapter(LayoutInflater.from(ActionKiling.this).inflate(R.layout.cv_actionkilling,parent,false));
            }
        };
        recyclerView.setAdapter(adapter);
    }
}
