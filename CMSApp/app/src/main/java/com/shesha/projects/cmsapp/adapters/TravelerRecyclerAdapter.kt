package com.shesha.projects.cmsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shesha.projects.cmsapp.R
import com.shesha.projects.cmsapp.model.ReqResModel
import com.shesha.projects.cmsapp.model.Traveller

class TravelerRecyclerAdapter(context : Context) :  PagingDataAdapter<Traveller.Data, TravelerRecyclerAdapter.ViewHolder>(DataDifferentiator)  {


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var userFirstName : TextView = itemView.findViewById(R.id.user_first_name)
        var userTrips : TextView = itemView.findViewById(R.id.user_trips)
    }

    object DataDifferentiator : DiffUtil.ItemCallback<Traveller.Data>() {
        override fun areItemsTheSame(oldItem: Traveller.Data, newItem: Traveller.Data): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Traveller.Data, newItem: Traveller.Data): Boolean {
            return oldItem == newItem
        }


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userFirstName.text = getItem(position)?.name ?: "Name"
        holder.userTrips.text = getItem(position)?.trips.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.traveler_card_item, parent, false)
        )
    }
}