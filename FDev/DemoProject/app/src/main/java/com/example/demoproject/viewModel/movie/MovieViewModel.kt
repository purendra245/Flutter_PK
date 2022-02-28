package com.example.demoproject.viewModel.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.domain.useCase.GetMovieUseCase
import com.example.demoproject.domain.useCase.UpdateMovieUseCase

class MovieViewModel(val getMovieUseCase: GetMovieUseCase ,val updateMovieUseCase: UpdateMovieUseCase  ): ViewModel() {


    fun getMovie() = liveData <List<Movie>>{
        val moviewList  = getMovieUseCase.getMovies()
        emit(moviewList)
    }


    fun updateMovie() = liveData <List<Movie>>{
        val updateMoviewList  = updateMovieUseCase.updateMovies()
        emit(updateMoviewList)
    }


}