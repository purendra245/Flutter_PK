package com.example.demoproject.viewModel.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.demoproject.data.model.movie.Movie
import com.example.demoproject.data.model.people.People
import com.example.demoproject.domain.useCase.GetMovieUseCase
import com.example.demoproject.domain.useCase.GetPeopleUseCase
import com.example.demoproject.domain.useCase.UpdateMovieUseCase
import com.example.demoproject.domain.useCase.UpdatePeopleUseCase

class ArtistViewModel(val getPeopleUseCase: GetPeopleUseCase,val updatePeopleUseCase: UpdatePeopleUseCase ): ViewModel() {


    fun getArtist() = liveData <List<People>>{
        val getPeople  = getPeopleUseCase.getPeople()
        emit(getPeople)
    }


    fun updateMovie() = liveData <List<People>>{
        val updatePeople  = updatePeopleUseCase.updatePeople()
        emit(updatePeople)
    }


}