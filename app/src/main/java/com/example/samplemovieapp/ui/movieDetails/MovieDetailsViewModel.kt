package com.example.samplemovieapp.ui.movieDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.samplemovieapp.data.db.repository.MovieRepository
import com.example.samplemovieapp.data.model.Event
import com.example.samplemovieapp.data.model.GoToCast
import com.example.samplemovieapp.data.model.GoToVideo
import com.example.samplemovieapp.data.model.entity.Cast
import com.example.samplemovieapp.data.model.entity.Movie
import com.example.samplemovieapp.data.model.entity.Video
import com.example.samplemovieapp.ui.BaseViewModel
import com.example.samplemovieapp.util.extension.liveDataBlockScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val movieRepository: MovieRepository) :
        BaseViewModel(), GoToCast,
        GoToVideo {
    private var movieId: Int = 0

    fun setMovieId(movieId: Int) {
        this.movieId = movieId
    }

    val movie: LiveData<Movie> = liveDataBlockScope {
        movieRepository.loadDetails(movieId) { mSnackBarText.postValue(Event(it)) }
    }
    val videoList: LiveData<List<Video>> = liveDataBlockScope {
        movieRepository.loadVideos(movieId) { mSnackBarText.postValue(Event(it)) }
    }
    val castList: LiveData<List<Cast>> = liveDataBlockScope {
        movieRepository.loadCredits(movieId) { mSnackBarText.postValue(Event(it)) }
    }

    override val goToCastDetailsEvent: MutableLiveData<Event<Cast>> = MutableLiveData()
    override val goToVideoEvent: MutableLiveData<Event<Video>> = MutableLiveData()

}
