package com.example.samplemovieapp.data.model.network

import com.example.samplemovieapp.data.model.entity.Image
import com.google.gson.annotations.SerializedName

data class PersonImagesResponse(
    @SerializedName("profiles")
    override var results: List<Image>
) : BaseListResponse<Image>