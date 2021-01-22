package com.shesha.projects.cmsapp.viewmodel

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.shesha.projects.cmsapp.dao.EmployeeDatabase
import com.shesha.projects.cmsapp.model.Employee
import com.shesha.projects.cmsapp.repository.EmployeeRepository

class EmployeeViewModel(private val context : Context) : ViewModel()
{
    var employeeRepository : EmployeeRepository = EmployeeRepository.getInstance(EmployeeDatabase.getInstance().employeeDao)
    var employeeFirstName : String ?= null
    var employeeLastName : String ?= null
    var employeeDepartment : String ?= null
    var idCounter : Int = 1

    fun getEmployees() = employeeRepository.getEmployees()

    fun addEmployee(employee : Employee)
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
            addEmployee(Employee(idCounter,employeeFirstName.toString(),employeeLastName.toString(),employeeDepartment.toString()))
            idCounter += 1
        }
    }

}