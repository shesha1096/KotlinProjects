package com.shesha.projects.cmsapp.repository

import androidx.annotation.WorkerThread
import com.shesha.projects.cmsapp.dao.EmployeeDao
import com.shesha.projects.cmsapp.model.Employee
import kotlinx.coroutines.flow.Flow

class EmployeeRepository private constructor(private val employeeDao : EmployeeDao){
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addEmployee(employee : Employee)
    {
        employeeDao.addEmployee(employee)
    }


    val allEmployees : Flow<List<Employee>> = employeeDao.getEmployees()

    companion object
    {
        @Volatile private var instance : EmployeeRepository ?= null

        fun getInstance (employeeDao : EmployeeDao) = instance ?: synchronized(this)
        {
            instance ?: EmployeeRepository(employeeDao).also { instance = it }
        }
    }
}