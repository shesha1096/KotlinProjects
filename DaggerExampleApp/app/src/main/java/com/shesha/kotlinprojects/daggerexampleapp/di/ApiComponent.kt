package com.shesha.kotlinprojects.daggerexampleapp.di

import com.shesha.kotlinprojects.daggerexampleapp.MainActivity
import dagger.Component

import javax.inject.Singleton


@Singleton
@Component(modules = [ApiClientModule::class, AppModule::class])
interface ApiComponent {
    fun inject(mainActivity: MainActivity?)
}