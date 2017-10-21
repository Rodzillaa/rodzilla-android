package rodzillaa.github.io.rodzilla.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import rodzillaa.github.io.rodzilla.R;
import rodzillaa.github.io.rodzilla.model.Borough;
import rodzillaa.github.io.rodzilla.model.LocationType;

public class SubmitARatSightingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitaratsighting);
//Info required
//        unique key (auto-assigned by system)
//        location (latitude and longitude)
//        date and time
//        location type (select from: 1-2 Family Dwelling,
//          3+ Family Apt. Building, 3+ Family Mixed Use Building,
//          Commercial Building, Vacant Lot, Construction Site,
//          Hospital, Catch Basin/Sewer)
//        Incident zip code
//        Incident address
//        City
//        Borough ( select from: MANHATTAN, STATEN ISLAND, QUEENS, BROOKLYN, BRONX)
//

        Spinner locationTypeSpinner = (Spinner) findViewById(R.id.locationTypeSpinner);
        Spinner boroughSpinner = (Spinner) findViewById(R.id.boroughSpinner);

        /*
          Set up the adapter to display the allowable majors in the spinner
         */
        ArrayAdapter<String> locationTypeAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, LocationType.values());
        locationTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationTypeSpinner.setAdapter(locationTypeAdapter);

        /*
          Set up the adapter to display the allowable class standing options in the spinner
         */
        ArrayAdapter<String> boroughAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, Borough.values());
        boroughAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boroughSpinner.setAdapter(boroughAdapter);

    }
}
