package com.example.samplemovieapp.ui.shows

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.switchMap
import com.example.samplemovieapp.data.db.repository.TvRepository
import com.example.samplemovieapp.data.model.Event
import com.example.samplemovieapp.data.model.GoToTvShow
import com.example.samplemovieapp.data.model.entity.TvShow
import com.example.samplemovieapp.ui.BaseViewModel
import com.example.samplemovieapp.util.extension.appendList
import com.example.samplemovieapp.util.extension.liveDataBlockScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TvShowsViewModel @Inject constructor(private val tvShowRepository: TvRepository) :
        BaseViewModel(), GoToTvShow {

    private val loadedTvShowList: LiveData<List<TvShow>>
    private val tvShowsPage = MutableLiveData<Int>().apply { value = 1 }

    val tvShowList = MediatorLiveData<MutableList<TvShow>>()

    override val goToTvShowEvent: MutableLiveData<Event<TvShow>> = MutableLiveData()

    init {
        loadedTvShowList = tvShowsPage.switchMap {
            liveDataBlockScope {
                tvShowRepository.loadDiscoverList(it) { mSnackBarText.postValue(Event(it)) }
            }
        }
        tvShowList.addSource(loadedTvShowList) { it?.let { list -> tvShowList.appendList(list) } }
    }

    fun loadMoreTvShows() {
        tvShowsPage.value = tvShowsPage.value?.plus(1)
    }
}