package com.shesha.projects.cmsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.shesha.projects.cmsapp.R
import com.shesha.projects.cmsapp.databinding.ActivityMainBinding
import com.shesha.projects.cmsapp.model.Employee
import com.shesha.projects.cmsapp.viewmodel.EmployeeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var employeeViewModel : EmployeeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        employeeViewModel  = EmployeeViewModel(this@MainActivity)
        binding.employeeViewModel =employeeViewModel
        binding.lifecycleOwner = this
        initializeUI()


    }
    fun initializeUI()
    {
        employeeViewModel.allEmployees.observe(this, Observer<List<Employee>> { employees ->
            val stringBuilder = StringBuilder()
            employees.forEach { employee ->
                stringBuilder.append("$employee \n\n")
            }
            binding.employeeListId.text = stringBuilder.toString()
        })
    }
}