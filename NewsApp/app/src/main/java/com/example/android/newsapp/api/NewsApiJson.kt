package com.example.android.newsapp.api

data class NewsApiJson(
    val news: List<New>,
    val page: Int,
    val status: String
)