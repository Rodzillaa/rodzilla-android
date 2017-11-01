package rodzillaa.github.io.rodzilla.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import rodzillaa.github.io.rodzilla.R;

/**
 * Activity that displays the information of multiple
 * individual RatSightings in a formatted view.
 */
public class ViewSightingActivity extends AppCompatActivity {

    private static final String TAG = "ViewSightingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewsighting);

        Intent detailView = getIntent();
        ((TextView)findViewById(R.id.key)).setText(detailView.getStringExtra("key"));
        ((TextView)findViewById(R.id.date)).setText(detailView.getStringExtra("date"));
        ((TextView)findViewById(R.id.location_type)).setText(detailView.getStringExtra("location_type"));
        ((TextView)findViewById(R.id.zip)).setText(detailView.getStringExtra("zip"));
        ((TextView)findViewById(R.id.address)).setText(detailView.getStringExtra("address"));
        ((TextView)findViewById(R.id.city)).setText(detailView.getStringExtra("city"));
        ((TextView)findViewById(R.id.borough)).setText(detailView.getStringExtra("borough"));
        ((TextView)findViewById(R.id.latitude)).setText(detailView.getStringExtra("latitude"));
        ((TextView)findViewById(R.id.longitude)).setText(detailView.getStringExtra("longitude"));

    }

}
