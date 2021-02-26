package com.shesha.kotlinprojects.daggerexampleapp.network

import retrofit2.Call
import retrofit2.http.GET

interface PageEndPoints {
    @get:GET("/wp-json/wp/v2/pages")
    val pages: Call<List<PageResponse?>?>?
}