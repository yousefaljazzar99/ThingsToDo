package com.example.thingstodo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class screen3 extends AppCompatActivity {
    TextView create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen33);
        create=findViewById(R.id.btncreate);
        create.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();

            }
        });
    }
}