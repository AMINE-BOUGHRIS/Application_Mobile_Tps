package com.example.applicationmobiletps;


import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Tp3 extends AppCompatActivity {
    private EditText firstName, lastName;
    private MySQLiteHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tp3);
        firstName = findViewById(R.id.firstname);
        lastName = findViewById(R.id.lastname);
        Button savebtn = findViewById(R.id.save);

        dbHelper = new MySQLiteHelper(this); // Create database helper

        savebtn.setOnClickListener(v -> {
            String fname = firstName.getText().toString();
            String lname = lastName.getText().toString();

            SQLiteDatabase db = dbHelper.getWritableDatabase(); // Get database instance
            ContentValues values = new ContentValues();
            values.put("FirstName", fname);
            values.put("LastName", lname);

            long result = db.insert("Students", null, values);

            if (result != -1) {
                Toast.makeText(Tp3.this, "Data saved successfully", Toast.LENGTH_SHORT).show();
                firstName.setText(""); // Clear input fields
                lastName.setText("");
            } else {
                Toast.makeText(Tp3.this, "Error saving data", Toast.LENGTH_SHORT).show();
            }
            db.close(); // Close the database
        });

    }


}
