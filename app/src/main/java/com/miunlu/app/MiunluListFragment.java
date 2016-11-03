package com.miunlu.app;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;

import models.MiunMovie;
import models.Overview;
import models.Trend;
import network.TrakTvApiEndpointInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ivan.pico.martin
 */
public class MiunluListFragment extends ListFragment {


    private MiunluArrayAdapter miunluArrayAdapter;
    private MiunMovie miunMovie;
    private ArrayList<Trend> auxArrayList;
    private static final String BASE_URL = "https://api.trakt.tv";
    private static final int STREAM_PAGES = 1;
    private static final int STREAM_LIMIT = 15;
    private TrakTvApiEndpointInterface apiService;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        miunMovie = new MiunMovie();
        this.getListView().setDivider(null);

        showMovies();

    }


    public void showMovies() {

        miunMovie.clear();

        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(TrakTvApiEndpointInterface.class);

        // call service with pagination
        Call<Trend[]> callTrending = apiService.getTrendingPaginated(STREAM_PAGES, STREAM_LIMIT);
        callTrending.enqueue(new Callback<Trend[]>() {
            @Override
            public void onResponse(Call<Trend[]> call, Response<Trend[]> response) {
                int statusCode = response.code();
                if (statusCode == 200) {

                    Trend[] trendArray = response.body();


                    ArrayList<Trend> arrayListTrends = new ArrayList<Trend>(Arrays.asList(trendArray));

                    miunMovie.setTrendList(arrayListTrends);

                    auxArrayList = new ArrayList<Trend>(Arrays.asList(trendArray));

                    getOverview();

                    Log.i("SUCCESS Retrofit ", "SUCCESS getTrendingPaginated");

                } else
                    Log.i("ERROR Retrofit ", "Error CODE " + statusCode);
            }

            @Override
            public void onFailure(Call<Trend[]> call, Throwable t) {
                // Log error here since request failed
                Log.i("ERROR Retrofit ", "Error connection");
            }

        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);


        return super.onCreateView(inflater, container, savedInstanceState);


    }


    private void getOverview() {

        if (auxArrayList.size() > 0) {
            // call service with pagination
            Log.i("Info getOverview","Slug: " + auxArrayList.get(0).getMovie().getIds().getSlug());
            Call<Overview[]> callTrending = apiService.getShowOverview(auxArrayList.get(0).getMovie().getIds().getSlug(), "en");
            callTrending.enqueue(new Callback<Overview[]>() {
                @Override
                public void onResponse(Call<Overview[]> call, Response<Overview[]> response) {
                    int statusCode = response.code();
                    if (statusCode == 200) {
                        // TODO: remove hardcored input
                        Overview[] overviewArray = response.body();
                        Log.i("SUCCESS Retrofit ", "SUCCESS getShowOverview");

                        if (overviewArray.length > 0)
                            miunMovie.getOverviewList().add(overviewArray[0]);
                        else
                            miunMovie.getOverviewList().add(new Overview());

                    } else {
                        Log.i("ERROR Retrofit ", "Error CODE " + statusCode + " in getShowOverview");
                        miunMovie.getOverviewList().add(new Overview());
                    }
                    auxArrayList.remove(0);
                    getOverview();

                }

                @Override
                public void onFailure(Call<Overview[]> call, Throwable t) {
                    // Log error here since request failed
                    Log.i("ERROR Retrofit ", "Error getShowOverview");
                    miunMovie.getOverviewList().add(new Overview());
                    auxArrayList.remove(0);
                    getOverview();
                }

            });

        } else {
            miunluArrayAdapter = new MiunluArrayAdapter(getContext(), miunMovie);
            setListAdapter(miunluArrayAdapter);
        }
    }


}
