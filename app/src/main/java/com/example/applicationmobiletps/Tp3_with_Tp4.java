package com.example.applicationmobiletps;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Tp3_with_Tp4 extends AppCompatActivity {
    private EditText firstName, lastName;
    private TextView currenttext, totaltext;
    private Button recived, Startover,insert,totalbtn;
    private MySQLiteHelper dbHelper;
    int currentRow = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tp3_with_tp4);
        //Variable Initialization
        firstName = findViewById(R.id.firstname);
        lastName = findViewById(R.id.lastname);
        currenttext = findViewById(R.id.currenttext);
        totaltext = findViewById(R.id.totaltext);
        totalbtn=findViewById(R.id.totalbtn);
        insert = findViewById(R.id.insert);
        Startover=findViewById(R.id.startover);
        recived = findViewById(R.id.recived);
        dbHelper = new MySQLiteHelper(this);

        //insert button
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fname = firstName.getText().toString();
                String lname = lastName.getText().toString();

                SQLiteDatabase db = dbHelper.getWritableDatabase(); // Get database instance
                ContentValues values = new ContentValues();
                values.put("FirstName", fname);
                values.put("LastName", lname);

                long result = db.insert("Students", null, values);

                if (result != -1) {
                    Toast.makeText(Tp3_with_Tp4.this, "Data saved successfully", Toast.LENGTH_SHORT).show();
                    firstName.setText(""); // Clear input fields
                    lastName.setText("");
                } else {
                    Toast.makeText(Tp3_with_Tp4.this, "Error saving data", Toast.LENGTH_SHORT).show();
                }
                //db.close(); // Close the database
            }
        });
        //recived btn

        recived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                Cursor cursor = db.query("Students", new String[]{"FirstName", "LastName"}, null, null, null, null, null);

                if (cursor.getCount() > 0) {
                    if (currentRow == -1) {
                        // First click, move to the first row
                        cursor.moveToFirst();
                        currentRow = 0;
                    } else if (cursor.moveToPosition(currentRow + 1)) {
                        // Subsequent clicks, move to the next row if possible
                        currentRow++;
                    } else {
                        // Reached end of data
                        recived.setEnabled(false);

                        Toast.makeText(Tp3_with_Tp4.this, "There is no more", Toast.LENGTH_SHORT).show();
                        currentRow = -1; // Reset for next retrieval from beginning
                    }

                    if (currentRow != -1) {
                        // Data retrieved successfully, update fields and toast message
                        String fname = cursor.getString(0);
                        String lname = cursor.getString(1);
                        firstName.setText(fname);
                        lastName.setText(lname);
                        Toast.makeText(Tp3_with_Tp4.this, "Data retrieved successfully", Toast.LENGTH_SHORT).show();
                        String line= String.valueOf(currentRow+1);
                        currenttext.setText(line);

                    }
                } else {
                    // No data found in the database
                    Toast.makeText(Tp3_with_Tp4.this, "No data found in the database", Toast.LENGTH_SHORT).show();
                }
                //cursor.close();
            }


        });
        //startover button
        Startover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentRow=-1;
                firstName.setText("");
                lastName.setText("");
                recived.setEnabled(true);
            }
        });
        //totalbtn
        totalbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totaltext.setText(String.valueOf(dbHelper.getNumberOfStudents()));
            }
        });
    }
}