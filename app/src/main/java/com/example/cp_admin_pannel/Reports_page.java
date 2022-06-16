package com.example.cp_admin_pannel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.card.MaterialCardView;

public class Reports_page extends AppCompatActivity implements View.OnClickListener {
    MaterialCardView cvAllIncident,cvmurder,cvrape,cvactionkilling,cvharasement,cvCurroption,cvsnatching,cvrobberuy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports_page);
        cvAllIncident=findViewById(R.id.cv_all_incident);
        cvactionkilling=findViewById(R.id.cv_actionkilling);
        cvCurroption=findViewById(R.id.cv_corruption);
        cvharasement=findViewById(R.id.cv_harrasement);
        cvmurder=findViewById(R.id.cv_murder);
        cvrape=findViewById(R.id.cv_rape);
        cvsnatching=findViewById(R.id.cv_snatching);
        cvrobberuy=findViewById(R.id.cv_robbery);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cv_actionkilling:
                Intent i=new Intent(getApplicationContext(),ActionKiling.class);
                startActivity(i);
                break;
            case R.id.cv_corruption:
                Intent a=new Intent(getApplicationContext(),corruption.class);
                startActivity(a);
                break;
            case R.id.cv_harrasement:
                Intent b=new Intent(getApplicationContext(),Home.class);
                startActivity(b);
                break;
            case R.id.cv_murder:
                Intent c=new Intent(getApplicationContext(),murder_report.class);
                startActivity(c);
                break;
            case R.id.cv_rape:
                Intent d=new Intent(getApplicationContext(),Home.class);
                startActivity(d);
                break;
            case R.id.cv_robbery:
                Intent e=new Intent(getApplicationContext(),Home.class);
                startActivity(e);
                break;
            case R.id.cv_snatching:
                Intent f=new Intent(getApplicationContext(),Home.class);
                startActivity(f);
                break;
            case R.id.cv_all_incident:
                Intent m=new Intent(getApplicationContext(),All_incident_reports.class);
                startActivity(m);
                break;
            default:
                break;
        }


    }
}
