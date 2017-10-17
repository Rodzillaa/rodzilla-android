package rodzillaa.github.io.rodzilla.controller;

import android.os.Bundle;

import okhttp3.FormBody;
import okhttp3.RequestBody;
import rodzillaa.github.io.rodzilla.R;
import rodzillaa.github.io.rodzilla.model.RatSighting;
import rodzillaa.github.io.rodzilla.model.RatSightingDatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class RecentRatSightingsActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerViewAdapter;
    private RecyclerView.LayoutManager mRecyclerViewLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recentratsightings);
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
