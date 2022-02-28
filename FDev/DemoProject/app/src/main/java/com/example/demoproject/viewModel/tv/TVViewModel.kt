package com.example.demoproject.viewModel.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.data.model.people.People
import com.example.demoproject.data.model.tv.TvResult
import com.example.demoproject.domain.useCase.*

class TVViewModel(val getTvShowUseCase: GetTvShowUseCase, val updateTvShowUseCase: UpdateTvShowUseCase ): ViewModel() {


    fun getTvShow() = liveData <List<TvResult>>{
        val getTv  = getTvShowUseCase.getTvShow()
        emit(getTv)
    }


    fun updateTvShow() = liveData <List<TvResult>>{
        val updateTv  = updateTvShowUseCase.updateTvShow()
        emit(updateTv)
    }


}