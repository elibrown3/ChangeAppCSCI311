package com.example.pocketchangecollecter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SeekBar pennySeekBar;
    private TextView pennyLabel;

    private SeekBar nickelSeekBar;
    private TextView nickelLabel;


    private SeekBar dimeSeekBar;
    private TextView dimeLabel;

    private SeekBar quarterSeekBar;
    private TextView quarterLabel;

    private Button pocketButton;
    private TextView pocketTotal;

    private Button walletButton;
    private Button pocketClearButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initWalletButton();

        pennyLabel = findViewById(R.id.pennyLabel);
        pennySeekBar = findViewById(R.id.pennySeekBar);
        pennySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                pennyLabel.setText(i+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        nickelLabel = findViewById(R.id.nickelLabel);
        nickelSeekBar = findViewById(R.id.nickelSeekBar);
        nickelSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                nickelLabel.setText(i+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        dimeLabel = findViewById(R.id.dimeLabel);
        dimeSeekBar = findViewById(R.id.dimeSeekBar);
        dimeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                dimeLabel.setText(i+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        quarterLabel = findViewById(R.id.quarterLabel);
        quarterSeekBar = findViewById(R.id.quarterseekBar);
        quarterSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                quarterLabel.setText(i+"");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });



        pocketTotal = findViewById(R.id.textView);
        pocketButton = findViewById(R.id.pocketButton);


        pocketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               SharedPreferences sharedPref = getSharedPreferences("pocketChange", Context.MODE_PRIVATE);
               float total = Float.parseFloat(pocketTotal.getText().toString());
               total += pennySeekBar.getProgress()*.01 + nickelSeekBar.getProgress()*.05 + dimeSeekBar.getProgress()*.1 + quarterSeekBar.getProgress()*.25;
               SharedPreferences.Editor editor = sharedPref.edit();
               editor.putFloat("PocketTotal", total);
               editor.apply();
               pocketTotal.setText(Math.round(total * 100.0) / 100.0 + "");
            }
        });

        pocketClearButton = findViewById(R.id.pocketClearButton);
        pocketClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPref = getSharedPreferences("pocketChange", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putFloat("PocketTotal", 0);
                editor.apply();
                pocketTotal.setText("0");
            }
        });
    }

    public void onResume() {
        super.onResume();
        float pocketTotal2 = 0;
        SharedPreferences sharedPref = getSharedPreferences("pocketChange", Context.MODE_PRIVATE);
        pocketTotal2 = sharedPref.getFloat("PocketTotal", 99);
        pocketTotal.setText(Math.round(pocketTotal2 * 100.0) / 100.0 + "");
    }


    private void initWalletButton() {
        Button walletButton = findViewById(R.id.walletButton);
        walletButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, totalView.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }





}