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
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.db.chart.Tools;
import com.db.chart.model.LineSet;
import com.db.chart.view.AxisController;
import com.db.chart.view.ChartView;
import com.db.chart.view.LineChartView;
import com.sam_chordas.android.stockhawk.AppConstants;
import com.sam_chordas.android.stockhawk.R;
import com.sam_chordas.android.stockhawk.data.QuoteColumns;
import com.sam_chordas.android.stockhawk.data.QuoteProvider;

import java.util.ArrayList;
import java.util.Collections;

public class MyStockDetailsActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor>{

    private static final int CURSOR_LOADER_ID = 0;
    private Cursor mCursor;
    private LineChartView lineChartView;
    private LineSet mLineSet;
    int maxRange,minRange;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_graph);
        mLineSet = new LineSet();
        lineChartView = (LineChartView) findViewById(R.id.linechart);
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
        displayChart(mCursor);
    }

    @Override public void onLoaderReset(Loader<Cursor> loader) {

    }

    private void displayChart(Cursor mCursor) {
        Paint gridPaint = new Paint();
        gridPaint.setColor(getResources().getColor(R.color.paint));
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setAntiAlias(true);
        gridPaint.setStrokeWidth(Tools.fromDpToPx(1f));
        lineChartView.setBorderSpacing(Tools.fromDpToPx(10))
                .setAxisBorderValues(minRange-100, maxRange+100, 50)
                .setXLabels(AxisController.LabelPosition.OUTSIDE)
                .setYLabels(AxisController.LabelPosition.OUTSIDE)
                .setLabelsColor(getResources().getColor(R.color.labels))
                .setXAxis(false)
                .setYAxis(false)
                .setBorderSpacing(Tools.fromDpToPx(5))
                .setGrid(ChartView.GridType.HORIZONTAL, gridPaint);

        ArrayList<Float> mArrayList = new ArrayList<>();
        for(mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
            mArrayList.add(Float.parseFloat(mCursor.getString(mCursor.getColumnIndex(QuoteColumns.BIDPRICE))));
        }
        maxRange = Math.round(Collections.max(mArrayList));
        minRange = Math.round(Collections.min(mArrayList));
        if(minRange>100)
            minRange = minRange-100;

        mCursor.moveToFirst();
        for (int i = 0; i < mCursor.getCount(); i++){
            String label = mCursor.getString(mCursor.getColumnIndexOrThrow(QuoteColumns.BIDPRICE));
            float price = Float.parseFloat(mCursor.getString(mCursor.getColumnIndex(QuoteColumns.BIDPRICE)));
            mLineSet.addPoint(label, price);
            mCursor.moveToNext();
        }
        mLineSet.setColor(getResources().getColor(R.color.set))
                .setDotsStrokeThickness(Tools.fromDpToPx(2))
                .setDotsStrokeColor(getResources().getColor(R.color.stroke))
                .setDotsColor(getResources().getColor(R.color.dots));
        lineChartView.addData(mLineSet);
        lineChartView.show();
    }
}