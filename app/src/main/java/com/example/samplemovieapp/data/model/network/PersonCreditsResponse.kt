package com.example.samplemovieapp.data.model.network

import com.example.samplemovieapp.data.model.entity.Credit
import com.google.gson.annotations.SerializedName

data class PersonCreditsResponse(
        @SerializedName("cast")
        override var results: List<Credit>
) : BaseListResponse<Credit>