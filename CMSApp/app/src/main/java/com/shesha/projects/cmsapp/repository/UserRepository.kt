package com.shesha.projects.cmsapp.repository

import android.util.Log
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shesha.projects.cmsapp.model.ReqResModel
import com.shesha.projects.cmsapp.model.User
import com.shesha.projects.cmsapp.network.ReqResEndPoints
import com.shesha.projects.cmsapp.service.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {
    private var lisfOfUsers: MutableLiveData<List<ReqResModel.Data>> = MutableLiveData<List<ReqResModel.Data>>()
    private var usersList : MutableLiveData<List<ReqResModel.Data>> = MutableLiveData<List<ReqResModel.Data>>()
    private var result : Boolean = false
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun addUser(user : User) : Boolean
    {
        val jsonObject : JSONObject = JSONObject()
        jsonObject.put("name",user.name)
        jsonObject.put("job",user.job)
        val jsonObjectString = jsonObject.toString()
        val requestBody = jsonObjectString.toRequestBody("application/json".toMediaTypeOrNull())
        runBlocking {
            withContext(Dispatchers.IO)
            {
                result = addUserAPICall(user,jsonObjectString,requestBody)
            }
        }
        return result
    }

    suspend private fun addUserAPICall(user: User, jsonObjectString: String, requestBody: RequestBody)  : Boolean{
        val request = ServiceBuilder.buildService(ReqResEndPoints::class.java)
        val service = request.addUser(requestBody)
        if (service.isSuccessful)
        {
            return true
        }
        return false
    }

    fun getUsers() : LiveData<List<ReqResModel.Data>>
    {
        runBlocking {
            withContext(Dispatchers.IO)
            {
               lisfOfUsers = makeApiCallForUsers()
            }
        }
        return lisfOfUsers
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun makeApiCallForUsers() : MutableLiveData<List<ReqResModel.Data>>
    {
        val request = ServiceBuilder.buildService(ReqResEndPoints::class.java)
        val call = request.getUsers()
        call.enqueue(object : Callback<ReqResModel> {
            override fun onResponse(call: Call<ReqResModel>, response: Response<ReqResModel>) {
                if (response.isSuccessful){
                    var reqResModel : ReqResModel? = response.body()
                    var data : List<ReqResModel.Data> = (reqResModel?.data ?: null) as List<ReqResModel.Data>
                    usersList.value = data
                }
            }
            override fun onFailure(call: Call<ReqResModel>, t: Throwable) {
                Log.d("EXCEPTION","exception")

            }
        })
        return usersList

    }
}