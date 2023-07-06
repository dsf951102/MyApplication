package com.gestordetrabajo.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.color.DynamicColors;

public class Info extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        DynamicColors.applyToActivitiesIfAvailable(this.getApplication());
    }
}