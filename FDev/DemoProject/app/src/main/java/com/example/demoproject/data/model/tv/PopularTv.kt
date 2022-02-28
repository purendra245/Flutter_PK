package com.example.demoproject.data.model.tv

data class PopularTv(
    val page: Int,
    val results: List<TvResult>,
    val total_pages: Int,
    val total_results: Int
)