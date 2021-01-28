package com.shesha.projects.cmsapp.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shesha.projects.cmsapp.model.ReqResModel
import com.shesha.projects.cmsapp.network.ReqResEndPoints
import com.shesha.projects.cmsapp.service.ServiceBuilder

class UserDataSource :
    PagingSource<Int, ReqResModel.Data>() {
    override fun getRefreshKey(state: PagingState<Int, ReqResModel.Data>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ReqResModel.Data> {
        try {
            val currentLoadingPageKey = params.key ?: 1
            var request = ServiceBuilder.buildService(ReqResEndPoints::class.java)
            val response = request.getAllUsers(currentLoadingPageKey)
            val responseData = mutableListOf<ReqResModel.Data>()
            val data = response.body()?.data ?: emptyList()
            responseData.addAll(data)

            val prevKey = if (currentLoadingPageKey == 1) null else currentLoadingPageKey - 1

            return LoadResult.Page(
                data = responseData,
                prevKey = prevKey,
                nextKey = currentLoadingPageKey.plus(1)
            )
        } catch (e: Exception) {
        return LoadResult.Error(e)
    }
    }
}