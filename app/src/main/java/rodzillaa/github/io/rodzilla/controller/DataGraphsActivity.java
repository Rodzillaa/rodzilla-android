package rodzillaa.github.io.rodzilla.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import rodzillaa.github.io.rodzilla.R;
import rodzillaa.github.io.rodzilla.model.RatSighting;
import rodzillaa.github.io.rodzilla.model.RatSightingDatabase;

public class DataGraphsActivity extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener {

    private Date begin;
    private Date end;

    @Override
    public void onDateSet(DatePickerDialog view,
                          int year, int monthOfYear,
                          int dayOfMonth,int yearEnd,
                          int monthOfYearEnd,
                          int dayOfMonthEnd) {
        String date = "You picked the following date: From- "
            + (++monthOfYear) + dayOfMonth + "/" + "/" + year + " To "
                + (++monthOfYearEnd) + "/" + dayOfMonthEnd + "/" + yearEnd;
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            begin = format.parse(year + "/" + monthOfYear + "/" + dayOfMonth);
            end = format.parse(yearEnd + "/" + monthOfYear + "/" + dayOfMonthEnd);

            Log.d("Graphs", "date=" + begin);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(), date, Toast.LENGTH_LONG).show();

        BarChart ratSightingsBarChart = (BarChart) findViewById(R.id.ratSightingsBarChart);
        Description description = new Description();
        description.setText("");
        ratSightingsBarChart.setDescription(description);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(begin);

        TextView graphTitle = (TextView) findViewById(R.id.ratSightingBarGraphTitle);
        String titleText = "Number of Rat Sightings from " + calendar.get(Calendar.MONTH)
                + "/" + calendar.get(Calendar.DAY_OF_MONTH) + "/"
                + calendar.get(Calendar.YEAR) + " to ";
        calendar.setTime(end);
        titleText += calendar.get(Calendar.MONTH) + "/"
                + calendar.get(Calendar.DAY_OF_MONTH) + "/"
                + calendar.get(Calendar.YEAR);
        graphTitle.setText(titleText);

        //Need to store data in a list (LinkedList)
        //using date range.


        if (begin != null) {
            calendar.setTime(begin);
        } else {
            calendar.setTime(calendar.getTime());
        }
        Log.d("DATA GRAPHS ACTIVITY", "date=" + begin);
        int beginYear = calendar.get(Calendar.YEAR);
        int beginMonth = calendar.get(Calendar.MONTH);
        int beginDay = calendar.get(Calendar.DAY_OF_MONTH);

        List<RatSighting> ratList = RatSightingDatabase.getRatSightings();

        HashMap<String, Integer> ratCount = new HashMap<>();
        //Enumerating the rat sightings for each day in time range.
        for (RatSighting sighting : ratList) {
            calendar.setTime(sighting.getDate());
            int rYear = calendar.get(Calendar.YEAR);
            int rMonth = calendar.get(Calendar.MONTH);
            int rDay = calendar.get(Calendar.DAY_OF_MONTH);
//            if (sighting.getDate().compareTo(begin) >= 0
//                    && sighting.getDate().compareTo(end) <= 0) {
                if (ratCount.containsKey("" + rYear + rMonth + rDay)) {
                    ratCount.put("" + rYear + rMonth + rDay,
                            ratCount.get("" + rYear + rMonth + rDay) + 1);
                } else {
                    ratCount.put("" + rYear + rMonth + rDay, 1);
                }
//            }
        }

          //iterate over each rat sighting and store it in the below ArrayList object
        float count = 0;
        List<BarEntry> entries = new ArrayList<>();
        for (String key: ratCount.keySet()) {
            // 1st parameter in BarEntry object is 0f, 1f, 2f, ...
            // 2nd parameter in BarEntry object is the data (i.e. rat sighting count)
            //entries.add(new BarEntry(count, sighting.getYValue()));
            entries.add(new BarEntry(count, ratCount.get(key)));
            // ratCount.get(key))
            count++;

        }

        BarDataSet dataSet = new BarDataSet(entries, "Rat Sightings");
        //dataSet.setColor(R.color.lightblue);

        BarData barData = new BarData(dataSet);

        XAxis xAxis = ratSightingsBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //xAxis.setValueFormatter(new MyCustomFormatter());

        ratSightingsBarChart.setData(barData);
        ratSightingsBarChart.invalidate(); // refresh

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datagraphs);

        Calendar now = Calendar.getInstance();
        DatePickerDialog dpd =
                com.borax12.materialdaterangepicker.date.DatePickerDialog.newInstance(
                        DataGraphsActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
        dpd.show(getFragmentManager(), "Datepickerdialog");
    }
}
