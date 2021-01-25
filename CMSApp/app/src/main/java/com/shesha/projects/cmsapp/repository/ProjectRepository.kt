package com.shesha.projects.cmsapp.repository

import androidx.annotation.WorkerThread
import com.shesha.projects.cmsapp.dao.EmployeeDao
import com.shesha.projects.cmsapp.dao.ProjectDao
import com.shesha.projects.cmsapp.model.Project
import kotlinx.coroutines.flow.Flow

class ProjectRepository private constructor(private val projectDao : ProjectDao){

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun addProject(project : Project)
    {
        projectDao.addProject(project)
    }

    val allProjects : Flow<List<Project>> = projectDao.getProjects()

    companion object
    {
        @Volatile private var instance : ProjectRepository ?= null

        fun getInstance (projectDao: ProjectDao) = instance ?: synchronized(this)
        {
            instance ?: ProjectRepository(projectDao).also { instance = it }
        }
    }
}