package com.miunlu.app.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.miunlu.app.R;
import com.miunlu.app.models.MiunMovie;
import com.miunlu.app.models.Overview;
import com.miunlu.app.models.Trend;
import com.miunlu.app.network.TrakTvApiEndpointInterface;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ivan.pico.martin
 */
public class MiunluListFragment extends Fragment {

    // base Url (data source)
    private static final String BASE_URL = "https://api.trakt.tv";
    //Pagination list
    private static final int STREAM_PAGES = 1;
    private static final int STREAM_LIMIT = 7;

    // Dataset
    private MiunMovie miunMovie;
    private ArrayList<Trend> auxArrayList;
    private TrakTvApiEndpointInterface apiService;
    // list view
    protected RecyclerView mRecyclerView;
    private View miunluListFragmentView;

    //Custom Adapter
    private MiunluRecycleAdapter miunluArrayAdapter;

    protected RecyclerView.LayoutManager mLayoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        miunluListFragmentView = inflater.inflate(R.layout.list_fragment, container, false);
        mRecyclerView = (RecyclerView) miunluListFragmentView.findViewById(R.id.lfrag_recycleview);

        // use a linear layout manager
        return miunluListFragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        miunMovie = new MiunMovie();

        // specify an adapter (see also next example)
        miunluArrayAdapter = new MiunluRecycleAdapter(miunMovie);
        mRecyclerView.setAdapter(miunluArrayAdapter);

        // init data set, and show results
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

                } else
                    showServerProblem();
            }

            @Override
            public void onFailure(Call<Trend[]> call, Throwable t) {
                // Log error here since request failed
                showServerProblem();
            }

        });
    }


    private void getOverview() {

        if (auxArrayList.size() > 0) {
            // call service with pagination
            Call<Overview[]> callTrending = apiService.getShowOverview(auxArrayList.get(0).getMovie().getIds().getSlug(), "en");
            callTrending.enqueue(new Callback<Overview[]>() {
                @Override
                public void onResponse(Call<Overview[]> call, Response<Overview[]> response) {
                    int statusCode = response.code();
                    if (statusCode == 200) {

                        Overview[] overviewArray = response.body();

                        if (overviewArray.length > 0)
                            miunMovie.getOverviewList().add(overviewArray[0]);
                        else
                            miunMovie.getOverviewList().add(new Overview());

                    } else {
                        miunMovie.getOverviewList().add(new Overview());
                    }
                    auxArrayList.remove(0);
                    getOverview();

                }

                @Override
                public void onFailure(Call<Overview[]> call, Throwable t) {
                    miunMovie.getOverviewList().add(new Overview());
                    auxArrayList.remove(0);
                    getOverview();
                }

            });

        } else {
            setDataset();
        }
    }

    private void setDataset() {

        // specify an adapter (see also next example)
        miunluArrayAdapter = new MiunluRecycleAdapter(miunMovie);
        mRecyclerView.setAdapter(miunluArrayAdapter);
    }

    // move this to control exceptions class
    private void showServerProblem() {
        Toast.makeText(miunluListFragmentView.getContext(), R.string.error_server, Toast.LENGTH_LONG);
    }


}
