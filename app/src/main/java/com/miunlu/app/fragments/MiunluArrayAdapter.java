package com.miunlu.app.fragments;

/**
 * Created by ivan.pico.martin
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.miunlu.app.R;
import com.miunlu.app.models.MiunMovie;

public class MiunluArrayAdapter extends ArrayAdapter<MiunMovie> {

    private final Context context;
    private final MiunMovie values;

    public MiunluArrayAdapter(Context context, MiunMovie values) {
        super(context, 0);//, new ArrayList(values.getOverviewList()));//values.getOverviewList());
        this.context = context;
        this.values = values;
//        return;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

//        Overview overview = values.getOverviewList().get(position);
//        Trend trend = values.getTrendList().get(position);


        View rowView = inflater.inflate(R.layout.row_movie_layout, parent, false);

        TextView rmly_tv_title = (TextView) rowView.findViewById(R.id.rmly_tv_title);
        TextView rmly_tv_year = (TextView) rowView.findViewById(R.id.rmly_tv_year);
        TextView rmly_tv_overview = (TextView) rowView.findViewById(R.id.rmly_tv_overview);
        ImageView rmly_iv_movieimage = (ImageView) rowView.findViewById(R.id.rmly_iv_movieimage);


//        rmly_tv_title.setText( trend.getMovie().getTitle());
//        rmly_tv_year.setText(String.valueOf(trend.getMovie().getYear()));
//        rmly_tv_overview.setText(overview.getOverview());

        return rowView;


    }
}