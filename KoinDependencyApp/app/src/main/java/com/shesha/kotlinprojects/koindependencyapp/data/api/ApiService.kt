package com.shesha.kotlinprojects.koindependencyapp.data.api

import com.shesha.kotlinprojects.koindependencyapp.data.model.User
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

}