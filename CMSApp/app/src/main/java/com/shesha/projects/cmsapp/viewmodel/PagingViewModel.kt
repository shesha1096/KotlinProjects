package com.shesha.projects.cmsapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.shesha.projects.cmsapp.datasource.UserDataSource
import com.shesha.projects.cmsapp.service.ServiceBuilder

class PagingViewModel() : ViewModel() {

    val listData = Pager(PagingConfig(pageSize = 6))
    {
        UserDataSource()
    }.flow.cachedIn(viewModelScope)
}