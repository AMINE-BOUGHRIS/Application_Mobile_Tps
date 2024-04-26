package com.example.applicationmobiletps;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Tp1_with_Tp2 extends AppCompatActivity {
    private EditText firstName, lastName;
    private TextView currenttext, totaltext;
    private Button recived, Startover,insert,totalbtn;
    private BufferedReader reader;
    String firstLine;
    int current=0,lines=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tp1_with_tp2);
        //Variable Initialization
        firstName = findViewById(R.id.firstname);
        lastName = findViewById(R.id.lastname);
        currenttext = findViewById(R.id.currenttext);
        totaltext = findViewById(R.id.totaltext);
        totalbtn = findViewById(R.id.totalbtn);
        insert = findViewById(R.id.insert);
        Startover = findViewById(R.id.startover);
        recived = findViewById(R.id.recived);

        //insert button
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstNameText = firstName.getText().toString(); // Get text from EditTexts
                String lastNameText = lastName.getText().toString();

                // Check if names are empty before saving
                if (firstNameText.isEmpty() || lastNameText.isEmpty()) {
                    Toast.makeText(Tp1_with_Tp2.this, "Please enter both first and last name", Toast.LENGTH_SHORT).show();
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
                    recived.setEnabled(true);
                    Toast.makeText(Tp1_with_Tp2.this, "Names saved successfully", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });
        //recived button

        try {
            // Open file for reading (Context.MODE_PRIVATE is for writing)
            FileInputStream inputStream = openFileInput("tp1.txt");
            reader = new BufferedReader(new InputStreamReader(inputStream));

            // Read the first line immediately
            firstLine = reader.readLine();
            current++;

            recived.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (firstLine != null && !firstLine.isEmpty()) {
                        String[] names = firstLine.split(",");
                        currenttext.setText(String.valueOf(current));
                        if (names.length >= 1) {
                            firstName.setText(names[0]);
                        }
                        if (names.length >= 2) {
                            lastName.setText(names[1]);
                        } else {
                            lastName.setText("");
                        }

                        // Read the next line for subsequent clicks
                        try {
                            firstLine = reader.readLine();
                            current++;
                        } catch (IOException e) {
                            // Handle potential exception at end of file
                            e.printStackTrace();
                            Toast.makeText(Tp1_with_Tp2.this, "No more names to read", Toast.LENGTH_SHORT).show();
                            recived.setEnabled(false);
                        }
                    } else {
                        // Handle the case where the file is empty or doesn't exist
                        Toast.makeText(Tp1_with_Tp2.this, "No names found in the file", Toast.LENGTH_SHORT).show();
                        recived.setEnabled(false);
                    }
                }
            });
            //start over button
            Startover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        // Open file for reading (Context.MODE_PRIVATE is for writing)
                        FileInputStream inputStream = openFileInput("tp1.txt");
                        reader = new BufferedReader(new InputStreamReader(inputStream));

                        // Read the first line immediately
                        firstLine = reader.readLine();
                    } catch (FileNotFoundException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    firstName.setText("");
                    lastName.setText("");
                    currenttext.setText("");
                    recived.setEnabled(true);
                }
            });
        } catch (FileNotFoundException e) {
            // File not found (likely no names saved yet)
            Toast.makeText(Tp1_with_Tp2.this, "File Not Found", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            // Handle other IO exceptions
        }
        //total button
        totalbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int total = 0;
                try {
                    // Open the file for reading line by line (avoiding loading everything in memory)
                    FileInputStream inputStream = openFileInput("tp1.txt");
                    BufferedReader lineReader = new BufferedReader(new InputStreamReader(inputStream));

                    // Read each line and increment the count
                    while (lineReader.readLine() != null) {
                        total++;
                    }

                    lineReader.close();

                    // Display the total count in the totaltext TextView
                    totaltext.setText(String.valueOf(total));
                } catch (FileNotFoundException e) {
                    // Handle file not found (no names saved yet)
                    Toast.makeText(Tp1_with_Tp2.this, "File Not Found", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    // Handle other IO exceptions
                }
            }
        });


    }


}
