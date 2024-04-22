package com.example.applicationmobiletps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Tp5 extends AppCompatActivity {
    EditText et1,et2;
    Button nextbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tp5);
        et1=findViewById(R.id.firstname);
        et2=findViewById(R.id.lastname);
        nextbtn=findViewById(R.id.next);

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname=et1.getText().toString();
                String lname=et2.getText().toString();
                Intent intent=new Intent(Tp5.this,Tp5_1.class);
                intent.putExtra("Firstname",fname);
                intent.putExtra("Lastname",lname);
                startActivity(intent);
            }
        });

    }
}