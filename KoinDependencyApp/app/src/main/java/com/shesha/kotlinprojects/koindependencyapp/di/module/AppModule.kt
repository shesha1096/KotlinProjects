package com.shesha.kotlinprojects.koindependencyapp.di.module

import android.content.Context
import com.shesha.kotlinprojects.koindependencyapp.BuildConfig
import com.shesha.kotlinprojects.koindependencyapp.data.api.ApiHelper
import com.shesha.kotlinprojects.koindependencyapp.data.api.ApiHelperImpl
import com.shesha.kotlinprojects.koindependencyapp.data.api.ApiService
import com.shesha.kotlinprojects.koindependencyapp.main.helper.NetworkHelper
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import org.koin.dsl.module

val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get(), BuildConfig.BASE_URL) }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }

    single<ApiHelper> {
        return@single ApiHelperImpl(get())
    }
}
    private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

    private fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else OkHttpClient
        .Builder()
        .build()

    private fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build()

    private fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    private fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

