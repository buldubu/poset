package com.poset.barcode;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class Pop extends Activity {

    static TextView text;
    static RelativeLayout arkaPlan;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popwindow);
        arkaPlan = findViewById(R.id.arkaPlan);
        Intent i = getIntent();
        String son = i.getExtras().getString("son");
        text = findViewById(R.id.textView);
        text.setText(Html.fromHtml(son));

        if(text.getText() == null)
            text.setText(Html.fromHtml(son));
        if(son == null) {
            arkaPlan.setBackgroundColor(Color.GREEN);
        }else
            arkaPlan.setBackgroundColor(Color.WHITE);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width*.64),(int) (height*.22));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent i = getIntent();
        String son = i.getExtras().getString("son");
        if(text.getText() == null)
            text.setText(Html.fromHtml(son));
        if(son == null) {
            arkaPlan.setBackgroundColor(Color.GREEN);
        }else
            arkaPlan.setBackgroundColor(Color.WHITE);
    }
}
