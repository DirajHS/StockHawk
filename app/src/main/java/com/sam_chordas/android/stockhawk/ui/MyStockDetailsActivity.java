package com.sam_chordas.android.stockhawk.ui;

/**
 * Created by Diraj H S on 10/4/16.
 * Copyright (c) 2016. All rights reserved.
 */

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.db.chart.Tools;
import com.db.chart.model.LineSet;
import com.db.chart.view.AxisController;
import com.db.chart.view.LineChartView;
import com.db.chart.view.animation.Animation;
import com.sam_chordas.android.stockhawk.AppConstants;
import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;


public class MyStockDetailsActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor>{

    private static final int CURSOR_LOADER_ID = 0;
    private Cursor mCursor;
    private LineChartView lineChartView;
    private LineSet mLineSet;
    float max, min;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_graph);
        mLineSet = new LineSet();
        lineChartView = (LineChartView) findViewById(R.id.linechart);
        mLineSet = new LineSet();
        Intent intent = getIntent();
        Bundle args = new Bundle();
        args.putString(AppConstants.SYMBOL, intent.getStringExtra(AppConstants.SYMBOL));
        getLoaderManager().initLoader(CURSOR_LOADER_ID, args, this);
    }

    @Override public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id) {
            case CURSOR_LOADER_ID:
                return new CursorLoader(this, QuoteProvider.Quotes.CONTENT_URI,
                        new String[]{ QuoteColumns.BIDPRICE},
                        QuoteColumns.SYMBOL + " = ?",
                        new String[]{args.getString(AppConstants.SYMBOL)},
                        null);
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursor = data;
        getRange(mCursor);
        displayChart();
    }

    @Override public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void displayChart() {
        mLineSet.setColor(getResources().getColor(R.color.paint))
                .setFill(getResources().getColor(R.color.fill))
                .setDotsColor(getResources().getColor(R.color.dot))
                .setThickness(4)
                .setDashed(new float[]{10f, 10f});


        lineChartView.setBorderSpacing(Tools.fromDpToPx(15))
                .setYLabels(AxisController.LabelPosition.OUTSIDE)
                .setXLabels(AxisController.LabelPosition.NONE)
                .setLabelsColor(getResources().getColor(R.color.labels))
                .setXAxis(false)
                .setYAxis(false)
                .setAxisBorderValues(Math.round(Math.max(0f, min - 5f)), Math.round(max + 5f))
                .addData(mLineSet);

        Animation anim = new Animation();

        if (mLineSet.size() > 1)
            lineChartView.show(anim);
        else
            Toast.makeText(this, "Stock data unavailable", Toast.LENGTH_SHORT).show();
    }

    private void getRange(Cursor data) {
        min= Float.MAX_VALUE;
        max = Float.MIN_VALUE;

        for (data.moveToFirst(); !data.isAfterLast(); data.moveToNext()) {
            String label = data.getString(data.getColumnIndexOrThrow(QuoteColumns.BIDPRICE));
            float price = Float.parseFloat(label);

            mLineSet.addPoint(label, price);
            min = Math.min(min, price);
            max = Math.max(max, price);
        }
    }
}