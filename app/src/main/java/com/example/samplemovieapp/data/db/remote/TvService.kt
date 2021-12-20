package com.example.samplemovieapp.data.db.remote

import com.example.samplemovieapp.data.model.entity.TvShowDetails
import com.example.samplemovieapp.data.model.network.CreditsResponse
import com.example.samplemovieapp.data.model.network.TvDiscoverResponse
import com.example.samplemovieapp.data.model.network.VideosResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvService {
    @GET("/${TheMovieDatabaseAPI.API_VERSION}/discover/tv")
    fun fetchDiscoveryList(@Query("page") page: Int): Call<TvDiscoverResponse>

    @GET("/${TheMovieDatabaseAPI.API_VERSION}/tv/{id}")
    fun fetchDetails(@Path("id") id: Int): Call<TvShowDetails>

    @GET("/${TheMovieDatabaseAPI.API_VERSION}/tv/{id}/credits")
    fun fetchCredits(@Path("id") id: Int): Call<CreditsResponse>

    @GET("/${TheMovieDatabaseAPI.API_VERSION}/tv/{id}/videos")
    fun fetchVideos(@Path("id") id: Int): Call<VideosResponse>
}