package com.shesha.projects.cmsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shesha.projects.cmsapp.R
import com.shesha.projects.cmsapp.adapters.HeaderFooterAdapter
import com.shesha.projects.cmsapp.adapters.OfflineTravelersAdapter
import com.shesha.projects.cmsapp.adapters.PagingRecyclerAdapter
import com.shesha.projects.cmsapp.adapters.TravelerRecyclerAdapter
import com.shesha.projects.cmsapp.databinding.ActivityPagingBinding
import com.shesha.projects.cmsapp.databinding.ActivityTravelerBinding
import com.shesha.projects.cmsapp.model.ReqResModel
import com.shesha.projects.cmsapp.model.Traveller
import com.shesha.projects.cmsapp.viewmodel.PagingViewModel
import com.shesha.projects.cmsapp.viewmodel.TravelerViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class TravelerActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTravelerBinding
    private lateinit var travelerViewModel : TravelerViewModel
    private lateinit var travelerRecyclerAdapter: TravelerRecyclerAdapter
    private var userList : List<Traveller.Data> = arrayListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_traveler)
        travelerViewModel = TravelerViewModel(this)
        binding.travelerViewModel = travelerViewModel
        binding.lifecycleOwner = this
        initializeUI()
    }

    private fun initializeUI() {

        travelerViewModel.allTravellers.observe(this,{travelers ->
            Log.d("TRAVELERS",travelers.toString())
            val offlineTravelersAdapter = OfflineTravelersAdapter(travelers,this)
            binding.usersRecyclerView.layoutManager = LinearLayoutManager(this)
            binding.usersRecyclerView.adapter = offlineTravelersAdapter
        })

        travelerRecyclerAdapter = TravelerRecyclerAdapter(this)
        binding.usersRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@TravelerActivity)
            adapter = travelerRecyclerAdapter
                lifecycleScope.launch {
                    travelerViewModel.listData?.collect {
                        travelerRecyclerAdapter.submitData(it)
                    }
                }
            adapter = travelerRecyclerAdapter.withLoadStateFooter(HeaderFooterAdapter())
            travelerRecyclerAdapter.addLoadStateListener {
                if (it.refresh == LoadState.Loading) {
                    // show progress view

                } else {
                    //hide progress view
                }

            }







        }

    }
}