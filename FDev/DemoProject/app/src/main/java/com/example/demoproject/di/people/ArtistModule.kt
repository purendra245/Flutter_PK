package com.example.demoproject.di.people

import com.example.demoproject.domain.useCase.GetPeopleUseCase
import com.example.demoproject.domain.useCase.UpdatePeopleUseCase
import com.example.demoproject.viewModel.artist.ArtistViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ArtistModule {

    @ArtistScope
    @Provides
    fun getArtistViewModelFactory(getPeopleUseCase: GetPeopleUseCase,  updatePeopleUseCase: UpdatePeopleUseCase):ArtistViewModelFactory{
        return ArtistViewModelFactory(getPeopleUseCase,updatePeopleUseCase)
    }

}