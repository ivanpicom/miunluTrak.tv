package models;

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

    public ArrayList<Overview> getOverviewList() {
        return overviewList;
    }

    public void setOverviewList(ArrayList<Overview> overviewList) {
        this.overviewList = overviewList;
    }

    public void addOverview(Overview overview) {

        overviewList.add(overview);

    }

}
