package com.shesha.kotlinprojects.koindependencyapp.data.api

import com.shesha.kotlinprojects.koindependencyapp.data.model.User
import retrofit2.Response

interface ApiHelper {

    suspend fun getUsers(): Response<List<User>>
}