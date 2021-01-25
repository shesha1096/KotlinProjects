package com.shesha.projects.cmsapp.viewmodel

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.shesha.projects.cmsapp.dao.EmployeeDatabase
import com.shesha.projects.cmsapp.model.Project
import com.shesha.projects.cmsapp.repository.ProjectRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ProjectViewModel(private val context : Context) : ViewModel() {
    var projectName : String ?= null
    var projectDescription : String ?= null
    var projectRepository : ProjectRepository = ProjectRepository.getInstance(EmployeeDatabase.invoke(context).getProjectDao())
    var projectList : List<Project>? = projectRepository.allProjects.asLiveData().value
    var projectId = -1

    var allProjects : LiveData<List<Project>> = projectRepository.allProjects.asLiveData()

     suspend fun addProject(project : Project)
    {
        projectRepository.addProject(project)
    }

    fun onAddProjectDetailsClicked(view : View)
    {
        if (projectName.isNullOrEmpty() || projectDescription.isNullOrEmpty())
        {
            Toast.makeText(context,"Please provide complete details",Toast.LENGTH_SHORT).show()
        }
        else
        {
            if (projectList != null)
            {
                if (projectList!!.size == 0)
                {
                    projectId = 1
                }
                else
                {
                    projectId = projectList!![projectList!!.size - 1].projectId + 1
                }
            }
            runBlocking {
                withContext(Dispatchers.IO)
                {
                    addProject(Project(projectId,projectName.toString(),projectDescription.toString()))
                }
            }
        }
    }

}