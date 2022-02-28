package com.example.demoproject.domain.useCase

import com.example.demoproject.data.model.people.People
import com.example.demoproject.domain.repository.PeopleRepository

class GetPeopleUseCase(val repository: PeopleRepository) {

    suspend fun getPeople():List<People> = repository.getPopularPeople()
}