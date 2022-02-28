package com.example.demoproject.viewModel.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.demoproject.domain.useCase.GetMovieUseCase
import com.example.demoproject.domain.useCase.GetPeopleUseCase
import com.example.demoproject.domain.useCase.UpdateMovieUseCase
import com.example.demoproject.domain.useCase.UpdatePeopleUseCase

class ArtistViewModelFactory (val getPeopleUseCase: GetPeopleUseCase, val updatePeopleUseCase: UpdatePeopleUseCase)
    : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return  ArtistViewModel(
            getPeopleUseCase,
            updatePeopleUseCase
        ) as T
    }

}