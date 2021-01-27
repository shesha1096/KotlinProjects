package com.shesha.projects.cmsapp.viewmodel

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.shesha.projects.cmsapp.dao.EmployeeDatabase
import com.shesha.projects.cmsapp.model.Employee
import com.shesha.projects.cmsapp.repository.EmployeeRepository
import com.shesha.projects.cmsapp.ui.ProjectListActivity
import com.shesha.projects.cmsapp.ui.RetrofitExampleActivity
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
    var employeeId : Int = -1

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
            var employeeList : List<Employee>? = allEmployees.value
            if (employeeList != null) {
                if (employeeList.size == 0) {
                    employeeId = 1
                } else {
                    employeeId = (employeeList?.get(employeeList.size - 1)?.employeeId ?: -1) + 1
                }
            }

            runBlocking {
                withContext(Dispatchers.IO)
                {
                    addEmployee(Employee(employeeId,employeeFirstName.toString(),employeeLastName.toString(),employeeDepartment.toString()))
                }
            }

        }
    }

    fun onProjectListClicked(view: View)
    {
        var intent : Intent = Intent(context,ProjectListActivity::class.java)
        context.startActivity(intent)
    }

    fun onApiCallsClicked(view : View)
    {
        var intent : Intent = Intent(context,RetrofitExampleActivity::class.java)
        context.startActivity(intent)
    }



}