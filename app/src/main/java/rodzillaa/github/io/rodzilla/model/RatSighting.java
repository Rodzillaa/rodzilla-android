package rodzillaa.github.io.rodzilla.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * RatSighting class that defines the information fields
 * that a RatSighting object should contain. Information
 * fields are address, borough, city, date, key,
 * latitude, location_type, longitude, zip and timestamp
 */
public class RatSighting {
    public String address, borough, city, date, key, latitude, location_type, longitude, zip;
    public long timestamp;

    /**
     * Method that returns the date (and time) of the rat sighting
     * in String form.
     *
     * @return c.
     */
    public String getDateString() {
        return date;
    }

    /**
     * Method that returns the date (and time) of the rat sighting
     * as a Date object.
     *
     * @return Method that returns the date (and time) of the rat
     * sighting in Date form (format: MM/dd/yyyy HH:mm:ss).
     */
    public Date getDate() {
        String myStrDate = date.substring(0, date.length()-2).trim();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        try {
            Date dateObject = format.parse(myStrDate);
            return dateObject;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
