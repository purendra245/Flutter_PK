package com.example.demoproject.di.core

import android.content.Context
import com.example.demoproject.di.movie.MovieModule
import com.example.demoproject.di.movie.MovieSubComponent
import com.example.demoproject.di.people.ArtistSubComponent
import com.example.demoproject.di.tv.TvSubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents  = [MovieSubComponent::class, TvSubComponent::class, ArtistSubComponent::class])
class AppModule (private val context: Context){

    @Singleton
    @Provides
    fun getApplicationContext(): Context {
        return context.applicationContext
    }
}