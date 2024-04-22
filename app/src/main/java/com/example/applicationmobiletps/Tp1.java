package com.example.applicationmobiletps;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Tp1 extends AppCompatActivity {

    private EditText firstName;
    private EditText lastName;
    private Button saveButton; // Use descriptive names

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tp1);

        // Initialize design elements
        firstName = findViewById(R.id.firstname);
        lastName = findViewById(R.id.lastname);
        saveButton = findViewById(R.id.save);

        // Save button click listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNameText = firstName.getText().toString(); // Get text from EditTexts
                String lastNameText = lastName.getText().toString();

                // Check if names are empty before saving
                if (firstNameText.isEmpty() || lastNameText.isEmpty()) {
                    Toast.makeText(Tp1.this, "Please enter both first and last name", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    // Open file for appending
                    FileOutputStream fileOutputStream = openFileOutput("tp1.txt", Context.MODE_APPEND);
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
                    writer.write(firstNameText + "," + lastNameText); // Combine with comma
                    writer.newLine();
                    writer.close();

                    // Clear edit texts and show success toast
                    firstName.setText("");
                    lastName.setText("");
                    Toast.makeText(Tp1.this, "Names saved successfully", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
