package com.example.applicationmobiletps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class Tp2 extends AppCompatActivity {

    private BufferedReader reader;
    String firstLine;
    private EditText firstNameEditText; // Use descriptive names
    private EditText lastNameEditText;
    private Button getNamesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tp2);

        firstNameEditText = findViewById(R.id.firstname);
        lastNameEditText = findViewById(R.id.lastname);
        getNamesButton = findViewById(R.id.save); // Likely referring to get operation, rename for clarity

        try {
            // Open file for reading (Context.MODE_PRIVATE is for writing)
            FileInputStream inputStream = openFileInput("tp1.txt");
            reader = new BufferedReader(new InputStreamReader(inputStream));

            // Read the first line immediately
            firstLine = reader.readLine();

            getNamesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (firstLine != null && !firstLine.isEmpty()) {
                        String[] names = firstLine.split(",");
                        if (names.length >= 1) {
                            firstNameEditText.setText(names[0]);
                        }
                        if (names.length >= 2) {
                            lastNameEditText.setText(names[1]);
                        } else {
                            lastNameEditText.setText("");
                        }

                        // Read the next line for subsequent clicks
                        try {
                            firstLine = reader.readLine();
                        } catch (IOException e) {
                            // Handle potential exception at end of file
                            e.printStackTrace();
                            Toast.makeText(Tp2.this, "No more names to read", Toast.LENGTH_SHORT).show();
                            getNamesButton.setEnabled(false);
                        }
                    } else {
                        // Handle the case where the file is empty or doesn't exist
                        Toast.makeText(Tp2.this, "No names found in the file", Toast.LENGTH_SHORT).show();
                        getNamesButton.setEnabled(false);
                    }
                }
            });
        } catch (FileNotFoundException e) {
            // File not found (likely no names saved yet)
            Toast.makeText(Tp2.this, "File Not Found", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle other IO exceptions
        }
    }
}
