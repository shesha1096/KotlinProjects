package com.shesha.projects.cmsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shesha.projects.cmsapp.R
import com.shesha.projects.cmsapp.model.ReqResModel

class UsersRecyclerAdapter ( userList : List<ReqResModel.Data>, context : Context) :
    RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder>() {

    private var userList = arrayListOf<ReqResModel.Data>()
    private lateinit var context: Context
    init {
        this.userList = userList as ArrayList<ReqResModel.Data>
        this.context = context

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.user_card_item,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.userFirstName.text = userList[position].first_name
        holder.userEmail.text = userList[position].email
        Glide.with(context).load(userList[position].avatar).into(holder.userAvatar)
    }

    override fun getItemCount(): Int{
        return userList.size
    }

    class UserViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)
    {
        var userFirstName : TextView = itemView.findViewById(R.id.user_first_name)
        var userEmail : TextView = itemView.findViewById(R.id.user_email)
        var userAvatar : ImageView = itemView.findViewById(R.id.user_avatar)

    }
}