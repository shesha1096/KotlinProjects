package com.shesha.projects.cmsapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Employee(@PrimaryKey(autoGenerate = true) @ColumnInfo(name="id") val employeeId : Int,val employeeFirstName : String, val employeeLastName : String, val department : String) {

    override fun toString(): String {
        return "First Name: $employeeFirstName, Last Name: $employeeLastName, Department: $department"
    }
}