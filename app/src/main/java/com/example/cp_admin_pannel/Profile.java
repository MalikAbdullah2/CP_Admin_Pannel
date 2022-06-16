package com.example.cp_admin_pannel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        databaseReference = FirebaseDatabase.getInstance().getReference("Admin").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        final EditText edFullName = findViewById(R.id.name_profile);
        final EditText edGender = findViewById(R.id.Gender_profile);
        final EditText edEmail =findViewById(R.id.Email_profile);
        final EditText edContact = findViewById(R.id.contact_profile);
        Button btUpdate = findViewById(R.id.update_profile);


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

              admin  admininfo= dataSnapshot.getValue(admin.class);

                edFullName.setText( admininfo.getName());
                edGender.setText( admininfo.getGender());
                edEmail.setText( admininfo.getEmail());
                edContact.setText( admininfo.getContact());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                admin  admininfo = new admin(edFullName.getText().toString(),edGender.getText().toString(),edEmail.getText().toString(),edContact.getText().toString());

                databaseReference.setValue( admininfo)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isComplete())
                                    Toast.makeText(Profile.this, "Data Updated", Toast.LENGTH_SHORT).show();
                            }
                        });



            }
        });

    }
}
