package rodzillaa.github.io.rodzilla.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import rodzillaa.github.io.rodzilla.R;
import rodzillaa.github.io.rodzilla.model.Borough;
import rodzillaa.github.io.rodzilla.model.LocationType;
import rodzillaa.github.io.rodzilla.model.RatSighting;
import rodzillaa.github.io.rodzilla.model.RatSightingDatabase;
import rodzillaa.github.io.rodzilla.utils.APIUtil;

/**
 * Activity class that provides the functionality for
 * the Submit-A-Rat-Sighting page in the mobile app.
 * This view allows users to enter information about a
 * rat sighting.
 */
public class SubmitARatSightingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submitaratsighting);

        final Spinner locationTypeSpinner = (Spinner) findViewById(R.id.locationTypeSpinner);
        final Spinner boroughSpinner = (Spinner) findViewById(R.id.boroughSpinner);

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

        Button submitSightingButton = (Button) findViewById(R.id.submitSightingButton);
        Button cancelSightingButton = (Button) findViewById(R.id.cancelSightingButton);

        cancelSightingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Cancelled",
                        Toast.LENGTH_SHORT).show();
                Intent homeIntent = new Intent(getBaseContext(), HomepageActivity.class);
                startActivity(homeIntent);
            }

        });


        submitSightingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText zipcodeEditText = (EditText) findViewById(R.id.zipcodeEditText);
                EditText streetEditText = (EditText) findViewById(R.id.streetEditText);
                EditText cityEditText = (EditText) findViewById(R.id.cityEditText);
                String key = UUID.randomUUID().toString();
                String date = "10/31/2017 12:00:00 AM";
                RequestBody formBody = new FormBody.Builder()
                        .add("key", key)
                        .add("date", date)
                        .add("location_type", locationTypeSpinner.getSelectedItem().toString())
                        .add("zip", zipcodeEditText.getText().toString())
                        .add("address", streetEditText.getText().toString())
                        .add("city", cityEditText.getText().toString())
                        .add("borough", boroughSpinner.getSelectedItem().toString())
                        .add("latitude", "-34")
                        .add("longitude", "151")
                        .build();
                RatSighting temp = new RatSighting();
                temp.address = streetEditText.getText().toString();
                temp.borough = boroughSpinner.getSelectedItem().toString();
                temp.city = cityEditText.getText().toString();
                temp.date = date;
                temp.key = key;
                temp.latitude = "-34";
                temp.location_type = locationTypeSpinner.getSelectedItem().toString();
                temp.longitude = "151";
                temp.zip = zipcodeEditText.getText().toString();
                RatSightingDatabase.addSighting(temp);
                try {
                    post(APIUtil.SERVER_URL+"/checkUser", formBody);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

    }

    /**
     * A method that posts the rat report information to
     * the database on the server-side.
     *
     * @param url
     * @param requestBody
     * @throws IOException
     */
    protected void post(String url, RequestBody requestBody) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = requestBody;
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    String resp = responseBody.string();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Rat Reported!",
                                    Toast.LENGTH_SHORT).show();
                            Intent homeIntent = new Intent(getBaseContext(), HomepageActivity.class);
                            startActivity(homeIntent);
                        }
                    });

                }
            }
        });
    }
}
