package com.example.thingstodo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class Login extends AppCompatActivity {
    TextView create;
    EditText email , password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        email=findViewById(R.id.loginemail);
        password=findViewById(R.id.loginpass);
        login=findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Login.this.doSignIn(email.getText().toString(),password.getText().toString());

                Intent i=new Intent(Login.this,ListActivity.class);
                startActivity(i);
            }
        });



        create=findViewById(R.id.btncreate);
        create.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent i2=new Intent(Login.this,Signup.class);
                startActivity(i2);
            }
        });
    }
    private void doSignIn (String email, String password) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull final Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "LogIn Successfully", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Login.this, ListActivity.class);
                            startActivity(i);
                        }

                    }
                });
    }
}