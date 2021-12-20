package com.example.samplemovieapp.data.model.network

interface BaseListResponse<T> {
    var results: List<T>
}
