package com.example.administrator.mpcharttest1;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;

/**
 * 　　　　　　　　┏┓　　　┏┓
 * 　　　　　　　┏┛┻━━━┛┻┓
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　━　　　┃
 * 　　　　　　 ████━████     ┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┃　　　┻　　　┃
 * 　　　　　　　┃　　　　　　　┃
 * 　　　　　　　┗━┓　　　┏━┛
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　　　┃
 * 　　　　　　　　　┃　 　 ┗━━━┓
 * 　　　　　　　　　┃ 神兽保佑　　 ┣┓
 * 　　　　　　　　　┃ 代码无BUG   ┏┛
 * 　　　　　　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　　　　　　┃┫┫　┃┫┫
 * 　　　　　　　　　　┗┻┛　┗┻┛
 * Created by dutingjue on 2016/11/1.
 */
public class BarActivity extends Activity {
    BarChart barChart;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar);
        barChart = (BarChart) findViewById(R.id.barMP);
        BarData barData=getBarData();
        showChart(barChart,barData);
    }
    private void showChart(BarChart barChart,BarData barData){
        XAxis xAxis=barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.setData(barData);
        barChart.animateY(2000);
        barChart.setDescription("");
        barChart.setTouchEnabled(false);
    }
    private BarData getBarData() {
        ArrayList<BarEntry> yValues1 = new ArrayList<BarEntry>();
        for (int i = 0; i < 10; i++) {
            float value = (float) (Math.random() * 100);
            yValues1.add(new BarEntry(value, i));
        }
        ArrayList<BarEntry> yValues2 = new ArrayList<BarEntry>();
        for (int i = 0; i < 10; i++) {
            float value = (float) (Math.random() * 100);
            yValues2.add(new BarEntry(value, i));
        }

        ArrayList<String> xValues = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            // x轴显示的数据，这里默认使用数字下标显示
            xValues.add("" + i);
        }
        BarDataSet barDataSet1=new BarDataSet(yValues1,"测试1");
        barDataSet1.setColor(Color.parseColor("#3366ff"));
        BarDataSet barDataSet2=new BarDataSet(yValues2,"测试2");
        barDataSet2.setColor(Color.parseColor("#9bd880"));
        ArrayList<BarDataSet>barDataSets=new ArrayList<BarDataSet>();
        barDataSets.add(barDataSet1);
        barDataSets.add(barDataSet2);
        BarData barData=new BarData(xValues,barDataSets);
        return barData;
    }
}
