package com.poset.barcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Pop extends Activity {

    TextView text;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popwindow);

        Intent i = getIntent();
        String son = i.getStringExtra("son");
        text = findViewById(R.id.textView);
        text.setText(son);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width*.6),(int) (height*.6));
    }
}
