package com.example.samplemovieapp.ui.showDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.samplemovieapp.data.db.repository.TvRepository
import com.example.samplemovieapp.data.model.Event
import com.example.samplemovieapp.data.model.GoToCast
import com.example.samplemovieapp.data.model.GoToVideo
import com.example.samplemovieapp.data.model.entity.Cast
import com.example.samplemovieapp.data.model.entity.TvShowDetails
import com.example.samplemovieapp.data.model.entity.Video
import com.example.samplemovieapp.ui.BaseViewModel
import com.example.samplemovieapp.util.extension.liveDataBlockScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TVShowDetailsViewModel @Inject constructor(
        tvShowRepository: TvRepository
) : BaseViewModel(), GoToVideo,
        GoToCast {

    private var tvShowId: Int = 0

    fun setTvShowId(tvShowId: Int) {
        this.tvShowId = tvShowId
    }

    val tvShow: LiveData<TvShowDetails> = liveDataBlockScope {
        tvShowRepository.loadDetails(tvShowId) { mSnackBarText.postValue(Event(it)) }
    }
    val videoList: LiveData<List<Video>> = liveDataBlockScope {
        tvShowRepository.loadVideos(tvShowId) { mSnackBarText.postValue(Event(it)) }
    }
    val castList: LiveData<List<Cast>> = liveDataBlockScope {
        tvShowRepository.loadCredits(tvShowId) { mSnackBarText.postValue(Event(it)) }
    }

    override val goToVideoEvent: MutableLiveData<Event<Video>> = MutableLiveData()
    override val goToCastDetailsEvent: MutableLiveData<Event<Cast>> = MutableLiveData()

}