package com.gestordetrabajo.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.color.DynamicColors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FinaceActivity extends AppCompatActivity {

    private final List<String> xValues = Arrays.asList("Salario","Gastos");
    List<RegistroGastos>registros;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.finance_activity);
        DynamicColors.applyToActivitiesIfAvailable(this.getApplication());

        List<RegistroGastos> list = new ArrayList<>();
        list.add(new RegistroGastos("Julio", "10000", "9500"));
        list.add(new RegistroGastos("Julio", "10000", "9500"));
        list.add(new RegistroGastos("Julio", "10000", "9500"));


        //Inicializacion de graficos
        initializeRecyclerView(this,list);


    }

    public void initializeRecyclerView(Context context, List<RegistroGastos>items) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_graphic);
        GraphicsAdapter  adapter = new GraphicsAdapter(context, items);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(adapter);

    }

}



