package com.example.thingstodo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    TextView already;
    EditText password , email;
    Button create;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        mAuth = FirebaseAuth.getInstance();
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        create=findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Signup.this.doSignUp(email.getText().toString(),password.getText().toString());
            }
        });
        already=findViewById(R.id.btnlogin);
        already.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(Signup.this, Login.class);
                startActivity(i);
            }
        });
    }
    private void doSignUp (String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull final Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            String uid = user.getUid();
                            Map<String, Object> data = new HashMap<>();
                            data.put("uid", uid);

                            FirebaseDatabase.getInstance().getReference("Users").child(uid).setValue(data)
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Signup.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                            Log.d("Error", e.getLocalizedMessage());
                                        }
                                    }).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Intent i2 = new Intent(Signup.this, Login.class);
                                    startActivity(i2);
                                }
                            });

                            Toast.makeText(Signup.this, "Account Created Succesfully", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
}
