package com.shesha.kotlinprojects.koindependencyapp

import android.app.Application
import com.shesha.kotlinprojects.koindependencyapp.di.module.appModule
import com.shesha.kotlinprojects.koindependencyapp.di.module.repoModule
import com.shesha.kotlinprojects.koindependencyapp.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application()
{
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, repoModule, viewModelModule))
        }
    }
}

