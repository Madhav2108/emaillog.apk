package com.example.emaillogin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button singin,login;
    EditText pwds,emails;
    FirebaseAuth mauth;
    boolean islogin=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      mauth=FirebaseAuth.getInstance();

    emails=findViewById(R.id.email);
    pwds=findViewById(R.id.pwd);
    singin=findViewById(R.id.sign2);
    login=findViewById(R.id.login);


        mauth.createUserWithEmailAndPassword("njnitesh82@gmail.com","1234456").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Done",Toast.LENGTH_SHORT).show();
                }

            }
        });

   singin.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            if((emails.getText().length()!=0)&(pwds.getText().length()!=0))
            {
                mauth.createUserWithEmailAndPassword(""+emails.getText(),""+pwds.getText()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "You have sucessfuly registered!!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this,"something went wrong",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }


    });
login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if((emails.getText().length()!=0)&(pwds.getText().length()!=0)){

            mauth.signInWithEmailAndPassword(emails.getText().toString()
            ,pwds.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(task.isSuccessful()) {
                        Toast.makeText(MainActivity.this,"login",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),login.class));
                    }else{
                        Toast.makeText(MainActivity.this,"wrong user id and password",Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }
    }
});
/*
    singin.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v){
            if((emails.getText().length()!=0)&(pwds.getText().length()!=0)){
                //Toast.makeText(MainActivity.this,"please enter Your userid andd password",Toast.LENGTH_SHORT).show();

                if((emails.getText().equals("admin"))     &
                (pwds.getText().equals("admin"))){
                    Toast.makeText(MainActivity.this,"login successful",Toast.LENGTH_SHORT).show();


                    Intent i=new Intent(MainActivity.this,login.class);
                    startActivity(i);

                }

            }
        }
    });

*/








    }

    @Override
    protected void onStart(){
        super.onStart();
        FirebaseUser user=mauth.getCurrentUser();

        if(user!=null) {
            islogin = true;
            Intent i = new Intent(MainActivity.this, login.class);
            startActivity(i);
        }

    }
}
