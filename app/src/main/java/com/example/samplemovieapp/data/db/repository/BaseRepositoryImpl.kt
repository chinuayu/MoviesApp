package com.example.samplemovieapp.data.db.repository

import androidx.lifecycle.MutableLiveData
import com.example.samplemovieapp.data.model.network.BaseListResponse
import com.example.samplemovieapp.data.model.network.BasePageListResponse
import com.example.samplemovieapp.util.extension.onException
import com.example.samplemovieapp.util.extension.onFailure
import com.example.samplemovieapp.util.extension.onSuccess
import com.example.samplemovieapp.util.extension.request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

abstract class BaseRepositoryImpl {

    protected suspend fun <Type> loadCall(
            call: () -> Call<Type>,
            result: MutableLiveData<Type>,
            errorText: (String) -> Unit
    ) =
            withContext(Dispatchers.IO) {
                call().request { response ->
                    response.onSuccess { data?.let { result.postValue(it) } }
                    response.onException { message?.let { errorText(it) } }
                    response.onFailure { message?.let { errorText(it) } }
                }
                result.apply { postValue(null) }
            }

    protected suspend fun <Response : BaseListResponse<ListType>, ListType> loadListCall(
            call: () -> Call<Response>,
            result: MutableLiveData<List<ListType>>,
            errorText: (String) -> Unit
    ) =
            withContext(Dispatchers.IO) {
                call().request { response ->
                    response.onSuccess { data?.let { result.postValue((it).results) } }
                    response.onException { message?.let { errorText(it) } }
                    response.onFailure { message?.let { errorText(it) } }
                }
                result.apply { postValue(null) }
            }

    protected suspend fun <Response : BasePageListResponse<ListType>, ListType> loadPageListCall(
            call: () -> Call<Response>,
            result: MutableLiveData<List<ListType>>,
            errorText: (String) -> Unit
    ) =
            withContext(Dispatchers.IO) {
                call().request { response ->
                    response.onSuccess { data?.let { result.postValue((it).results) } }
                    response.onException { message?.let { errorText(it) } }
                    response.onFailure { message?.let { errorText(it) } }
                }
                result.apply { postValue(null) }
            }
}