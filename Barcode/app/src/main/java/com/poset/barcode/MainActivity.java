package com.poset.barcode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button nxt_button;
    CheckBox vegetarian_check;
    CheckBox vegan_check;
    CheckBox pork_check;
    CheckBox gluten_check;
    CheckBox fructose_check;
    CheckBox peanut_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        readData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        readData();
    }

    private void initViews() {
        nxt_button = findViewById(R.id.nxt_button);
        vegetarian_check = findViewById(R.id.vegetarian_check);
        vegan_check = findViewById(R.id.vegan_check);
        pork_check = findViewById(R.id.pork_check);
        gluten_check = findViewById(R.id.gluten_check);
        fructose_check = findViewById(R.id.fructose_check);
        peanut_check = findViewById(R.id.peanut_check);
        nxt_button.setOnClickListener(this);
        vegetarian_check.setOnClickListener(this);
        vegan_check.setOnClickListener(this);
        pork_check.setOnClickListener(this);
        gluten_check.setOnClickListener(this);
        fructose_check.setOnClickListener(this);
        peanut_check.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nxt_button:
                String str = "";
                str+=(vegetarian_check.isChecked())+"\n";
                str+=(vegan_check.isChecked())+"\n";
                str+=(pork_check.isChecked())+"\n";
                str+=(gluten_check.isChecked())+"\n";
                str+=(fructose_check.isChecked())+"\n";
                str+=(peanut_check.isChecked())+"\n";
                writeData(str);
                startActivity(new Intent(MainActivity.this, ScannedBarcodeActivity.class));
                break;
            case R.id.vegetarian_check:
                vegetarian_check.setChecked(vegetarian_check.isChecked());
                break;
            case R.id.vegan_check:
                vegan_check.setChecked(vegan_check.isChecked());
                break;
            case R.id.pork_check:
                pork_check.setChecked(pork_check.isChecked());
                break;
            case R.id.gluten_check:
                gluten_check.setChecked(gluten_check.isChecked());
                break;
            case R.id.fructose_check:
                fructose_check.setChecked(fructose_check.isChecked());
                break;
            case R.id.peanut_check:
                peanut_check.setChecked(peanut_check.isChecked());
                break;
        }
    }

    public void writeData ( String data ) {
        try {
            deleteFile("settings.dat");
            FileOutputStream fOut = openFileOutput ( "settings.dat" , Context.MODE_PRIVATE ) ;
            OutputStreamWriter osw = new OutputStreamWriter ( fOut ) ;
            osw.write ( data ) ;
            osw.flush ( ) ;
            osw.close ( ) ;
        } catch ( Exception e ) {
            e.printStackTrace ( ) ;
        }
    }


    public void readData(){
        int i = 0;
        try {
            FileInputStream fIn = openFileInput ( "settings.dat" ) ;
            InputStreamReader isr = new InputStreamReader ( fIn ) ;
            BufferedReader buffreader = new BufferedReader ( isr ) ;
            String readString = buffreader.readLine ( ) ;
            while ( readString != null ) {
                if(i == 0 && readString.equals("true")) vegetarian_check.setChecked(true);
                else if(i == 0) vegetarian_check.setChecked(false);

                if(i == 1 && readString.equals("true")) vegan_check.setChecked(true);
                else if(i == 1) vegan_check.setChecked(false);

                if(i == 2 && readString.equals("true")) pork_check.setChecked(true);
                else if(i == 2) pork_check.setChecked(false);

                if(i == 3 && readString.equals("true")) gluten_check.setChecked(true);
                else if(i == 3) gluten_check.setChecked(false);

                if(i == 4 && readString.equals("true")) fructose_check.setChecked(true);
                else if(i == 4) fructose_check.setChecked(false);

                if(i == 5 && readString.equals("true")) peanut_check.setChecked(true);
                else if(i == 5) peanut_check.setChecked(false);
                i++;
                readString = buffreader.readLine ( ) ;
            }
            isr.close ( ) ;
        } catch ( IOException ioe ) {
            ioe.printStackTrace ( ) ;
        }
    }
}



