package com.example.samplemovieapp.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.example.samplemovieapp.data.db.repository.MovieRepository
import com.example.samplemovieapp.data.model.Event
import com.example.samplemovieapp.data.model.GoToMovie
import com.example.samplemovieapp.data.model.entity.Movie
import com.example.samplemovieapp.ui.BaseViewModel
import com.example.samplemovieapp.util.extension.appendList
import com.example.samplemovieapp.util.extension.liveDataBlockScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val movieRepository: MovieRepository) :
        BaseViewModel(), GoToMovie {

    private val loadedMovies: LiveData<List<Movie>>
    private val moviesPage = MutableLiveData<Int>().apply { value = 1 }

    override val goToMovieDetailsEvent: MutableLiveData<Event<Movie>> = MutableLiveData()

    val movieList = MediatorLiveData<MutableList<Movie>>()

    init {
        loadedMovies = moviesPage.switchMap {
            liveDataBlockScope {
                movieRepository.loadDiscoverList(it) { mSnackBarText.postValue(Event(it)) }
            }
        }

        movieList.addSource(loadedMovies) { it?.let { list -> movieList.appendList(list) } }
    }

    fun loadMoreMovies() {
        moviesPage.value = moviesPage.value?.plus(1)
    }
}