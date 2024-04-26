package com.example.applicationmobiletps;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Tp4 extends AppCompatActivity {

    private EditText firstName, lastName;
    private MySQLiteHelper dbHelper;
    int currentRow = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tp4);
        firstName = findViewById(R.id.firstname);
        lastName = findViewById(R.id.lastname);
        Button retrieveBtn = findViewById(R.id.recived); // Assuming a button for retrieval

        dbHelper = new MySQLiteHelper(this);
        // Initialize to -1 (no data retrieved yet)

        retrieveBtn.setOnClickListener(v -> {
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
                    Toast.makeText(Tp4.this, "Reached end of data", Toast.LENGTH_SHORT).show();
                    currentRow = -1; // Reset for next retrieval from beginning
                }

                if (currentRow != -1) {
                    // Data retrieved successfully, update fields and toast message
                    String fname = cursor.getString(0);
                    String lname = cursor.getString(1);
                    firstName.setText(fname);
                    lastName.setText(lname);
                    Toast.makeText(Tp4.this, "Data retrieved successfully", Toast.LENGTH_SHORT).show();
                }
            } else {
                // No data found in the database
                Toast.makeText(Tp4.this, "No data found in the database", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        });
    }
}