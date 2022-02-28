package com.example.demoproject.domain.repository

import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.data.model.people.People
import com.example.demoproject.data.model.tv.TvResult

interface PeopleRepository {

    //Popular People
    suspend fun getPopularPeople():List<People>
    suspend fun updatePopularPeople():List<People>
}