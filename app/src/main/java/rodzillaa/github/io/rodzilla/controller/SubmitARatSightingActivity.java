package rodzillaa.github.io.rodzilla.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.RequestBody;
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

        final Spinner locationTypeSpinner = (Spinner) findViewById(R.id.locationTypeSpinner);
        final Spinner boroughSpinner = (Spinner) findViewById(R.id.boroughSpinner);
        Button submitButton = (Button) findViewById(R.id.submitSightingButton);
        Button cancelButton = (Button) findViewById(R.id.cancelSightingButton);

        /*
          Set up the adapter to display the allowable majors in the spinner
         */
        final ArrayAdapter<String> locationTypeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, LocationType.values());
        locationTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationTypeSpinner.setAdapter(locationTypeAdapter);

        /*
          Set up the adapter to display the allowable class standing options in the spinner
         */
        ArrayAdapter<String> boroughAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Borough.values());
        boroughAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        boroughSpinner.setAdapter(boroughAdapter);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Don't forget to include:
                // (Unique key should be auto-generated)
                // Date
                // Longitude
                // Latitude

                //Implement the post function

                EditText streetName = (EditText) findViewById(R.id.streetEditText);
                EditText city = (EditText) findViewById(R.id.cityEditText);
                EditText zipcode = (EditText) findViewById(R.id.zipcodeEditText);

                RequestBody formBody = new FormBody.Builder()
                        .add("Location Type", locationTypeSpinner.getSelectedItem().toString())
                        .add("Street", streetName.getText().toString())
                        .add("City", city.getText().toString())
                        .add("Zip Code", zipcode.getText().toString())
                        .add("Borough", boroughSpinner.getSelectedItem().toString())
                        .build();
                try {
                    post("http://143.215.91.97:9000/addReport", formBody);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancelIntent = new Intent(getBaseContext(), HomepageActivity.class);
                startActivity(cancelIntent);
            }
        });

    }
    /**
     * Send post request to web api to insert rat report into system
     * @param url
     * @param requestBody
     * @throws IOException
     */
    protected void post(String url, RequestBody requestBody) throws IOException {


    }
}
