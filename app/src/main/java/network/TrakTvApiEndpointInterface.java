package network;

import models.Movie;
import models.Trend;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by ivan.pico.martin
 */
public interface TrakTvApiEndpointInterface {

    @Headers({  "trakt-api-key: 019a13b1881ae971f91295efc7fdecfa48b32c2a69fe6dd03180ff59289452b8",
                "Content-type: application/json",
                "trakt-api-version: 2"})

    @GET("/movies/trending")
    Call<Trend[]> getTrending();


}
