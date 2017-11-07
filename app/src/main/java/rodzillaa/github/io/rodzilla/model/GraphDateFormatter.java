package rodzillaa.github.io.rodzilla.model;

import android.util.Log;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GraphDateFormatter implements IAxisValueFormatter {
    private long begin;
    private long end;

    public GraphDateFormatter(long begin, long end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        // "value" represents the position of the label on the axis (x or y)
        long dateInterval = (long) (begin + ((end - begin) * (value / axis.getAxisMaximum())));
        Log.d("GraphDateFormatter", "value: " + new Float(value).toString());
        Log.d("GraphDateFormatter", "max: " + new Float(axis.getAxisMaximum()).toString());
        Log.d("GraphDateFormatter", "date in ms: " + new Float(dateInterval).toString());

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dateInterval);


        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        return (mMonth + 1) + "/" + mDay + "/" + mYear;
    }

}
