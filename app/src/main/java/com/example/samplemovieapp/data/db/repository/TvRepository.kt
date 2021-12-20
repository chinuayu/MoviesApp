package com.example.samplemovieapp.data.db.repository

import androidx.lifecycle.MutableLiveData
import com.example.samplemovieapp.data.db.remote.TvService
import com.example.samplemovieapp.data.model.entity.Cast
import com.example.samplemovieapp.data.model.entity.TvShow
import com.example.samplemovieapp.data.model.entity.TvShowDetails
import com.example.samplemovieapp.data.model.entity.Video

interface TvRepository {
    suspend fun loadDiscoverList(
            id: Int,
            errorText: (String) -> Unit
    ): MutableLiveData<List<TvShow>>

    suspend fun loadDetails(id: Int, errorText: (String) -> Unit): MutableLiveData<TvShowDetails>
    suspend fun loadCredits(id: Int, errorText: (String) -> Unit): MutableLiveData<List<Cast>>
    suspend fun loadVideos(id: Int, errorText: (String) -> Unit): MutableLiveData<List<Video>>
}

class TvRepositoryImpl(private val tvService: TvService) : TvRepository, BaseRepositoryImpl() {
    override suspend fun loadDiscoverList(
            id: Int,
            errorText: (String) -> Unit
    ): MutableLiveData<List<TvShow>> {
        return loadPageListCall(
                { tvService.fetchDiscoveryList(id) },
                MutableLiveData<List<TvShow>>(),
                errorText
        )
    }

    override suspend fun loadDetails(
            id: Int,
            errorText: (String) -> Unit
    ): MutableLiveData<TvShowDetails> {
        return loadCall({ tvService.fetchDetails(id) }, MutableLiveData<TvShowDetails>(), errorText)
    }

    override suspend fun loadCredits(
            id: Int,
            errorText: (String) -> Unit
    ): MutableLiveData<List<Cast>> {
        return loadListCall(
                { tvService.fetchCredits(id) },
                MutableLiveData<List<Cast>>(),
                errorText
        )
    }

    override suspend fun loadVideos(
            id: Int,
            errorText: (String) -> Unit
    ): MutableLiveData<List<Video>> {
        return loadListCall(
                { tvService.fetchVideos(id) },
                MutableLiveData<List<Video>>(),
                errorText
        )
    }
}