package com.gestordetrabajo.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.textview.MaterialTextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class List_Graphics_VH extends RecyclerView.ViewHolder {

    Context context;
    GraphicsAdapter adapter;


    public List_Graphics_VH(@NonNull Context context, View itemView) {
        super(itemView);
        this.context = context;

        initGraph();



    }

    public List_Graphics_VH linkAdapter(GraphicsAdapter adapter) {
        this.adapter = adapter;
        return this;

    }

    public void initGraph(){
        List<String> xValues = Arrays.asList("Salario","Gastos");
        BarChart barchar = itemView.findViewById(R.id.chart);
        TextView textView = itemView.findViewById(R.id.month_indicator);
        barchar.getAxisRight().setDrawLabels(false);

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        entries.add(new BarEntry(0,45));
        entries.add(new BarEntry(1,80));


        YAxis yAxis = barchar.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        //yAxis.setAxisMaximum(100f);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(3);



        BarDataSet dataSet = new BarDataSet(entries,"Leyenda");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);


        BarData barData = new BarData(dataSet);
        barchar.setData(barData);

        barchar.getDescription().setEnabled(false);
        barchar.invalidate();

        barchar.getXAxis().setValueFormatter(new IndexAxisValueFormatter(xValues));
        barchar.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barchar.getXAxis().setGranularity(1f);
        barchar.getXAxis().setGranularityEnabled(true);
        barchar.getXAxis().setAxisLineWidth(2f);
        barchar.getXAxis().setAxisLineColor(Color.BLACK);
    }


}
