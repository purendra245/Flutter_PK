package com.example.demoproject.domain.repository

import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.data.model.people.People
import com.example.demoproject.data.model.tv.TvResult

interface MovieRepository {

    //Popular Movies
    suspend fun getMovies():List<Movie>
    suspend fun updateMovies():List<Movie>


}