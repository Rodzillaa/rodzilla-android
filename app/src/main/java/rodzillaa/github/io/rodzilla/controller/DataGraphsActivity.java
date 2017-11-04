package rodzillaa.github.io.rodzilla.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import java.util.ArrayList;
import java.util.List;

import rodzillaa.github.io.rodzilla.R;
import rodzillaa.github.io.rodzilla.model.RatSighting;

public class DataGraphsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datagraphs);

        BarChart ratSightingsBarChart = (BarChart) findViewById(R.id.ratSightingsBarChart);

        //Need to store data in an array
        RatSighting[] dataArray;

        //iterate over each rat sighting and store it in the below ArrayList object
        List<BarEntry> entries = new ArrayList<>();
        int count = 0;
        //for(RatSighting sighting : dataArray) {

            // 1st parameter in BarEntry object is 0f, 1f, 2f, ...
            // 2nd parameter in BarEntry object is the data (i.e. rat sighting count)
            //entries.add(new BarEntry(count, sighting.getYValue()));
            //count++;
        //}

        BarDataSet barDataSet = new BarDataSet(entries, "Rat Sightings");
        barDataSet.setColor(R.color.lightblue);

        BarData barData = new BarData(barDataSet);

        ratSightingsBarChart.setFitBars(true); // make the x-axis fit exactly all bars
        ratSightingsBarChart.setData(barData);
        ratSightingsBarChart.invalidate(); // refresh

    }
}
