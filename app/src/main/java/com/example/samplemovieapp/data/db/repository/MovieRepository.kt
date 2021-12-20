package com.example.samplemovieapp.data.db.repository

import androidx.lifecycle.MutableLiveData
import com.example.samplemovieapp.data.db.remote.MovieService
import com.example.samplemovieapp.data.model.entity.Cast
import com.example.samplemovieapp.data.model.entity.Genre
import com.example.samplemovieapp.data.model.entity.Movie
import com.example.samplemovieapp.data.model.entity.Video

interface MovieRepository {
    suspend fun loadPopularList(
            page: Int,
            errorText: (String) -> Unit
    ): MutableLiveData<List<Movie>>

    suspend fun loadInTheatersList(
            page: Int,
            errorText: (String) -> Unit
    ): MutableLiveData<List<Movie>>

    suspend fun loadUpcomingList(
            page: Int,
            errorText: (String) -> Unit
    ): MutableLiveData<List<Movie>>

    suspend fun loadDiscoverList(
            page: Int,
            errorText: (String) -> Unit
    ): MutableLiveData<List<Movie>>

    suspend fun loadGenreList(errorText: (String) -> Unit): MutableLiveData<List<Genre>>
    suspend fun loadDetails(id: Int, errorText: (String) -> Unit): MutableLiveData<Movie>
    suspend fun loadCredits(id: Int, errorText: (String) -> Unit): MutableLiveData<List<Cast>>
    suspend fun loadVideos(id: Int, errorText: (String) -> Unit): MutableLiveData<List<Video>>
}

class MovieRepositoryImpl(private val movieService: MovieService) : MovieRepository,
        BaseRepositoryImpl() {

    override suspend fun loadPopularList(
            page: Int,
            errorText: (String) -> Unit
    ): MutableLiveData<List<Movie>> {
        return loadPageListCall(
                { movieService.fetchPopularList(page) },
                MutableLiveData<List<Movie>>(),
                errorText
        )
    }

    override suspend fun loadInTheatersList(
            page: Int,
            errorText: (String) -> Unit
    ): MutableLiveData<List<Movie>> {
        return loadPageListCall(
                { movieService.fetchInTheatersList(page) },
                MutableLiveData<List<Movie>>(),
                errorText
        )
    }

    override suspend fun loadUpcomingList(
            page: Int,
            errorText: (String) -> Unit
    ): MutableLiveData<List<Movie>> {
        return loadPageListCall(
                { movieService.fetchUpcomingList(page) },
                MutableLiveData<List<Movie>>(),
                errorText
        )
    }

    override suspend fun loadDiscoverList(
            page: Int,
            errorText: (String) -> Unit
    ): MutableLiveData<List<Movie>> {
        return loadPageListCall(
                { movieService.fetchDiscoverList(page) },
                MutableLiveData<List<Movie>>(),
                errorText
        )
    }

    override suspend fun loadGenreList(errorText: (String) -> Unit): MutableLiveData<List<Genre>> {
        return loadListCall(
                { movieService.fetchMovieGenreList() },
                MutableLiveData<List<Genre>>(),
                errorText
        )
    }

    override suspend fun loadDetails(id: Int, errorText: (String) -> Unit): MutableLiveData<Movie> {
        return loadCall({ movieService.fetchDetails(id) }, MutableLiveData<Movie>(), errorText)
    }

    override suspend fun loadCredits(
            id: Int,
            errorText: (String) -> Unit
    ): MutableLiveData<List<Cast>> {
        return loadListCall(
                { movieService.fetchCredits(id) },
                MutableLiveData<List<Cast>>(),
                errorText
        )
    }

    override suspend fun loadVideos(
            id: Int,
            errorText: (String) -> Unit
    ): MutableLiveData<List<Video>> {
        return loadListCall(
                { movieService.fetchVideos(id) },
                MutableLiveData<List<Video>>(),
                errorText
        )
    }
}
