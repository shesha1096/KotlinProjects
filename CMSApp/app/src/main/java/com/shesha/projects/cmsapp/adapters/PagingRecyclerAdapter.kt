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

class PagingRecyclerAdapter(context : Context) : PagingDataAdapter<ReqResModel.Data, PagingRecyclerAdapter.ViewHolder>(DataDifferentiator)  {
    private lateinit var context: Context
    init {
        this.context = context
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var userFirstName : TextView = itemView.findViewById(R.id.user_first_name)
        var userEmail : TextView = itemView.findViewById(R.id.user_email)
        var userAvatar : ImageView = itemView.findViewById(R.id.user_avatar)
    }

    object DataDifferentiator : DiffUtil.ItemCallback<ReqResModel.Data>() {

        override fun areItemsTheSame(oldItem: ReqResModel.Data, newItem: ReqResModel.Data): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ReqResModel.Data, newItem: ReqResModel.Data): Boolean {
            return oldItem == newItem
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.userFirstName.text = getItem(position)?.first_name
        holder.userEmail.text = getItem(position)?.email
        Glide.with(context).load(getItem(position)?.avatar).into(holder.userAvatar)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.user_card_item, parent, false)
        )
    }
}