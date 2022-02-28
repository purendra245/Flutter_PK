package com.example.demoproject.domain.useCase

import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.domain.repository.MovieRepository

class GetMovieUseCase (val repository: MovieRepository){

    suspend fun getMovies():List<Movie> = repository.getMovies()

}