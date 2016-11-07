package com.example.administrator.mpcharttest1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button buttonLine, buttonBar, buttonData;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Intent intent = (Intent) msg.obj;
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonLine = (Button) findViewById(R.id.lineChart);
        buttonLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLine = new Intent(MainActivity.this, LineActivity.class);
                startActivity(intentLine);
            }
        });
        buttonBar = (Button) findViewById(R.id.barChart);
        buttonBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBar = new Intent(MainActivity.this, BarActivity.class);
                startActivity(intentBar);
            }
        });
        buttonData = (Button) findViewById(R.id.dataBase);
        buttonData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebThreadPOLATu web = new WebThreadPOLATu();
                web.start();
            }
        });
    }

    class WebThreadPOLATu extends Thread {
        @Override
        public void run() {
            String[] dataset = WebUtils.getPLATOData2();
            Intent intentBarLine = new Intent(MainActivity.this, LineBarActivity.class);
            intentBarLine.putExtra("data", dataset);
            Message message = mHandler.obtainMessage();
            message.obj = intentBarLine;
            mHandler.sendMessage(message);
        }
    }

}
