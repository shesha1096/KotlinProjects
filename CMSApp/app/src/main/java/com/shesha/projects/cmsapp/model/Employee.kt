package com.shesha.projects.cmsapp.model

data class Employee(val employeeId: Int, val employeeFirstName : String, val employeeLastName : String, val department : String) {
    override fun toString(): String {
        return "ID: $employeeId, First Name: $employeeFirstName, Last Name: $employeeLastName"
    }
}