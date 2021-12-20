package com.example.samplemovieapp.ui.showAll

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.example.samplemovieapp.data.db.repository.MovieRepository
import com.example.samplemovieapp.data.model.Event
import com.example.samplemovieapp.data.model.GoToMovie
import com.example.samplemovieapp.data.model.MovieListType
import com.example.samplemovieapp.data.model.entity.Movie
import com.example.samplemovieapp.ui.BaseViewModel
import com.example.samplemovieapp.util.extension.appendList
import com.example.samplemovieapp.util.extension.liveDataBlockScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShowAllViewModel @Inject constructor(
        movieRepository: MovieRepository
) : BaseViewModel(), GoToMovie {

    private var movieListType: MovieListType = MovieListType.POPULAR

    fun setMovieListType(movieListType: MovieListType) {
        this.movieListType = movieListType
    }

    private val moviePage = MutableLiveData<Int>().apply { value = 1 }
    private val loadedMovieList: LiveData<List<Movie>> = when (movieListType) {
        MovieListType.POPULAR -> {
            moviePage.switchMap {
                liveDataBlockScope {
                    movieRepository.loadPopularList(it) { mSnackBarText.postValue(Event(it)) }
                }
            }
        }
        MovieListType.IN_THEATERS -> {
            moviePage.switchMap {
                liveDataBlockScope {
                    movieRepository.loadInTheatersList(it) { mSnackBarText.postValue(Event(it)) }
                }
            }
        }
        else -> {
            moviePage.switchMap {
                liveDataBlockScope {
                    movieRepository.loadUpcomingList(it) { mSnackBarText.postValue(Event(it)) }
                }
            }
        }
    }
    val movieList = MediatorLiveData<MutableList<Movie>>()

    override val goToMovieDetailsEvent: MutableLiveData<Event<Movie>> = MutableLiveData()

    init {
        movieList.addSource(loadedMovieList) { it?.let { list -> movieList.appendList(list) } }
    }

    fun loadMoreMovies() {
        moviePage.value = moviePage.value?.plus(1)
    }
}