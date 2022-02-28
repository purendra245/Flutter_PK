package com.example.demoproject.di.movie

import com.example.demoproject.di.people.ArtistModule
import com.example.demoproject.di.people.ArtistSubComponent
import com.example.demoproject.di.tv.TvSubComponent
import com.example.demoproject.view.MovieActivity
import dagger.Component
import dagger.Subcomponent

@MovieScope
@Subcomponent(modules = [MovieModule::class])
interface MovieSubComponent {

    fun inject(movieActivity: MovieActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create():MovieSubComponent
    }
}