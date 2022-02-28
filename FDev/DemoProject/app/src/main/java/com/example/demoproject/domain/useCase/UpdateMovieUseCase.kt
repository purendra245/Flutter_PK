package com.example.demoproject.domain.useCase

import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.domain.repository.MovieRepository

class UpdateMovieUseCase (val repository: MovieRepository){

    suspend fun updateMovies():List<Movie> = repository.updateMovies()
}