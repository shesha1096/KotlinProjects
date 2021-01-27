package com.shesha.projects.cmsapp.network

import com.shesha.projects.cmsapp.model.ReqResModel
import com.shesha.projects.cmsapp.network.response.UserResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ReqResEndPoints {

    @GET("/api/users")
    fun getUsers(@Query("page") page : Int = 1) : Call<ReqResModel>

    @POST("/api/users")
    suspend fun addUser(@Body requestBody : RequestBody) : Response<UserResponse>
}