package com.shesha.projects.cmsapp.network

import com.shesha.projects.cmsapp.model.ReqResModel
import com.shesha.projects.cmsapp.model.Traveller
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TravelerEndPoints {

    @GET("/v1/passenger")
    suspend fun getUsers(@Query("page") page : Int = 1,@Query(value = "size") size: Int = 10) : Response<Traveller>

}