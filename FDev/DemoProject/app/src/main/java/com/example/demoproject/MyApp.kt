package com.example.demoproject

import android.app.Application
import com.example.demoproject.constant.AppConstant
import com.example.demoproject.di.core.*
import com.example.demoproject.di.movie.MovieSubComponent
import com.example.demoproject.di.people.ArtistSubComponent
import com.example.demoproject.di.tv.TvSubComponent

class MyApp :Application(),Injector{

    private lateinit var appComponent: AppComponent
    val BASE_URL="https://api.themoviedb.org/3/"


    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .apiInstance(ApiInstance(BASE_URL))
            .remoteDataSourceModule(RemoteDataSourceModule(AppConstant.API_KEY))
            .build()
    }

    override fun createMovieSubComponent(): MovieSubComponent {
       return  appComponent.getMovieSubComponent().create()
    }

    override fun createTvSubComponent(): TvSubComponent {
        return  appComponent.getTvSubComponent().create()
    }

    override fun createArtistSubComponent(): ArtistSubComponent {
        return  appComponent.getPeopleSubComponent().create()
    }
}