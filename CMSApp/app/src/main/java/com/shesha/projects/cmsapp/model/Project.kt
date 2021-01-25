package com.shesha.projects.cmsapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class Project(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "project_id") val projectId : Int, val projectName : String, val projectDescription : String ) {
    override fun toString(): String {
        return "Project Name: $projectName, Description: $projectDescription"
    }
}