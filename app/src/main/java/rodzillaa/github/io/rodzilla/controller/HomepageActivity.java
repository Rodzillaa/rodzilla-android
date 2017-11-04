package rodzillaa.github.io.rodzilla.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import rodzillaa.github.io.rodzilla.R;

/**
 * Activity class that provides the functionality
 * and display for the application's homepage
 * (after the user logs in).
 */
public class HomepageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Button submitARatSightingButton = (Button) findViewById(R.id.submitARatSightingButton);

        Button viewDataGraphsButton =(Button) findViewById(R.id.dataGraphs);
        Button mapViewButton = (Button) findViewById(R.id.mapViewButton);
        Button recentRatSightingsButton = (Button) findViewById(R.id.listOfRatSightingsButton);
        Button logoutButton = (Button) findViewById(R.id.logoutButton);

        mapViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mapViewIntent = new Intent(getBaseContext(), MapsActivity.class);
                startActivity(mapViewIntent);
            }
        });

        submitARatSightingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent submitARatSightingIntent = new Intent(getBaseContext(), SubmitARatSightingActivity.class);
                startActivity(submitARatSightingIntent);
            }
        });

        viewDataGraphsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent dataGraphsIntent = new Intent(getBaseContext(), DataGraphsActivity.class);
                startActivity(dataGraphsIntent);
            }
        });

        recentRatSightingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recentRatSightingsIntent = new Intent(getBaseContext(), RecentRatSightingsActivity.class);
                startActivity(recentRatSightingsIntent);
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent logoutIntent = new Intent(getBaseContext(), WelcomeActivity.class);
                startActivity(logoutIntent);
                Toast.makeText(getApplicationContext(), "Successfully logged out!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
