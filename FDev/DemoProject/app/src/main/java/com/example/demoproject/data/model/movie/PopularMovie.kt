package com.example.demoproject.data.model.movie


data class PopularMovie(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)