package com.example.nerdeyesem.retrofit;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {
    @Headers("user-key: 2c73167e80a1722e5935b6946f122cb9")
    @GET("v2.1/search")
    Call<SearchResponse> getRestaurants(@Query("lat") double lat,
                                        @Query("lon") double lng,
                                        @Query("start") int start,
                                        @Query("count") int count,
                                        @Query("sort") String sort,
                                        @Query("q") String search);

}
