package com.shesha.projects.cmsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.shesha.projects.cmsapp.R
import com.shesha.projects.cmsapp.databinding.ActivityProjectListBinding
import com.shesha.projects.cmsapp.viewmodel.ProjectViewModel
import java.lang.StringBuilder

class ProjectListActivity : AppCompatActivity() {
    private lateinit var binding : ActivityProjectListBinding
    private lateinit var viewModel : ProjectViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_project_list)
        viewModel = ProjectViewModel(this@ProjectListActivity)
        binding.projectViewModel = viewModel
        binding.lifecycleOwner = this
        initializeUI()
    }

    private fun initializeUI()
    {
        viewModel.allProjects.observe(this,{projects ->
            val stringBuilder = StringBuilder()
            projects.forEach { project ->
                stringBuilder.append("$project \n\n")
            }
            binding.projectListTextId.text = stringBuilder.toString()
        })
    }
}