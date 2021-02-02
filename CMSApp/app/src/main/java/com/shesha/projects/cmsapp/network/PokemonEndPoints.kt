package com.shesha.projects.cmsapp.network

import com.shesha.projects.cmsapp.model.Pokemon
import com.shesha.projects.cmsapp.model.Traveller
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonEndPoints {

    @GET("/api/v2/pokemon")
    suspend fun getPokemons(@Query("offset") limit : Int = 0, @Query(value = "limit") size: Int = 10) : Response<Pokemon>
}