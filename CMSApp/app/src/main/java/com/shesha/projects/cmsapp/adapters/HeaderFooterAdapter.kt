package com.shesha.projects.cmsapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shesha.projects.cmsapp.R
import java.util.zip.Inflater

class HeaderFooterAdapter() : LoadStateAdapter<HeaderFooterAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        Log.d("LOADING","Loading")
        if (loadState is LoadState.Loading) {
            //show progress view
            holder.progressBar.visibility = View.VISIBLE
        } else //hide the view
        {
            holder.progressBar.visibility = View.GONE

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.traveler_card_item,parent,false)
        return LoadStateViewHolder(
                view
        )
    }

    class LoadStateViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        var progressBar : ProgressBar = view.findViewById(R.id.user_progress_bar)
    }
}