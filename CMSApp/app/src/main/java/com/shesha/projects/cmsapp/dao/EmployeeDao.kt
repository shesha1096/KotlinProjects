package com.shesha.projects.cmsapp.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shesha.projects.cmsapp.model.Employee

class EmployeeDao {
    private val employeeList = arrayListOf<Employee>()
    private val employees = MutableLiveData<List<Employee>>()

    init {
        employees.value = employeeList
    }

    fun addEmployee(employee : Employee)
    {
        employeeList.add(employee)
        employees.value = employeeList
    }

    fun getEmployees () = employees as LiveData<List<Employee>>
}