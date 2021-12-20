package com.example.samplemovieapp.data.db.remote

import com.example.samplemovieapp.data.model.entity.Person
import com.example.samplemovieapp.data.model.network.PersonCreditsResponse
import com.example.samplemovieapp.data.model.network.PersonImagesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface PeopleService {
    @GET("/${TheMovieDatabaseAPI.API_VERSION}/person/{id}")
    fun fetchDetails(@Path("id") id: Int): Call<Person>

    @GET("/${TheMovieDatabaseAPI.API_VERSION}/person/{id}/images")
    fun fetchImages(@Path("id") id: Int): Call<PersonImagesResponse>

    @GET("/${TheMovieDatabaseAPI.API_VERSION}/person/{id}/combined_credits")
    fun fetchCredits(@Path("id") id: Int): Call<PersonCreditsResponse>
}