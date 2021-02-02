package com.shesha.projects.cmsapp.datasource

import android.content.Context
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shesha.projects.cmsapp.dao.EmployeeDatabase
import com.shesha.projects.cmsapp.model.Pokemon
import com.shesha.projects.cmsapp.model.Traveller
import com.shesha.projects.cmsapp.model.TravellerDatabaseInstance
import com.shesha.projects.cmsapp.network.PokemonEndPoints
import com.shesha.projects.cmsapp.network.TravelerEndPoints
import com.shesha.projects.cmsapp.repository.TravelerRepository
import com.shesha.projects.cmsapp.service.PokemonServiceBuilder
import com.shesha.projects.cmsapp.service.TravelerServiceBuilder

class PokemonDataSource(context : Context) : PagingSource<Int, Pokemon.Result>() {
    private lateinit var context: Context
    init {
        this.context = context
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon.Result>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon.Result> {
        try {
            val currentLoadingPageKey = params.key ?: 0
            Log.d("PAGE_KEY",currentLoadingPageKey.toString())
            var request = PokemonServiceBuilder.buildService(PokemonEndPoints::class.java)
            val response = request.getPokemons(currentLoadingPageKey,10)
            val responseData = mutableListOf<Pokemon.Result>()
            val data = response.body()?.results ?: emptyList()
            responseData.addAll(data)
            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1
            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(10)
            )
        } catch (e: Exception) {
            Log.d("RESPONSE",e.toString())
            return LoadResult.Error(e)
        }
    }
}