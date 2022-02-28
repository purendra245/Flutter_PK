package com.example.demoproject.di.tv

import com.example.demoproject.view.MovieActivity
import com.example.demoproject.view.TvActivity
import dagger.Component
import dagger.Subcomponent

@TvScope
@Subcomponent(modules = [TvModule::class])
interface TvSubComponent {

    fun inject(tvActivity: TvActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create():TvSubComponent
    }
}