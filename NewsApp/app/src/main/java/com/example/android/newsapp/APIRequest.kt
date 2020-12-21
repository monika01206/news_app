package com.example.android.newsapp

import com.example.android.newsapp.api.NewsApiJson
import retrofit2.http.GET

interface APIRequest {
    @GET("/v1/latest-news?language=en&apiKey=TsTSlatI-7hH040vwXG9bV4hkF1XrdRGZEViXUHEwr13s8Ys")
    suspend fun getNews() :NewsApiJson
}