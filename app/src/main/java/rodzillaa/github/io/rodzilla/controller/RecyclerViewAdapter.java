package rodzillaa.github.io.rodzilla.controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rodzillaa.github.io.rodzilla.R;
import rodzillaa.github.io.rodzilla.model.RatSighting;

/**
 * Customized ReclyclerViewAdapter to format RatSightings
 * in list view.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<RatSighting> mDataset;
    private RecyclerView recyclerView;
    private Context context;

    /**
     * Class that provides a reference to the views for each data item.
     * Complex data items may need more than one view per item, and
     * you provide access to all the views for a data item in a view holder.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

    /**
     * Class that implements a click listener and is
     * customized to include rat sighting data fields.
     */
    private class SightingOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int itemPosition = recyclerView.getChildPosition(v);
            Intent detailView = new Intent(context, ViewSightingActivity.class);
            detailView.putExtra("key", mDataset.get(itemPosition).key);
            detailView.putExtra("date", mDataset.get(itemPosition).date);
            detailView.putExtra("location_type", mDataset.get(itemPosition).location_type);
            detailView.putExtra("zip", mDataset.get(itemPosition).zip);
            detailView.putExtra("address", mDataset.get(itemPosition).address);
            detailView.putExtra("city", mDataset.get(itemPosition).city);
            detailView.putExtra("borough", mDataset.get(itemPosition).borough);
            detailView.putExtra("latitude", mDataset.get(itemPosition).latitude);
            detailView.putExtra("longitude", mDataset.get(itemPosition).longitude);
            context.startActivity(detailView);
        }
    }

    // Provide a suitable constructor (depends on the kind of data set)
    /**
     * Constructor for the RecyclerViewAdapter.
     *
     * @param myDataset list of RatSighting objects (the data set).
     * @param recyclerView recyclerView object to be used.
     * @param c context to which the method invocation should be applied.
     */
    public RecyclerViewAdapter(List<RatSighting> myDataset, RecyclerView recyclerView, Context c) {
        this.mDataset = myDataset;
        this.recyclerView = recyclerView;
        this.context = c;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rat_sighting, parent, false);
        v.setOnClickListener(new SightingOnClickListener());
        return new ViewHolder(v);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mDataset.get(position).address);

    }

    // Return the size of your data set (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}