package com.example.developernote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ErrorNote extends AppCompatActivity {

    Button btnAddErrorContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error_note);

        btnAddErrorContent = (Button)findViewById(R.id.btnAddErrorContent);

        btnAddErrorContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearLayout todoListLayout = (LinearLayout) findViewById(R.id.errornoteList);
                todoListLayout.addView(new listContent(getApplicationContext()),1);
            }
        });
    }



}