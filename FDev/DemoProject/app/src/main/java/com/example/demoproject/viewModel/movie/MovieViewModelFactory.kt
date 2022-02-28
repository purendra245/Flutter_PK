package com.example.demoproject.viewModel.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demoproject.domain.useCase.GetMovieUseCase
import com.example.demoproject.domain.useCase.UpdateMovieUseCase

class MovieViewModelFactory (val getMovieUseCase: GetMovieUseCase,val updateMovieUseCase: UpdateMovieUseCase)
    : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  MovieViewModel(
            getMovieUseCase,
            updateMovieUseCase
        ) as T
    }

}