package com.poset.barcode;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

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
    ConstraintLayout db1;
    ConstraintLayout db2;
    ConstraintLayout db3;
    ConstraintLayout db4;
    ConstraintLayout db5;
    ConstraintLayout db6;
    ImageView img1;
    ImageView img2;
    ImageView img3;
    ImageView img4;
    ImageView img5;
    ImageView img6;


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

        db1 = findViewById(R.id.db1);
        db2 = findViewById(R.id.db2);
        db3 = findViewById(R.id.db3);
        db4 = findViewById(R.id.db4);
        db5 = findViewById(R.id.db5);
        db6 = findViewById(R.id.db6);

        db1.setOnClickListener(this);
        db2.setOnClickListener(this);
        db3.setOnClickListener(this);
        db4.setOnClickListener(this);
        db5.setOnClickListener(this);
        db6.setOnClickListener(this);

        img1 = findViewById(R.id.image1);
        img2 = findViewById(R.id.image2);
        img3 = findViewById(R.id.image3);
        img4 = findViewById(R.id.image4);
        img5 = findViewById(R.id.image5);
        img6 = findViewById(R.id.image6);

        img1.setOnClickListener(this);
        img2.setOnClickListener(this);
        img3.setOnClickListener(this);
        img4.setOnClickListener(this);
        img5.setOnClickListener(this);
        img6.setOnClickListener(this);

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
            case R.id.image2:
                vegetarian_check.setChecked(vegetarian_check.isChecked());
                break;
            case R.id.image1:
                vegan_check.setChecked(vegan_check.isChecked());
                break;
            case R.id.image3:
                pork_check.setChecked(pork_check.isChecked());
                break;
            case R.id.image4:
                gluten_check.setChecked(gluten_check.isChecked());
                break;
            case R.id.image5:
                fructose_check.setChecked(fructose_check.isChecked());
                break;
            case R.id.image6:
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



