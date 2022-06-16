package com.example.cp_admin_pannel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLoginhome,btnRegSignup,btnLoginGoogle;
    EditText edtEmailMain,edtPassMain;
    DatabaseReference databaseReference;
    TextView tvForgotMain;
    String validemail = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@" + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" +

            "\\." +

            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

            ")+";
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        btnLoginhome = findViewById(R.id.btn_login_admin);
        btnRegSignup=findViewById(R.id.btn_signup_admin);
        edtEmailMain=findViewById(R.id.edt_mail_admin);
        edtPassMain=findViewById(R.id.edt_pass_admin);
        btnLoginhome.setOnClickListener(this);
        btnRegSignup.setOnClickListener(this);
        firebaseAuth=FirebaseAuth.getInstance();

    }
    @Override
    protected void onStart() {
        super.onStart();
        if (firebaseAuth.getCurrentUser()!=null){
            finish();
            Toast.makeText(getApplicationContext(),"Already Logged in",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(),Home.class));

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.btn_login_admin:
                if (checkForsignupDeatils()) {
                    String email = edtEmailMain.getText().toString().trim();
                    String password = edtPassMain.getText().toString().trim();


                    firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        if (firebaseAuth.getCurrentUser().isEmailVerified()){
                                            Toast.makeText(getApplicationContext(), "Login Successful " + edtEmailMain.getText().toString() +
                                                    "/n" + edtPassMain.getText().toString(), Toast.LENGTH_LONG).show();
                                            Intent i = new Intent(getApplicationContext(), Home.class);
                                            startActivity(i);
                                        }
                                        else {
                                            Toast.makeText(MainActivity.this,"please verify email"
                                                    ,Toast.LENGTH_LONG).show();
                                        }


                                    } else {

                                        Toast.makeText(MainActivity.this,task.getException().getMessage()
                                                ,Toast.LENGTH_LONG).show();
                                    }


                                }
                            });
                }



                break;
            case R.id.btn_signup_admin:
                Intent j=new Intent(getApplicationContext(),Signup.class);
                startActivity(j);
                break;

            default:
                break;
        }

    }





    private boolean checkForsignupDeatils() {
        String email = edtEmailMain.getText().toString();
        if (TextUtils.isEmpty(email)){
            edtEmailMain.setError("Enter email");
        }
        if (edtPassMain.getText().toString().equals("") || edtPassMain.length() < 3) {edtPassMain.setError("at least 3 characters");
            edtPassMain.setFocusable(true);
            return false;
        } else {
            edtPassMain.setError(null);
        }


        return true;
    }


    }

