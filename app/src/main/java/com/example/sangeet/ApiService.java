package com.example.sangeet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("https://saaavn.dev/api/songs")
    Call<SongResponse> getSongs(@Query("query") String query);
}
