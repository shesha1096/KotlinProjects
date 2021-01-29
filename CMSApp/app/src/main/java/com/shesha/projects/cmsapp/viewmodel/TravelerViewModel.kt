package com.shesha.projects.cmsapp.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shesha.projects.cmsapp.dao.EmployeeDatabase
import com.shesha.projects.cmsapp.datasource.TravelerDataSource
import com.shesha.projects.cmsapp.datasource.UserDataSource
import com.shesha.projects.cmsapp.model.Traveller
import com.shesha.projects.cmsapp.model.TravellerDatabaseInstance
import com.shesha.projects.cmsapp.repository.TravelerRepository
import kotlinx.coroutines.flow.Flow

class TravelerViewModel(private val context : Context) : ViewModel() {
    var listData : Flow<PagingData<Traveller.Data>> ?= null
    var allTravellers : LiveData<List<TravellerDatabaseInstance>> = MutableLiveData<List<TravellerDatabaseInstance>>()
    init {
        if (isNetworkAvailable(context))
        {
            getDataFromNetwork()
        }
        else
        {
            var travelerRepository : TravelerRepository = TravelerRepository.getInstance(EmployeeDatabase.invoke(context).getTravelerDao())
            allTravellers = travelerRepository.allTravellers.asLiveData()
        }
    }

    private fun getDataFromNetwork()
    {
        listData = Pager(PagingConfig(pageSize = 6))
        {
            TravelerDataSource(context)
        }.flow.cachedIn(viewModelScope)
    }


    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                return true
            }
        }
        return false
    }
}