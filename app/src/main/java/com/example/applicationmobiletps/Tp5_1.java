package com.example.applicationmobiletps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class Tp5_1 extends AppCompatActivity {
    EditText text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tp51);
        text=findViewById(R.id.name);
        Intent intent=getIntent();
        if(intent!=null){
        String fname=intent.getStringExtra("Firstname");
        String lname=intent.getStringExtra("Lastname");
        text.setText(fname+" "+lname);
        }else{
            Toast.makeText(this, "There is no data ", Toast.LENGTH_SHORT).show();
        }


    }
}