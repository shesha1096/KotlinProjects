package com.shesha.kotlinprojects.daggerexampleapp.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule(mApplication: Application) {
    private val mApplication: Application

    @get:Singleton
    @get:Provides
    val application: Application
        get() = mApplication

    init {
        this.mApplication = mApplication
    }
}