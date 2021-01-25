package com.shesha.projects.cmsapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.shesha.projects.cmsapp.model.Project
import kotlinx.coroutines.flow.Flow

@Dao
interface ProjectDao {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun addProject(project : Project)

    @Query("SELECT * FROM Project")
    fun getProjects() : Flow<List<Project>>
}