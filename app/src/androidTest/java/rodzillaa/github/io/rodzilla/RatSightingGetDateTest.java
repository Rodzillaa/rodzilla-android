package rodzillaa.github.io.rodzilla;

import android.content.Context;
import android.net.ParseException;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import rodzillaa.github.io.rodzilla.model.RatSighting;

import static org.junit.Assert.*;

/**
 * JUnits test for the getDate() method located in the
 * RatSighting class.
 */

@RunWith(AndroidJUnit4.class)
public class RatSightingGetDateTest {

    @Test
    public void getDateTest() {
        try {
            RatSighting r = new RatSighting();
            r.date = "10/09/2017 01:32:33";
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            Date date;
            date = format.parse("10/09/2017");
            assertEquals(date, r.getDate());
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void getDateThrowsExceptionTest() {
        //invalid date returns null
        RatSighting r = new RatSighting();
        r.date = "fjdklsnfke jdgjri 5843 53 ";
        assertEquals(null, r.getDate());
    }
}
