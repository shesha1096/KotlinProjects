package com.shesha.projects.cmsapp.model

import androidx.room.Entity

@Entity(primaryKeys = ["employeeId","projectId"])
data class EmployeeProjectList(val employeeId : Int, val projectId : Int) {
}