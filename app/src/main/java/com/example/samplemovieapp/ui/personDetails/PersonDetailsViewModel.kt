package com.example.samplemovieapp.ui.personDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.samplemovieapp.data.db.repository.PeopleRepository
import com.example.samplemovieapp.data.model.Event
import com.example.samplemovieapp.data.model.GoToCredit
import com.example.samplemovieapp.data.model.GoToImage
import com.example.samplemovieapp.data.model.entity.Credit
import com.example.samplemovieapp.data.model.entity.Image
import com.example.samplemovieapp.data.model.entity.Person
import com.example.samplemovieapp.ui.BaseViewModel
import com.example.samplemovieapp.util.extension.liveDataBlockScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
        personRepository: PeopleRepository
) : BaseViewModel(), GoToImage, GoToCredit {

    private var personId: Int = 0

    fun setPersonId(personId: Int) {
        this.personId = personId
    }

    val person: LiveData<Person> = liveDataBlockScope {
        personRepository.loadDetails(personId) { mSnackBarText.postValue(Event(it)) }
    }
    val imageList: LiveData<List<Image>> = liveDataBlockScope {
        personRepository.loadImages(personId) { mSnackBarText.postValue(Event(it)) }
    }
    val creditList: LiveData<List<Credit>> = liveDataBlockScope {
        personRepository.loadCredits(personId) { mSnackBarText.postValue(Event(it)) }
    }

    override val goToImageEvent: MutableLiveData<Event<Image>> = MutableLiveData()
    override val goToCreditEvent: MutableLiveData<Event<Credit>> = MutableLiveData()

}