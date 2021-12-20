package com.example.samplemovieapp.data.db.remote

import com.example.samplemovieapp.data.model.entity.Movie
import com.example.samplemovieapp.data.model.network.CreditsResponse
import com.example.samplemovieapp.data.model.network.GenresResponse
import com.example.samplemovieapp.data.model.network.MoviesResponse
import com.example.samplemovieapp.data.model.network.VideosResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("/${TheMovieDatabaseAPI.API_VERSION}/movie/popular")
    fun fetchPopularList(@Query("page") page: Int): Call<MoviesResponse>

    @GET("/${TheMovieDatabaseAPI.API_VERSION}/movie/upcoming")
    fun fetchUpcomingList(@Query("page") page: Int): Call<MoviesResponse>

    @GET("/${TheMovieDatabaseAPI.API_VERSION}/movie/now_playing")
    fun fetchInTheatersList(@Query("page") page: Int): Call<MoviesResponse>

    @GET("/${TheMovieDatabaseAPI.API_VERSION}/discover/movie")
    fun fetchDiscoverList(@Query("page") page: Int): Call<MoviesResponse>

    @GET("/${TheMovieDatabaseAPI.API_VERSION}/movie/{id}")
    fun fetchDetails(@Path("id") id: Int): Call<Movie>

    @GET("/${TheMovieDatabaseAPI.API_VERSION}/movie/{id}/credits")
    fun fetchCredits(@Path("id") id: Int): Call<CreditsResponse>

    @GET("/${TheMovieDatabaseAPI.API_VERSION}/movie/{id}/videos")
    fun fetchVideos(@Path("id") id: Int): Call<VideosResponse>

    @GET("/${TheMovieDatabaseAPI.API_VERSION}/genre/movie/list")
    fun fetchMovieGenreList(): Call<GenresResponse>
}