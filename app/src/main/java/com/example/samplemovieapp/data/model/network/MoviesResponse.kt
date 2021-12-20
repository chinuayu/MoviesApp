package com.example.samplemovieapp.data.model.network

import com.example.samplemovieapp.data.model.entity.Movie
import com.google.gson.annotations.SerializedName


data class MoviesResponse(
    @SerializedName("page")
    override var page: Int,

    @SerializedName("results")
    override var results: List<Movie>
) : BasePageListResponse<Movie>
