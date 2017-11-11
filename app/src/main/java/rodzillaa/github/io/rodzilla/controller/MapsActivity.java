package rodzillaa.github.io.rodzilla.controller;

import android.app.DatePickerDialog;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;
import java.util.List;

import rodzillaa.github.io.rodzilla.R;
import rodzillaa.github.io.rodzilla.model.RatSighting;
import rodzillaa.github.io.rodzilla.model.RatSightingDatabase;

/**
 * Activity class that allows the user of the app
 * to view a Google Map that presents a variety of
 * informational displays of rat sightings.
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = "MapsActivity";
    private GoogleMap mMap;
    private long startdate;
    private long enddate;
    private Calendar myCalendar = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener pickStart = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            startdate = myCalendar.getTimeInMillis();
            filterByDate();
        }
    };

    private DatePickerDialog.OnDateSetListener pickEnd = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            enddate = myCalendar.getTimeInMillis();
            filterByDate();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        Button startPickerButton = (Button) findViewById(R.id.startPickerButton);
        Button endPickerButton = (Button) findViewById(R.id.endPickerButton);
        startPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MapsActivity.this, pickStart, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        endPickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MapsActivity.this, pickEnd, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     *
     * @param googleMap GoogleMap object to display.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        addMarkers();
    }

    /**
     * Method that adds markers that represent rat sightings
     * to the map.
     */
    private void addMarkers() {
        List<RatSighting> list = RatSightingDatabase.getRatSightings();
        LatLng temp = new LatLng(-34, 151);
        int counter = 0;
        for (RatSighting r: list) {
            if (counter > 500) break;
            if (r.latitude.equals("") || r.longitude.equals("")) continue;
            temp = new LatLng(Double.parseDouble(r.latitude), Double.parseDouble(r.longitude));
            mMap.addMarker(new MarkerOptions().position(temp).title(r.key));
            counter++;
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(temp));
    }

    /**
     * Method that allows the user to filter rat sighting
     * information on the map by date.
     */
    private void filterByDate() {
        mMap.clear();
        List<RatSighting> list = RatSightingDatabase.getRatSightings();
        LatLng temp = new LatLng(-34, 151);
        int counter = 0;
        for (RatSighting r: list) {
            if (counter > 500) break;
            if (r.latitude.equals("") || r.longitude.equals("")) continue;
            if (r.timestamp < startdate || r.timestamp > enddate) continue;
            temp = new LatLng(Double.parseDouble(r.latitude), Double.parseDouble(r.longitude));
            mMap.addMarker(new MarkerOptions().position(temp).title(r.key));
            counter++;
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(temp));
    }
}
