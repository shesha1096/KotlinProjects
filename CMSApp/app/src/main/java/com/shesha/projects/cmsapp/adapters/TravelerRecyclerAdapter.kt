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
import com.bumptech.glide.Glide
import com.shesha.projects.cmsapp.R
import com.shesha.projects.cmsapp.model.ReqResModel
import com.shesha.projects.cmsapp.model.Traveller
import com.shesha.projects.cmsapp.utils.Constant

class TravelerRecyclerAdapter(context : Context) :  PagingDataAdapter<Traveller.Data, RecyclerView.ViewHolder>(DataDifferentiator)  {

    private lateinit var context : Context
    private lateinit var imageUrlList : List<String>
    init {
        this.context = context
        this.imageUrlList = Constant.IMAGE_URL
    }


    class FirstViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var userFirstName : TextView = itemView.findViewById(R.id.user_first_name)
        var userTrips : TextView = itemView.findViewById(R.id.user_trips)
    }

    class TravelerViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var userFirstName : TextView = itemView.findViewById(R.id.user_first_name)
    }

    class TravelerImageViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var userFirstName : TextView = itemView.findViewById(R.id.user_first_name)
        var imageView : ImageView = itemView.findViewById(R.id.airline_image)
    }


    object DataDifferentiator : DiffUtil.ItemCallback<Traveller.Data>() {
        override fun areItemsTheSame(oldItem: Traveller.Data, newItem: Traveller.Data): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: Traveller.Data, newItem: Traveller.Data): Boolean {
            return oldItem == newItem
        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder.itemViewType == 0)
        {
            var firstViewHolder : FirstViewHolder = holder as FirstViewHolder
            firstViewHolder.userFirstName.text = getItem(position)?.name ?: "Name"
            firstViewHolder.userTrips.text = getItem(position)?.trips.toString()
        }
        else if (holder.itemViewType == 1)
        {
            var travelerViewHolder : TravelerViewHolder = holder as TravelerViewHolder
            travelerViewHolder.userFirstName.text = getItem(position)?.name ?: "Name"
        }
        else
        {
            var travelerImageViewHolder : TravelerImageViewHolder = holder as TravelerImageViewHolder
            travelerImageViewHolder.userFirstName.text = getItem(position)?.name ?: "Name"
            Glide.with(context).load(imageUrlList[position%5]).into(holder.imageView)
            //Glide.with(context).load(Constant.URL).into(holder.imageView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == 0)
        {
            return FirstViewHolder(
                    LayoutInflater
                            .from(parent.context)
                            .inflate(R.layout.traveler_card_item, parent, false)
            )
        }
        if (viewType == 1)
        {
            return TravelerViewHolder(
                    LayoutInflater
                            .from(parent.context)
                            .inflate(R.layout.traveler_second_card, parent, false)
            )

        }

        return TravelerImageViewHolder(
                LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.traveler_third_card_item, parent, false)
        )



    }

    override fun getItemViewType(position: Int): Int {
        if (position % 3 == 0)
        {
            return 0;
        }
        if (position % 3 == 1)
        {
            return 1;
        }
        return 2;
    }
}