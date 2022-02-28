package com.example.demoproject.data.model.people

data class PopularPerson(
    val page: Int,
    val results: List<People>,
    val total_pages: Int,
    val total_results: Int
)