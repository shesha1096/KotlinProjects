package com.shesha.projects.cmsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.shesha.projects.cmsapp.R
import com.shesha.projects.cmsapp.adapters.UsersRecyclerAdapter
import com.shesha.projects.cmsapp.databinding.ActivityMainBinding
import com.shesha.projects.cmsapp.databinding.ActivityRetrofitExampleBinding
import com.shesha.projects.cmsapp.model.ReqResModel
import com.shesha.projects.cmsapp.viewmodel.EmployeeViewModel
import com.shesha.projects.cmsapp.viewmodel.UserViewModel

class RetrofitExampleActivity : AppCompatActivity() {
    private lateinit var binding : ActivityRetrofitExampleBinding
    private lateinit var userViewModel: UserViewModel
    private var userList : List<ReqResModel.Data> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_retrofit_example)
        userViewModel  = UserViewModel(this@RetrofitExampleActivity)
        binding.userViewModel = userViewModel
        binding.lifecycleOwner = this
        initializeUI()
        userViewModel.resultData.observe(this, {result ->
            if (result)
            {
                Toast.makeText(this,"User Added",Toast.LENGTH_SHORT).show()
            }
            else
            {
                Toast.makeText(this,"Oops! Something went wrong.",Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun initializeUI()
    {
        userViewModel.getUsers().observe(this, {users ->
            userList = users
            var usersRecyclerAdapter : UsersRecyclerAdapter = UsersRecyclerAdapter(userList,this)
            binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
            binding.usersRecyclerView.adapter = usersRecyclerAdapter
            Log.d("USERS",userList.toString())
        })
    }
}