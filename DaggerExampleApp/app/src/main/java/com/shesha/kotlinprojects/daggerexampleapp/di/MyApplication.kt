package com.shesha.kotlinprojects.daggerexampleapp.di

import android.app.Application

class MyApplication : Application() {
    var component: ApiComponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        component = DaggerApiComponent.builder()
            .appModule(AppModule(this))
            .apiClientModule(ApiClientModule("https://dweebsglobal.org/wp-json/wp/v2/pages"))
            .build()
    }

}