package com.example.demoproject.di.tv

import com.example.demoproject.domain.useCase.GetMovieUseCase
import com.example.demoproject.domain.useCase.GetTvShowUseCase
import com.example.demoproject.domain.useCase.UpdateMovieUseCase
import com.example.demoproject.domain.useCase.UpdateTvShowUseCase
import com.example.demoproject.viewModel.movie.MovieViewModelFactory
import com.example.demoproject.viewModel.tv.TvViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TvModule {

    @TvScope
    @Provides
    fun getTvViewModelFactory( getTvShowUseCase: GetTvShowUseCase,  updateTvShowUseCase: UpdateTvShowUseCase):TvViewModelFactory{
        return TvViewModelFactory(getTvShowUseCase,updateTvShowUseCase)
    }

}