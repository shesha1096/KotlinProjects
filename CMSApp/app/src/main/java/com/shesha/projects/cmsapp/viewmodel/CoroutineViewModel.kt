package com.shesha.projects.cmsapp.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

class CoroutineViewModel(private val context: Context) : ViewModel() {
    var dummyData : MutableLiveData<List<String>> = MutableLiveData<List<String>>()

    fun onClickMeClicked(view : View)
    {
        CoroutineScope(IO).launch {
            addData()
        }
    }

    private suspend fun addData()
    {
        val accumulateData : MutableList<String> = mutableListOf()
        for (i in 1..10)
        {
            accumulateData.add("Dummy Data: $i")
        }
        CoroutineScope(Main).launch {
            dummyData.value = accumulateData
        }

    }

}