package com.shesha.kotlinprojects.koindependencyapp.adapters

import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shesha.kotlinprojects.koindependencyapp.R
import com.shesha.kotlinprojects.koindependencyapp.data.model.User

class MainAdapter(
    private val users: ArrayList<User>
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var textViewUserName : TextView = itemView.findViewById(R.id.textViewUserName)
        private var textViewUserEmail : TextView = itemView.findViewById(R.id.textViewUserEmail)
        private var imageViewAvatar : ImageView = itemView.findViewById(R.id.imageViewAvatar)
        fun bind(user: User) {
            textViewUserName.text = user.name
            textViewUserEmail.text = user.email
            Glide.with(itemView.context)
                .load(user.avatar)
                .into(imageViewAvatar)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position])

    fun addData(list: List<User>) {
        users.addAll(list)
    }
}