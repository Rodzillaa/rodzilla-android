package rodzillaa.github.io.rodzilla.model;

import android.content.Context;

import java.util.ArrayList;

/**
 * Rat Sighting Database where all instances of
 * rat sightings are stored in an array. The array
 * stores RatSighting objects.
 *
 * This is a singleton (a class that only allows one
 * instance of itself to be created).
 */

public class RatSightingDatabase {
    private static RatSightingDatabase sRatSightingDatabase;
    private Context mAppContext;

    private ArrayList<RatSighting> mRatSightings;

    private RatSightingDatabase(Context appContext) {
        mAppContext = mAppContext;
        mRatSightings = new ArrayList<RatSighting>();
    }

    public static RatSightingDatabase get(Context c) {
        if (sRatSightingDatabase == null) {
            sRatSightingDatabase = new RatSightingDatabase(c.getApplicationContext());
        }
        return sRatSightingDatabase;
    }

    public ArrayList<RatSighting> getRatSightings() {
        return mRatSightings;
    }
}
