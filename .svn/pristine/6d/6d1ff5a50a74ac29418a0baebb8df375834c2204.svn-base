package com.oo115.myapplication.bodyMeasures;

import com.github.mikephil.charting.formatter.ValueFormatter;

public class MyXAxisValueFormatter extends ValueFormatter {

    private String[] mValues;

    public MyXAxisValueFormatter(String[] mValues) {
        this.mValues = mValues;
    }


    @Override
    public String getFormattedValue(float value) {
        return mValues[(int) value];
    }
}
