package com.example.cp_admin_pannel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class murder_report extends AppCompatActivity {
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    RecyclerView recyclerView;
    ArrayList<report_retrieve>list;
    Adaptor adaptor;
    FirebaseAuth firebaseAuth;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_murder_report);
        firebaseAuth=FirebaseAuth.getInstance();
        uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Murder Reports");
        recyclerView=findViewById(R.id.recyclerView_murder);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<report_retrieve>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){

                    report_retrieve r=dataSnapshot1.getValue(report_retrieve.class);
                    list.add(r);
                }
                adaptor=new Adaptor(murder_report.this,list);
                recyclerView.setAdapter(adaptor);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(murder_report.this,"OOPSSS....something is wrong",Toast.LENGTH_LONG).show();

            }
        });
    }


}
