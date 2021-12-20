package com.example.samplemovieapp.data.model.network

interface BasePageListResponse<T> {
    var page: Int
    var results: List<T>
}