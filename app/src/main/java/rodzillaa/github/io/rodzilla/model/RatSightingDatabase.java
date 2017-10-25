package rodzillaa.github.io.rodzilla.model;

import android.content.Context;

import java.util.ArrayList;
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

    private static List<RatSighting> mRatSightings = new LinkedList<>();

    public static void addSighting(RatSighting r) {
        mRatSightings.add(0, r);
    }

    public static List<RatSighting> getRatSightings() {
        return mRatSightings;
    }
}
