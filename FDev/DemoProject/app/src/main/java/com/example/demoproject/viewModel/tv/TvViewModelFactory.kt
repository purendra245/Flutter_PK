package com.example.demoproject.viewModel.tv

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demoproject.domain.useCase.*

class TvViewModelFactory (val getTvShowUseCase: GetTvShowUseCase, val updateTvShowUseCase: UpdateTvShowUseCase)
    : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  TVViewModel(
            getTvShowUseCase,
            updateTvShowUseCase
        ) as T
    }

}