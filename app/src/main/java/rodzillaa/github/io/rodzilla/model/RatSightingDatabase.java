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

    private static ArrayList<RatSighting> mRatSightings = new ArrayList<>();

    public static void addSighting(RatSighting r) {
        mRatSightings.add(r);
    }

    public static ArrayList<RatSighting> getRatSightings() {
        return mRatSightings;
    }
}
