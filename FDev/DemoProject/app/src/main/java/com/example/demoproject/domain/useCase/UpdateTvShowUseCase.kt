package com.example.demoproject.domain.useCase

import com.example.demoproject.data.model.tv.TvResult
import com.example.demoproject.domain.repository.TvShowRepository

class UpdateTvShowUseCase (val repository: TvShowRepository) {

    suspend fun updateTvShow():List<TvResult> = repository.updateTvShow()
}