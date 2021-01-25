package com.shesha.projects.cmsapp.viewmodel

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.shesha.projects.cmsapp.dao.EmployeeDatabase
import com.shesha.projects.cmsapp.model.Employee
import com.shesha.projects.cmsapp.repository.EmployeeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class EmployeeViewModel(private val context : Context) : ViewModel()
{
    var employeeRepository : EmployeeRepository = EmployeeRepository.getInstance(EmployeeDatabase.invoke(context).getEmployeeDao())
    var employeeFirstName : String ?= null
    var employeeLastName : String ?= null
    var employeeDepartment : String ?= null

    val allEmployees : LiveData<List<Employee>> = employeeRepository.allEmployees.asLiveData()

    suspend fun addEmployee(employee : Employee)
    {
        employeeRepository.addEmployee(employee)
    }

    fun onAddEmployeeClicked(view :View)
    {
        if (employeeFirstName.isNullOrEmpty() || employeeLastName.isNullOrEmpty() || employeeDepartment.isNullOrEmpty())
        {
            Toast.makeText(context,"Please provide all information",Toast.LENGTH_SHORT).show()
        }
        else
        {

            runBlocking {
                withContext(Dispatchers.IO)
                {
                    addEmployee(Employee(1,employeeFirstName.toString(),employeeLastName.toString(),employeeDepartment.toString()))
                }
            }

        }
    }



}