 package com.example.cp_admin_pannel;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;

 public class Home extends AppCompatActivity implements View.OnClickListener {

     MaterialCardView cvUser,cvProfile,cvReports,cvLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        cvLogout=findViewById(R.id.cv_logout);
        cvProfile=findViewById(R.id.cv_profile);
        cvReports=findViewById(R.id.cv_Reorts);
        cvUser=findViewById(R.id.cv_user);
    }

     @Override
     public void onClick(View view) {
        switch (view.getId()){
            case R.id.cv_logout:
                AlertDialog.Builder dialoug=new AlertDialog.Builder(Home.this);
                dialoug.setTitle("Logout");
                dialoug.setMessage("Are you sure to exit?");
                dialoug.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseAuth.getInstance().signOut();
                        Intent j=new Intent(Home.this,MainActivity.class);
                        j.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        j.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(j);

                    }
                });
                dialoug.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialoug=dialoug.create();
                alertDialoug.show();
                break;
            case R.id.cv_profile:
                Intent j=new Intent(getApplicationContext(),Profile.class);
                startActivity(j);
                break;
            case R.id.cv_Reorts:
                Intent c=new Intent(getApplicationContext(),Reports_page.class);
                startActivity(c);
                break;
            case R.id.cv_user:
                Intent u=new Intent(getApplicationContext(),user_retrieve.class);
                startActivity(u);
                break;
                default:
                    break;
        }

     }
 }
