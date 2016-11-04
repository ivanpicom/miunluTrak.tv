package com.miunlu.app.network;

import com.miunlu.app.global.ApiServer;
import com.miunlu.app.models.Overview;
import com.miunlu.app.models.Trend;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ivan.pico.martin
 */
public interface TrakTvApiEndpointInterface {

    @Headers({"trakt-api-key: " + ApiServer.API_KEY,
            "Content-type: " + ApiServer.API_CONTENT_TYPE,
            "trakt-api-version:" + ApiServer.API_VERSION})

    @GET("/movies/trending")
    Call<Trend[]> getTrending();

    @Headers({"trakt-api-key: " + ApiServer.API_KEY,
            "Content-type: " + ApiServer.API_CONTENT_TYPE,
            "trakt-api-version:" + ApiServer.API_VERSION})
    @GET("/movies/trending")
    Call<Trend[]> getTrendingPaginated(@Query("page") int page, @Query("limit") int limit);


    @Headers({"trakt-api-key: " + ApiServer.API_KEY,
            "Content-type: " + ApiServer.API_CONTENT_TYPE,
            "trakt-api-version:" + ApiServer.API_VERSION})
    @GET("/movies/trending")
    Call<Trend[]> getTrendingSerchingPaginated(@Query("page") int page, @Query("limit") int limit, @Query("query") String query, @Query("fields") String fields); //query=potter&fields=title


    @Headers({"trakt-api-key: " + ApiServer.API_KEY,
            "Content-type: " + ApiServer.API_CONTENT_TYPE,
            "trakt-api-version:" + ApiServer.API_VERSION})
    @GET("/movies/{id}/translations/{language}")
    Call<Overview[]> getShowOverview(@Path("id") String show, @Path("language") String language);


}
