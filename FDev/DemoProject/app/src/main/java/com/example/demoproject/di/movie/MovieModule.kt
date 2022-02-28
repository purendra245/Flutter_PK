package com.example.demoproject.di.movie

import com.example.demoproject.domain.useCase.GetMovieUseCase
import com.example.demoproject.domain.useCase.UpdateMovieUseCase
import com.example.demoproject.viewModel.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {

    @MovieScope
    @Provides
    fun getMovieViewModelFactory( getMovieUseCase: GetMovieUseCase,  updateMovieUseCase: UpdateMovieUseCase):MovieViewModelFactory{
        return MovieViewModelFactory(getMovieUseCase,updateMovieUseCase)
    }

}