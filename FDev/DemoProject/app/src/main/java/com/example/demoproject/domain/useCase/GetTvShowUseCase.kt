package com.example.demoproject.domain.useCase

import com.example.demoproject.data.model.tv.TvResult
import com.example.demoproject.domain.repository.TvShowRepository

class GetTvShowUseCase (val repository: TvShowRepository) {

    suspend fun getTvShow():List<TvResult> = repository.geTvShow()
}