package com.example.thingstodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class screen2 extends AppCompatActivity {
    TextView already;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen22);
        already=findViewById(R.id.btnlogin);
        already.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(screen2.this,screen3.class);
                startActivity(i);
            }
        });
    }
}