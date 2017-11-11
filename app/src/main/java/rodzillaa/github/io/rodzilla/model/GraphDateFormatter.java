package rodzillaa.github.io.rodzilla.model;

import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.Calendar;

public class GraphDateFormatter implements IAxisValueFormatter {
    private long begin;
    private long end;
    private long size;

    public GraphDateFormatter(long begin, long end, int size) {
        this.begin = begin;
        this.end = end;
        this.size = size;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        float axisMax = axis.getAxisMaximum();
        double total = value * size;
        // "value" represents the position of the label on the axis (x or y)
        long dateInterval = (long) (begin + (((end - begin) * (value/axisMax))));
        //Log.d("GraphDateFormatter", "getlabelcount: " + axis.getLabelCount());
        Log.d("GraphDateFormatter", "value: " + Float.toString(value));
        Log.d("GraphDateFormatter", "max: " + Float.toString(axis.getAxisMaximum()));
        Log.d("GraphDateFormatter", "date in ms: " + Float.toString(dateInterval));

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dateInterval);

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        return (mMonth + 1) + "/" + mDay + "/" + mYear;
    }

}
