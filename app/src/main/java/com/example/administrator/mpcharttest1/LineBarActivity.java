package com.example.administrator.mpcharttest1;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;

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
public class LineBarActivity extends Activity {
    CombinedChart combinedChart;
    String[] dataset;
    ArrayList<BarEntry> barValues;
    ArrayList<Entry> yValues;
    float sum,sum2;
    ArrayList<String> xValues = new ArrayList<String>();
    ArrayList<Float> yLine = new ArrayList<Float>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linebar);
        sum = 0;
        sum2=0;
        dataset = this.getIntent().getStringArrayExtra("data");
        barValues = new ArrayList<BarEntry>();
        for (int i = dataset.length-1; i >0; i--) {
            String[] data1 = dataset[i].split(";");
            xValues.add(data1[1].substring(data1[1].indexOf("=") + 1, data1[1].indexOf("-")));
            String countStr = data1[0].substring(data1[0].indexOf("=") + 1);
            int num = Integer.parseInt(countStr);
            barValues.add(new BarEntry(num, dataset.length-i-1));
            yLine.add((float) num);
            sum += num;
        }
        yValues=new ArrayList<Entry>();
        for (int i = 0; i < yLine.size(); i++) {
            sum2+=yLine.get(i);
            Entry lineY = new Entry((sum2 / sum*100), i);
            yValues.add(lineY);
        }
        combinedChart = (CombinedChart) findViewById(R.id.linebar);
        showChart(combinedChart);
    }

    private void showChart(CombinedChart combinedChart) {

        CombinedData combinedData = new CombinedData(xValues);
        combinedData.setData(getBarData(barValues));
        combinedData.setData(getLineData());
        combinedChart.setData(combinedData);
        combinedChart.setDescription("");
        combinedChart.setBorderWidth(2f);
        combinedChart.setBorderColor(Color.RED);
        combinedChart.animateXY(1500, 1500);
        combinedChart.getViewPortHandler().setMaximumScaleX(2);
        combinedChart.getViewPortHandler().setMaximumScaleY(2);
        XAxis xAxis = combinedChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        YAxis yAxisLeft = combinedChart.getAxisLeft();
        yAxisLeft.setDrawGridLines(false);
        YAxis yAxisRight=combinedChart.getAxisRight();
        yAxisRight.setValueFormatter(new PercentFormatter());
        combinedChart.invalidate();
    }

    private LineData getLineData() {

        LineDataSet lineDataSet = new LineDataSet(yValues, "Fail事件百分比" /*显示在比例图上*/);
        lineDataSet.setAxisDependency(YAxis.AxisDependency.RIGHT);
        lineDataSet.setLineWidth(1.75f); // 线宽
        lineDataSet.setCircleSize(3f);// 显示的圆形大小
        lineDataSet.setColor(Color.parseColor("#cc3333"));// 显示颜色
        lineDataSet.setCircleColor(Color.parseColor("#cc3333"));// 圆形的颜色
        lineDataSet.setFillColor(Color.parseColor("#cc3333"));
        lineDataSet.setHighlightEnabled(false);
        lineDataSet.setValueFormatter(new PercentFormatter());
        LineData lineData = new LineData();
        lineData.addDataSet(lineDataSet);
        return lineData;
    }

    private BarData getBarData(ArrayList<BarEntry> barValues) {
        BarDataSet barDataSet = new BarDataSet(barValues, "Fail事件");
        barDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        barDataSet.setColor(Color.parseColor("#3366ff"));
        barDataSet.setHighlightEnabled(false);
        BarData barData = new BarData();
        barData.addDataSet(barDataSet);
        return barData;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
