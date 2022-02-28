package com.example.demoproject

import com.example.demoproject.di.movie.MovieSubComponent
import com.example.demoproject.di.people.ArtistSubComponent
import com.example.demoproject.di.tv.TvSubComponent

interface Injector {

    fun createMovieSubComponent():MovieSubComponent
    fun createTvSubComponent():TvSubComponent
    fun createArtistSubComponent():ArtistSubComponent
}