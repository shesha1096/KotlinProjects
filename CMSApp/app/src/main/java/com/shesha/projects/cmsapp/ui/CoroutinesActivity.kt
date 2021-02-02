package com.shesha.projects.cmsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.shesha.projects.cmsapp.R
import com.shesha.projects.cmsapp.databinding.ActivityCoroutinesBinding
import com.shesha.projects.cmsapp.viewmodel.CoroutineViewModel
import com.shesha.projects.cmsapp.viewmodel.EmployeeViewModel

class CoroutinesActivity : AppCompatActivity() {
    private lateinit var binding : ActivityCoroutinesBinding
    private lateinit var viewModel :CoroutineViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_coroutines)
        viewModel  = CoroutineViewModel(this@CoroutinesActivity)
        binding.coroutineViewModel = viewModel
        binding.lifecycleOwner = this
        initializeUI()
    }

    private fun initializeUI()
    {
        viewModel.dummyData.observe(this,{
            data ->
            binding.resultTv.text = data.toString()
        })
    }
}