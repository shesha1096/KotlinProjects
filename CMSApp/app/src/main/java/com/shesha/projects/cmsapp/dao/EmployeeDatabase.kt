package com.shesha.projects.cmsapp.dao

class EmployeeDatabase private constructor(){
    var employeeDao = EmployeeDao()
        private set
    companion object{
    @Volatile private var instance : EmployeeDatabase ?= null
        fun getInstance() =
            instance ?: synchronized(this){
                instance ?: EmployeeDatabase().also { instance = it }
            }
    }
}