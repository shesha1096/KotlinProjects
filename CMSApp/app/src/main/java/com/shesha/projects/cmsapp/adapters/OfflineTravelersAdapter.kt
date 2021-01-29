package com.shesha.projects.cmsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shesha.projects.cmsapp.R
import com.shesha.projects.cmsapp.model.TravellerDatabaseInstance

class OfflineTravelersAdapter(travelersList : List<TravellerDatabaseInstance>, context: Context) :
    RecyclerView.Adapter<OfflineTravelersAdapter.TravelerViewHolder>(){

    private lateinit var travelersList : List<TravellerDatabaseInstance>
    private lateinit var context: Context
    init {
        this.travelersList = travelersList
        this.context = context
    }

    class TravelerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        var userFirstName : TextView = itemView.findViewById(R.id.user_first_name)
        var userTrips : TextView = itemView.findViewById(R.id.user_trips)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TravelerViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.traveler_card_item,parent,false)
        return TravelerViewHolder(view)
    }

    override fun onBindViewHolder(holder: TravelerViewHolder, position: Int) {
        holder.userFirstName.text = travelersList[position].travelerName
        holder.userTrips.text = travelersList[position].numTrips.toString()
    }

    override fun getItemCount(): Int {
        return travelersList.size
    }
}