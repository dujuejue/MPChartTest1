package com.example.administrator.mpcharttest1;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

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
public class LineActivity extends Activity{
    LineChart lineChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line);
        lineChart = (LineChart) findViewById(R.id.lineChart);
        LineData mLineData = getLineData(7, 100);
        showChart(lineChart, mLineData, Color.parseColor("#7274B1"));
    }
    // 设置显示的样式
    private void showChart(LineChart lineChart, LineData lineData, int color) {
        lineChart.setDrawBorders(false);  //是否在折线图上添加边框
        XAxis xAxis=lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.enableGridDashedLine(10,5,5);
        YAxis rightYAxis=lineChart.getAxisRight();
        rightYAxis.setEnabled(false);
        LimitLine yLimitLine=new LimitLine(90,"我是测试线1");
        yLimitLine.setLineColor(Color.RED);
        yLimitLine.setTextColor(Color.RED);
        yLimitLine.enableDashedLine(10,5,5);
        yLimitLine.setTextSize(10);
        LimitLine yLimitLine2=new LimitLine(30,"我是测试线2");
        yLimitLine2.setLineColor(Color.BLUE);
        yLimitLine2.setTextColor(Color.BLUE);
        yLimitLine2.enableDashedLine(10,5,5);
        yLimitLine2.setTextSize(10);
        YAxis leftYAxis=lineChart.getAxisLeft();
        leftYAxis.addLimitLine(yLimitLine2);
        leftYAxis.addLimitLine(yLimitLine);
        leftYAxis.setSpaceTop(20);
        // no description text
        lineChart.setDescription("");// 数据描述
        // 如果没有数据的时候，会显示这个，类似listview的emtpyview
        lineChart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable / disable grid background
        lineChart.setDrawGridBackground(false); // 是否显示表格颜色
        lineChart.setGridBackgroundColor(Color.WHITE & 0x30FFFFFF); // 表格的的颜色，在这里是是给颜色设置一个透明度

        // enable touch gestures
        lineChart.setTouchEnabled(true); // 设置是否可以触摸

        // enable scaling and dragging
        lineChart.setDragEnabled(false);// 是否可以拖拽
        lineChart.setScaleEnabled(true);// 是否可以缩放
        lineChart.setScaleMinima(1f,1f);

        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(false);//点击缩放

        lineChart.setBackgroundColor(color);// 设置背景

        // add data
        lineChart.setData(lineData); // 设置数据

        // get the legend (only possible after setting data)
        Legend mLegend = lineChart.getLegend(); // 设置比例图标示，就是那个一组y的value的

        // modify the legend ...
        // mLegend.setPosition(LegendPosition.LEFT_OF_CHART);
        mLegend.setForm(Legend.LegendForm.CIRCLE);// 样式
        mLegend.setFormSize(6f);// 字体
        mLegend.setTextColor(Color.WHITE);// 颜色
//      mLegend.setTypeface(mTf);// 字体

        lineChart.animateX(2000); // 立即执行的动画,x轴
        lineChart.invalidate();
    }

    /**
     * 生成一个数据
     *
     * @param count 表示图表中有多少个坐标点
     * @param range 用来生成range以内的随机数
     * @return
     */
    private LineData getLineData(int count, float range) {
        ArrayList<String> xValues = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            // x轴显示的数据，这里默认使用数字下标显示
            xValues.add("" + i);
        }
        // y轴的数据
        ArrayList<Entry> yValues = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float value = (float) (Math.random() * range) + 3;
            yValues.add(new Entry(value, i));
        }
        // y轴的数据
        ArrayList<Entry> yValues1 = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            float value = (float) (Math.random() * range) + 3;
            yValues1.add(new Entry(value, i));
        }
        // create a dataset and give it a type
        // y轴的数据集合
        LineDataSet lineDataSet = new LineDataSet(yValues, "测试折线图" /*显示在比例图上*/);
        lineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        LineDataSet lineDataSet1= new LineDataSet(yValues1, "测试折线图1" /*显示在比例图上*/);
        lineDataSet1.setAxisDependency(YAxis.AxisDependency.LEFT);


        //用y轴的集合来设置参数

        lineDataSet.setLineWidth(1.75f); // 线宽
//        lineDataSet.setDrawCircles(false);
//        lineDataSet.setDrawCubic(true);
//        lineDataSet.setCubicIntensity(0.3f);
//        lineDataSet.setDrawFilled(true);
//        lineDataSet.setFillColor(Color.rgb(0,255,255));
        lineDataSet.setCircleSize(3f);// 显示的圆形大小
        lineDataSet.setColor(Color.WHITE);// 显示颜色
        lineDataSet.setCircleColor(Color.WHITE);// 圆形的颜色
        lineDataSet.setHighlightEnabled(false);
        lineDataSet1.setCircleColor(Color.GREEN);
        lineDataSet1.setCircleSize(3f);
        lineDataSet1.setLineWidth(1.75f); // 线宽
        lineDataSet1.setColor(Color.GREEN);// 显示颜色
        lineDataSet1.setHighlightEnabled(false);
        ArrayList<LineDataSet> lineDataSets=new ArrayList<LineDataSet>();
        lineDataSets.add(lineDataSet);
        lineDataSets.add(lineDataSet1);
        LineData lineData=new LineData(xValues,lineDataSets);
        return lineData;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
