package com.example.thingstodo;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewTaskActivity extends AppCompatActivity {
    private EditText dets_description;
    private TextView textview_edit;
    private TextView back_viewTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        TextView detsTaskName = findViewById(R.id.dets_taskName);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String taskName = bundle.getString("taskName");

            detsTaskName.setText(taskName);
        }

        dets_description = findViewById(R.id.dets_description);
        textview_edit = findViewById(R.id.textview_edit);

        if (!dets_description.getText().toString().isEmpty()) {
            dets_description.setEnabled(false);
            textview_edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dets_description.setEnabled(true);
                }
            });
        }

        back_viewTask= findViewById(R.id.back_viewTask);
        back_viewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}