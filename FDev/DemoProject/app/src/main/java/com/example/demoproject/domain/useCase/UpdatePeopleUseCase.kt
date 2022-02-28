package com.example.demoproject.domain.useCase

import com.example.demoproject.data.model.people.People
import com.example.demoproject.domain.repository.PeopleRepository

class UpdatePeopleUseCase(val repository: PeopleRepository) {

    suspend fun updatePeople():List<People> = repository.updatePopularPeople()
}