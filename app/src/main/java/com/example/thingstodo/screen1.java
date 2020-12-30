package com.example.thingstodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class screen1 extends AppCompatActivity {
    TextView next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen11);
        next=findViewById(R.id.btnnext);
        next.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent i=new Intent(screen1.this,screen2.class);
                startActivity(i);
            }
        });
}
}