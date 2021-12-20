package com.example.samplemovieapp.data.db.repository

import androidx.lifecycle.MutableLiveData
import com.example.samplemovieapp.data.db.remote.PeopleService
import com.example.samplemovieapp.data.model.entity.Credit
import com.example.samplemovieapp.data.model.entity.Image
import com.example.samplemovieapp.data.model.entity.Person

interface PeopleRepository {
    suspend fun loadDetails(id: Int, errorText: (String) -> Unit): MutableLiveData<Person>
    suspend fun loadCredits(id: Int, errorText: (String) -> Unit): MutableLiveData<List<Credit>>
    suspend fun loadImages(id: Int, errorText: (String) -> Unit): MutableLiveData<List<Image>>
}

class PeopleRepositoryImpl(
        private val peopleService: PeopleService
) : PeopleRepository, BaseRepositoryImpl() {

    override suspend fun loadDetails(
            id: Int,
            errorText: (String) -> Unit
    ): MutableLiveData<Person> {
        return loadCall({ peopleService.fetchDetails(id) }, MutableLiveData<Person>(), errorText)
    }

    override suspend fun loadCredits(
            id: Int,
            errorText: (String) -> Unit
    ): MutableLiveData<List<Credit>> {
        return loadListCall(
                { peopleService.fetchCredits(id) },
                MutableLiveData<List<Credit>>(),
                errorText
        )
    }

    override suspend fun loadImages(
            id: Int,
            errorText: (String) -> Unit
    ): MutableLiveData<List<Image>> {
        return loadListCall(
                { peopleService.fetchImages(id) },
                MutableLiveData<List<Image>>(),
                errorText
        )
    }
}