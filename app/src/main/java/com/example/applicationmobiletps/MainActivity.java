package com.example.applicationmobiletps;


import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button tp1,tp2,tp3,tp4,tp5,tp6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tp1=findViewById(R.id.tp1);
        tp2=findViewById(R.id.tp2);
        tp3=findViewById(R.id.tp3);
        tp4=findViewById(R.id.tp4);
        tp5=findViewById(R.id.tp5);
        tp6=findViewById(R.id.tp6);


        tp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Tp1.class);
                startActivity(intent);
            }
        });
        tp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Tp2.class);
                startActivity(intent);
            }
        });
        tp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Tp3.class);
                startActivity(intent);
            }
        });

        tp4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Tp4.class);
                startActivity(intent);
            }
        });
        tp5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Tp5.class);
                startActivity(intent);
            }
        });
        tp6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Tp6.class);
                startActivity(intent);
            }
        });

    }
}