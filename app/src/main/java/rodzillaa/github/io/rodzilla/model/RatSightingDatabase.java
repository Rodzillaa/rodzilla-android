package rodzillaa.github.io.rodzilla.model;

import android.content.Context;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Rat Sighting Database where all instances of
 * rat sightings are stored in an array. The array
 * stores RatSighting objects.
 *
 * This is a singleton (a class that only allows one
 * instance of itself to be created).
 */
public class RatSightingDatabase {

    private static final String TAG = "RatSightingDatabase";
    private static List<RatSighting> mRatSightings = new LinkedList<>();

    /**
     * Method that adds a RatSighting object to the
     * RatSightingDatabase. Contains a date and a
     * timestamp.
     *
     * @param r RatSighting object to be added to the
     *          database.
     */
    public static void addSighting(RatSighting r) {
        String myStrDate = r.date.substring(0, r.date.length()-2).trim();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        try {
            Date date = format.parse(myStrDate);
            r.timestamp = date.getTime();
            mRatSightings.add(0, r);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that returns a list of RatSighting objects that
     * were previously added to the RatSighting database.
     *
     * @return a LinkedList of RatSighting objects that have
     * been submitted to the database.
     */
    public static List<RatSighting> getRatSightings() {
        return mRatSightings;
    }
}
