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
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var employeeViewModel : EmployeeViewModel = EmployeeViewModel(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.employeeViewModel =employeeViewModel
        binding.lifecycleOwner = this

        employeeViewModel.getEmployees().observe(this, Observer<List<Employee>> { employees ->
            val stringBuilder = StringBuilder()
            employees.forEach { employee ->
                stringBuilder.append("$employee \n\n")
            }
            binding.employeeListId.text = stringBuilder.toString()
        })

    }
}