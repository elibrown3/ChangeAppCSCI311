package com.example.pocketchangecollecter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class totalView extends AppCompatActivity {
    private TextView walletTotal;
    private float pocketTotal;
    private TextView pocketTotal2;
    private Button walletButton;
    private Button walletClearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_total_view);
        walletButton = findViewById(R.id.walletEditButton);
        walletTotal = findViewById(R.id.walletTotal);
        pocketTotal2 = findViewById(R.id.pocketTotal2);
        SharedPreferences sharedPref = getSharedPreferences("pocketChange", Context.MODE_PRIVATE);
        pocketTotal = sharedPref.getFloat("PocketTotal", 99);
        pocketTotal2.setText(pocketTotal + "");
        float walletTotal2 = sharedPref.getFloat("WalletTotal", 0);


        walletButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float wTotal = Float.parseFloat(walletTotal.getText().toString());
                wTotal += pocketTotal;
                walletTotal.setText(wTotal + "");
                pocketTotal = 0;
                pocketTotal2.setText(pocketTotal + "");
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putFloat("PocketTotal", 0);
                editor.putFloat("WalletTotal", wTotal);
                editor.apply();
            }
        });

        walletTotal.setText(Math.round(walletTotal2 * 100.0) / 100.0 + "");

        walletClearButton = findViewById(R.id.pocketClearButton);
        walletClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = getSharedPreferences("pocketChange", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putFloat("WalletTotal", 0);
                editor.apply();
                //walletTotal2 = 0;
                walletTotal.setText("0");

            }
        });



    }
}