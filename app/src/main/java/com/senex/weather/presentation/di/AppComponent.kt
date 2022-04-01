package com.senex.weather.presentation.di

import android.app.Application
import com.senex.weather.presentation.WeatherApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ContextModule::class,
    RepositoryModule::class,
    UseCaseModule::class,
])
interface AppComponent : AndroidInjector<WeatherApp> {

    override fun inject(instance: WeatherApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }
}