package com.miunlu.app.fragments;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.miunlu.app.R;
import com.miunlu.app.models.MiunMovie;
import com.miunlu.app.models.Overview;
import com.miunlu.app.models.Trend;

/**
 * Created by ivan.pico.martin
 */
public class MiunluRecycleAdapter extends RecyclerView.Adapter<MiunluRecycleAdapter.ViewHolder> {
    // dataset
    private MiunMovie mDataset;


    // Provide a suitable constructor (depends on the kind of dataset)
    public MiunluRecycleAdapter(Activity activity, MiunMovie mDataset) {
        this.mDataset = mDataset;

    }

    public void add(int position, Trend trend, Overview overview) {
        mDataset.addTrend(position, trend);
        mDataset.addOverview(position, overview);
        notifyItemInserted(position);
    }

    public void remove(Trend trend, Overview overview) {

        int position = mDataset.remove(trend, overview);
        notifyItemRemoved(position);
    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_movie_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Trend trend = mDataset.getTrendList().get(position);
        Overview overview = mDataset.getOverviewList().get(position);

        holder.rmly_tv_title.setText(trend.getMovie().getTitle());
        holder.rmly_tv_year.setText(String.valueOf(trend.getMovie().getYear()));
        holder.rmly_tv_overview.setText(overview.getOverview());

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView rmly_tv_title;
        TextView rmly_tv_year;
        ImageView rmly_iv_movieimage;
        TextView rmly_tv_overview;


        public ViewHolder(View view) {
            super(view);

            rmly_tv_title = (TextView) view.findViewById(R.id.rmly_tv_title);
            rmly_tv_year = (TextView) view.findViewById(R.id.rmly_tv_year);
            rmly_tv_overview = (TextView) view.findViewById(R.id.rmly_tv_overview);
            rmly_iv_movieimage = (ImageView) view.findViewById(R.id.rmly_iv_movieimage);

        }
    }

}
