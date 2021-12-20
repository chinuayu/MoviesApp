package com.example.samplemovieapp.data.model.network

import com.example.samplemovieapp.data.model.entity.Video
import com.google.gson.annotations.SerializedName

data class VideosResponse(
        @SerializedName("results")
        override var results: List<Video>
) : BaseListResponse<Video>
