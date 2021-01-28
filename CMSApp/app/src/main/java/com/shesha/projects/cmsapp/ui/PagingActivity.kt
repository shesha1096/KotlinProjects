package com.shesha.projects.cmsapp.ui

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.shesha.projects.cmsapp.R
import com.shesha.projects.cmsapp.adapters.PagingRecyclerAdapter
import com.shesha.projects.cmsapp.databinding.ActivityPagingBinding
import com.shesha.projects.cmsapp.model.ReqResModel
import com.shesha.projects.cmsapp.viewmodel.PagingViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PagingActivity : AppCompatActivity() {
    private lateinit var binding : ActivityPagingBinding
    private lateinit var pagingViewModel : PagingViewModel
    private lateinit var pagingRecyclerAdapter : PagingRecyclerAdapter
    private var userList : List<ReqResModel.Data> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_paging)
        pagingViewModel = PagingViewModel()
        binding.pagingViewModel = pagingViewModel
        binding.lifecycleOwner = this
        initializeUI()
    }

    private fun initializeUI()
    {
        pagingRecyclerAdapter = PagingRecyclerAdapter(this)
        binding.usersRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@PagingActivity)
            adapter = pagingRecyclerAdapter
        }
        lifecycleScope.launch {
            lifecycleScope.launch {
                pagingViewModel.listData.collect {
                    pagingRecyclerAdapter.submitData(it)
                }
            }

            }
        pagingRecyclerAdapter.addLoadStateListener {
            if (it.refresh == LoadState.Loading) {
                // show progress view
                binding.userProgressBar.visibility = View.VISIBLE
            } else {
                //hide progress view
                binding.userProgressBar.visibility = View.GONE
            }

        }
        }
    }
