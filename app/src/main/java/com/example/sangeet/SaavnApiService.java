package com.example.sangeet;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SaavnApiService {
    @GET("api/songs")
    Call<SongResponse> getSongs();

    @GET("api/songs/{id}")
    Call<SongResponse> getSongById(@Path("id") String id);
}