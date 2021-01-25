package com.shesha.projects.cmsapp.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shesha.projects.cmsapp.model.Employee
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addEmployee(employee : Employee)

    @Query("SELECT * FROM Employee")
    fun getEmployees() : Flow<List<Employee>>

}