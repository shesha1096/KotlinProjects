package com.shesha.kotlinprojects.koindependencyapp.data.api

import com.shesha.kotlinprojects.koindependencyapp.data.model.User
import retrofit2.Response

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getUsers(): Response<List<User>> = apiService.getUsers()

}