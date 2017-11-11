package rodzillaa.github.io.rodzilla.controller;

import android.os.Bundle;

import rodzillaa.github.io.rodzilla.R;
import rodzillaa.github.io.rodzilla.model.RatSightingDatabase;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Activity to display recent rat sightings.
 */
public class RecentRatSightingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recentratsightings);
        RecyclerView mRecyclerView;
        RecyclerView.Adapter mRecyclerViewAdapter;
        RecyclerView.LayoutManager mRecyclerViewLayoutManager;
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mRecyclerViewLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mRecyclerViewLayoutManager);
        mRecyclerViewAdapter = new RecyclerViewAdapter(RatSightingDatabase.getRatSightings(),
                mRecyclerView, getBaseContext());
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

    }
}
