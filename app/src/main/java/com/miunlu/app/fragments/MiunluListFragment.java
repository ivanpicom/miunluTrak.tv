package com.miunlu.app.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
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
    private static final int STREAM_LIMIT = 10;
    // searching filters, separated by comma
    private static final String SERACHING_FIELDS = "title";
    private int currentPage = STREAM_PAGES;

    // Dataset
    private MiunMovie miunMovie;
    private ArrayList<Trend> auxArrayList;
    private TrakTvApiEndpointInterface apiService;
    // list view
    protected RecyclerView mRecyclerView;
    private View miunluListFragmentView;

    //Custom Adapter
    private MiunluRecycleAdapter miunluArrayAdapter;

    protected LinearLayoutManager mLayoutManager;

    private boolean loading = false;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    private EditText act_maintv_search;
    private Call<Overview[]> callOverview;
    private Call<Trend[]> callTrending;
    // calls have been cancelled
    private boolean callsCancelled = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        miunluListFragmentView = inflater.inflate(R.layout.list_fragment, container, false);

        act_maintv_search = (EditText) miunluListFragmentView.findViewById(R.id.act_maintv_search);

        act_maintv_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void onTextChanged(final CharSequence s, int start, int before, int count) {

                callsCancelled = true;
                currentPage = 0;
                cancelCalls();
                miunMovie.clear();
                // Execute some code after 2 seconds have passed
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        callsCancelled = false;
                        showMovies(currentPage, STREAM_LIMIT, s.toString(), SERACHING_FIELDS);
                    }
                }, 2000);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

        });


        mRecyclerView = (RecyclerView) miunluListFragmentView.findViewById(R.id.lfrag_recycleview);


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            currentPage++;
                            showMovies(currentPage, STREAM_LIMIT, null, null);
                        }
                    }
                }
            }
        });

        // use a linear layout manager
        return miunluListFragmentView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mLayoutManager = new LinearLayoutManager(mRecyclerView.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        miunMovie = new MiunMovie();
//        miunMovie.clear();

        // specify an adapter (see also next example)
        miunluArrayAdapter = new MiunluRecycleAdapter(getActivity(), miunMovie);
        mRecyclerView.setAdapter(miunluArrayAdapter);


        // init data set, and show results
        showMovies(STREAM_PAGES, STREAM_LIMIT, null, null);

    }

    public void showMovies(int page, int limit, String query, String fields) {


        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(TrakTvApiEndpointInterface.class);

        // call service with pagination
        if (query != null)
            callTrending = apiService.getTrendingSerchingPaginated(page, limit, query, fields);
        else
            callTrending = apiService.getTrendingPaginated(page, limit);

        callTrending.enqueue(new Callback<Trend[]>() {
            @Override
            public void onResponse(Call<Trend[]> call, Response<Trend[]> response) {
                int statusCode = response.code();
                if (statusCode == 200) {

                    Trend[] trendArray = response.body();

                    ArrayList<Trend> arrayListTrends = new ArrayList<Trend>(Arrays.asList(trendArray));
                    miunMovie.addTrendList(arrayListTrends);
                    auxArrayList = new ArrayList<Trend>(Arrays.asList(trendArray));

                    if (!callsCancelled)
                        getOverview();

                } else
                    showServerProblem();
            }

            @Override
            public void onFailure(Call<Trend[]> call, Throwable t) {
                // error here since request failed
                showServerProblem();
            }

        });
    }


    private void getOverview() {

        if (auxArrayList.size() > 0 && !callsCancelled) {
            // call service with pagination
            callOverview = apiService.getShowOverview(auxArrayList.get(0).getMovie().getIds().getSlug(), "en");
            callOverview.enqueue(new Callback<Overview[]>() {
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
                    if (auxArrayList.size() > 0) {
                        auxArrayList.remove(0);
                        getOverview();
                    }

                }

                @Override
                public void onFailure(Call<Overview[]> call, Throwable t) {
                    miunMovie.getOverviewList().add(new Overview());
                    auxArrayList.remove(0);
                    getOverview();
                }

            });

        } else {
            if (!callsCancelled)
                setDataset();
        }
    }

    private void setDataset() {

        // specify an adapter (see also next example)
        loading = true;
        miunluArrayAdapter.notifyDataSetChanged();
    }

    // move this to control to an notification class
    private void showServerProblem() {
        Toast.makeText(miunluListFragmentView.getContext(), R.string.error_server, Toast.LENGTH_LONG).show();

    }

    public void cancelCalls() {
        // put it in a call arrays
        callOverview.cancel();
        callTrending.cancel();
    }


}
