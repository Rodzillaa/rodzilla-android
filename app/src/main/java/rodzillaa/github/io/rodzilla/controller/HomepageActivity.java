package rodzillaa.github.io.rodzilla.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import rodzillaa.github.io.rodzilla.R;

public class HomepageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Button recentRatSightingsButton = (Button) findViewById(R.id.listOfRatSightingsButton);
        Button logoutButton = (Button) findViewById(R.id.logoutButton);

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
