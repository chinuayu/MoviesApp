package com.example.samplemovieapp.data.model.network

import com.example.samplemovieapp.data.model.entity.Genre
import com.google.gson.annotations.SerializedName

data class GenresResponse(
        @SerializedName("genres")
        override var results: List<Genre>
) : BaseListResponse<Genre>
