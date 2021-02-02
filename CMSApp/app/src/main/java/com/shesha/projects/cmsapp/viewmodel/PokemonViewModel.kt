package com.shesha.projects.cmsapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.shesha.projects.cmsapp.datasource.PokemonDataSource
import com.shesha.projects.cmsapp.datasource.TravelerDataSource
import com.shesha.projects.cmsapp.model.Pokemon
import com.shesha.projects.cmsapp.model.Traveller
import kotlinx.coroutines.flow.Flow

class PokemonViewModel(private val context: Context) : ViewModel() {
    var listData : Flow<PagingData<Pokemon.Result>>?= null

    init {
        listData = Pager(PagingConfig(pageSize = 6))
        {
            PokemonDataSource(context)
        }.flow.cachedIn(viewModelScope)
    }


}