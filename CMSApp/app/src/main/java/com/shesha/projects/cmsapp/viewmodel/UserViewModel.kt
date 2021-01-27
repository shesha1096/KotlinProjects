package com.shesha.projects.cmsapp.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shesha.projects.cmsapp.model.ReqResModel
import com.shesha.projects.cmsapp.model.User
import com.shesha.projects.cmsapp.network.ReqResEndPoints
import com.shesha.projects.cmsapp.repository.UserRepository
import com.shesha.projects.cmsapp.service.ServiceBuilder

class UserViewModel(private val context : Context) : ViewModel() {
    var userName : String ?= null
    var userJob : String ?= null
    var result : Boolean ?= null
    var resultData : MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)
    var userRepository : UserRepository = UserRepository()
    init {
        getUsers()
    }

    fun onAddUserClicked(view : View)
    {
         result = userRepository.addUser(User(userName.toString(),userJob.toString()))
        resultData.value = result
    }

    fun getUsers() : LiveData<List<ReqResModel.Data>>
    {
        return userRepository.getUsers()
    }

}