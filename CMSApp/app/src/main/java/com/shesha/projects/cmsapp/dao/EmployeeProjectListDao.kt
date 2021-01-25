package com.shesha.projects.cmsapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shesha.projects.cmsapp.model.EmployeeProjectList
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeProjectListDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addEntry(employeeProjectList : EmployeeProjectList)

    @Query("SELECT * FROM EmployeeProjectList WHERE employeeId = :employeeId")
    fun getProjectsByEmployeeId(employeeId : Int) : Flow<List<EmployeeProjectList>>

    @Query("SELECT * FROM EmployeeProjectList")
    fun getEntireList() : Flow<List<EmployeeProjectList>>

    @Query("SELECT * FROM EmployeeProjectList WHERE projectId = :projectId")
    fun getProjectsByProjectId(projectId : Int) : Flow<List<EmployeeProjectList>>
}