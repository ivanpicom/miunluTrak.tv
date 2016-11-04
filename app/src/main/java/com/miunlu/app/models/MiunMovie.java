package com.miunlu.app.models;

import java.util.ArrayList;

/**
 * Created by ivan.pico.martin
 */
public class MiunMovie {
    private ArrayList<Trend> trendList;
    private ArrayList<Overview> overviewList;


    public MiunMovie() {
        trendList = new ArrayList<Trend>();
        overviewList = new ArrayList<Overview>();
    }

    public void clear() {
        trendList.clear();
        overviewList.clear();
    }

    public ArrayList<Trend> getTrendList() {
        return trendList;
    }

    public void setTrendList(ArrayList<Trend> trendList) {
        this.trendList = trendList;
    }

    public void addTrendList(ArrayList<Trend> trendList) {
        for(Trend trend: trendList) {
            this.trendList.add(trend);
        }
    }

    public ArrayList<Overview> getOverviewList() {
        return overviewList;
    }

    public void setOverviewList(ArrayList<Overview> overviewList) {
        this.overviewList = overviewList;
    }

    public void addOverview(int position, Overview overview) {

        overviewList.add(overview);

    }

    public void addTrend(int position, Trend trend) {

        trendList.add(trend);
    }

    public int remove(Trend trend, Overview overview) {
        int position = trendList.indexOf(trend);

        overviewList.remove(position);
        trendList.remove(position);
        return position;
    }

    public int size() {
        return Math.min(trendList.size(),overviewList.size());
    }
}
