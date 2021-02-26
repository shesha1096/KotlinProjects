package com.shesha.kotlinprojects.koindependencyapp.data.repository

import com.shesha.kotlinprojects.koindependencyapp.data.api.ApiHelper

class MainRepository (private val apiHelper: ApiHelper) {

    suspend fun getUsers() =  apiHelper.getUsers()

}