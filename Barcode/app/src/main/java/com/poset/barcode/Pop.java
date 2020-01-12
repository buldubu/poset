package com.poset.barcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Pop extends Activity {

    static TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popwindow);

        Intent i = getIntent();
        String son = i.getExtras().getString("son");
        text = findViewById(R.id.textView);
        if(son != null)text.setText(son);
        if(text.getText() == null) text.setText(son);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width*.6),(int) (height*.6));
    }


    @Override
    protected void onResume() {
        super.onResume();
        Intent i = getIntent();
        String son = i.getExtras().getString("son");
        if(text.getText() == null) text.setText(son);
    }
}
