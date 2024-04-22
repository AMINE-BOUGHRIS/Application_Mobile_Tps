package com.example.applicationmobiletps;
import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Tp6 extends AppCompatActivity {

    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tp6);

        editText = findViewById(R.id.speedtest);
        button = findViewById(R.id.test);

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SuspiciousIndentation")
            @Override
            public void onClick(View v) {
                // Récupérer le débit de réseaux mobiles
                String debit = getMobileNetworkSpeed();
                editText.setText("");

                // Afficher le débit
                if(debit.equals(""))
                    Toast.makeText(Tp6.this, "No connection", Toast.LENGTH_SHORT).show();
                else editText.setText(debit);
            }
        });
    }

    private String getMobileNetworkSpeed() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
                NetworkCapabilities nc = cm.getNetworkCapabilities(cm.getActiveNetwork());
                if (nc != null) {
                    double downSpeed = nc.getLinkDownstreamBandwidthKbps()/1024;
                    return  String.format("%.2f Mbps",downSpeed);
                }

        }
        return "";
    }
}
