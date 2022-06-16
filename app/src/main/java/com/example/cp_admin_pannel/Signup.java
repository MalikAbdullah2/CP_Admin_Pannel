package com.example.cp_admin_pannel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Signup extends AppCompatActivity  {
    Button btnsignupsignup;
    EditText edtEmailsignup, edtPasssignup, edtgendersignup, edtcontactsignup, edtnamesignup;
    String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" +

            "\\." +

            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

            ")+";
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    admin admininfo;
    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth = FirebaseAuth.getInstance();
        btnsignupsignup = findViewById(R.id.btn_signup_signup);
        edtEmailsignup = findViewById(R.id.edt_email_signup);
        edtnamesignup = findViewById(R.id.edt_name_signup);
        edtPasssignup = findViewById(R.id.edt_pass_signup);
        edtgendersignup = findViewById(R.id.edt_gender_signup);
        edtcontactsignup = findViewById(R.id.edt_contact_signup);
        databaseReference = FirebaseDatabase.getInstance().getReference("Admin");
        firebaseDatabase = FirebaseDatabase.getInstance();
    }
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_signup_signup:
                if (checkForsignupDeatils()) {
                    final String email = edtEmailsignup.getText().toString();
                    final String name = edtnamesignup.getText().toString();
                    final String password = edtPasssignup.getText().toString();
                    final String gender = edtgendersignup.getText().toString();
                    final String contact = edtcontactsignup.getText().toString();

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        admininfo = new admin(
                                                email, name, gender, contact
                                        );
                                        firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    databaseReference.child(firebaseAuth.getCurrentUser().getUid()).setValue(admininfo);

                                                    Toast.makeText(Signup.this, "data inserted" +
                                                            ",please check email for verification", Toast.LENGTH_LONG).show();
                                                    Intent j = new Intent(getApplicationContext(), MainActivity.class);
                                                    j.putExtra("number",contact);
                                                    startActivity(j);


                                                } else {
                                                    Toast.makeText(Signup.this, task.getException().getMessage(),
                                                            Toast.LENGTH_SHORT).show();

                                                }
                                            }

                                        });


                                    } else {


                                    }

                                }
                            });
                }
                break;
            default:
                break;
        }


    }

    private boolean checkForsignupDeatils() {
        String email = edtEmailsignup.getText().toString();
        Matcher matcher = Pattern.compile(validemail).matcher(email);
        if (!matcher.matches()) {
            edtEmailsignup.setError("Enter valid email");
            edtEmailsignup.setFocusable(true);
            return false;
        }
        if (edtnamesignup.getText().toString().equals("") || edtnamesignup.length() < 3) {
            edtnamesignup.setError("at least 3 characters");
            edtnamesignup.setFocusable(true);
            return false;
        } else {
            edtnamesignup.setError(null);
        }


        return true;
    }
}
