package com.shesha.kotlinprojects.daggerexampleapp.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiServiceBuilder {
    private var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {
        val client = OkHttpClient.Builder().build()
        retrofit = Retrofit.Builder()
            .baseUrl("https://dweebsglobal.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        return retrofit
    }
}