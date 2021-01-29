package com.shesha.projects.cmsapp.datasource

import android.content.Context
import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shesha.projects.cmsapp.dao.EmployeeDatabase
import com.shesha.projects.cmsapp.model.Traveller
import com.shesha.projects.cmsapp.model.TravellerDatabaseInstance
import com.shesha.projects.cmsapp.network.TravelerEndPoints
import com.shesha.projects.cmsapp.repository.TravelerRepository
import com.shesha.projects.cmsapp.service.TravelerServiceBuilder

class TravelerDataSource(context: Context) : PagingSource<Int, Traveller.Data>() {
    private lateinit var context : Context
    init {
        this.context = context
    }
    private lateinit var travelerList : MutableList<TravellerDatabaseInstance>
    override fun getRefreshKey(state: PagingState<Int, Traveller.Data>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Traveller.Data> {
        travelerList = ArrayList()
        try {
            val currentLoadingPageKey = params.key ?: 1
            var request = TravelerServiceBuilder.buildService(TravelerEndPoints::class.java)
            val response = request.getUsers(1,10)
            val responseData = mutableListOf<Traveller.Data>()
            val data = response.body()?.data ?: emptyList()
            Log.d("RESPONSE",data.toString())
            responseData.addAll(data)
            for (i in data)
            {
                travelerList.add(TravellerDatabaseInstance(i.name,i.trips))
            }
            var travelerRepository : TravelerRepository = TravelerRepository.getInstance(
                EmployeeDatabase.invoke(context).getTravelerDao())
            travelerRepository.addAllTravelers(travelerList)
            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
            Log.d("RESPONSE",e.toString())
            return LoadResult.Error(e)
        }
    }

}