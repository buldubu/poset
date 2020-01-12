package com.poset.barcode;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static androidx.lifecycle.Lifecycle.State.RESUMED;

public class ScannedBarcodeActivity extends AppCompatActivity {

    SurfaceView surfaceView;
    TextView txtBarcodeValue;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private static final int REQUEST_CAMERA_PERMISSION = 201;
    String intentData = "";
    Date date1;
    Date date2;
    String ans = "";
    Map<String, String> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_barcode);
        initViews();
        date1 = new Date();
        date2 = new Date();
    }

    private void downloadJSON(final String urlWebService) {
        class DownloadJSON extends AsyncTask<Void, Void, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if(s != null && !s.equals("rro")) {
                    String[] datas = s.split("\",\"");
                    for (int i = 0; i < datas.length; i++) {
                        String temp = datas[i];
                        String[] keyValue = temp.split(":");
                        if(i == 0) map.put(keyValue[0], keyValue[1]+"\"");
                        else if(i == datas.length-1) map.put("\""+keyValue[0], keyValue[1]);
                        else map.put("\""+keyValue[0], keyValue[1]+"\"");
                    }
                    int i = 0;
                    String son = "";
                    String comp = "\"1\"";
                    try {
                        FileInputStream fIn = openFileInput ( "settings.dat" ) ;
                        InputStreamReader isr = new InputStreamReader ( fIn ) ;
                        BufferedReader buffreader = new BufferedReader ( isr ) ;
                        String readString = buffreader.readLine ( ) ;
                        while ( readString != null ) {
                            if(i == 0 && map.get("\"vegan\"").equals(comp) && Boolean.parseBoolean(readString)){
                                son = son + "Non Vegan!\n";
                            }
                            else if(i == 1 && map.get("\"vejeteryan\"").equals(comp) && Boolean.parseBoolean(readString)){
                                son = son + "Non Vegeterian!\n";
                            }
                            else if(i == 2 && map.get("\"non_pork\"").equals(comp) && Boolean.parseBoolean(readString)){
                                son = son + "Pork!\n";
                            }
                            else if(i == 3 && map.get("\"gluten\"").equals(comp) && Boolean.parseBoolean(readString)){
                                son = son + "Gluten includes!\n";
                            }
                            else if(i == 4 && map.get("\"fruktoz\"").equals(comp) && Boolean.parseBoolean(readString)){
                                son = son + "Fructose includes!\n";
                            }
                            else if(i == 5 && map.get("\"yer_fistigi\"").equals(comp) && Boolean.parseBoolean(readString)) {
                                son = son + "Peanut includes!\n";
                            }
                            i++;
                            readString = buffreader.readLine ( ) ;
                        }
                        if(son == "") {
                            Pop.arkaPlan.setBackgroundColor(Color.GREEN);
                            son = "You can eat it safely";
                        }else
                            Pop.arkaPlan.setBackgroundColor(Color.WHITE);
                        Pop.text.setText(son);
                        isr.close ( ) ;
                    } catch ( IOException ioe ) {
                        ioe.printStackTrace ( ) ;
                    }
                }
            }
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().substring(1, sb.length() - 2).trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        DownloadJSON getJSON = new DownloadJSON();
        getJSON.execute();
    }
    private void initViews() {
        txtBarcodeValue = findViewById(R.id.txtBarcodeValue);
        surfaceView = findViewById(R.id.surfaceView);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.floatingActionButton:
                finish();
                break;
        }
    }
    private void initialiseDetectorsAndSources() {
        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.EAN_13)
                .build();
        cameraSource = new CameraSource.Builder(this, barcodeDetector)
                .setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true)
                .build();
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                try {
                    if (ActivityCompat.checkSelfPermission(ScannedBarcodeActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        cameraSource.start(surfaceView.getHolder());
                    } else {
                        ActivityCompat.requestPermissions(ScannedBarcodeActivity.this, new
                                String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });
        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
            }
            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barcodes = detections.getDetectedItems();
                date2 = new Date();
                if (barcodes.size() != 0 && ((int) (date2.getTime() - date1.getTime()))/100 > 10) {
                    date1 = new Date();
                    intentData = barcodes.valueAt(0).displayValue;
                    downloadJSON("http://172.24.5.51:8080/deneme/index.php?barkod=" + intentData);
                    if(getLifecycle().getCurrentState().isAtLeast(RESUMED)) {
                        Intent i = new Intent(ScannedBarcodeActivity.this, Pop.class);
                        i.putExtra("son", ans);
                        startActivity(i);
                    }
                }
            }
        });
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        initialiseDetectorsAndSources();
    }
}
