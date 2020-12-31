package com.shesha.projects.kotlinapplication.services

import com.shesha.projects.kotlinapplication.models.Country
import retrofit2.Call
import retrofit2.http.GET

interface CountryService {

    @GET("countries")
    fun getAffectedCountryList () : Call<List<Country>>
}