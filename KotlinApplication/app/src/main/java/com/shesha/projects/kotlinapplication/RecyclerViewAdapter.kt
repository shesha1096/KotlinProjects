package com.shesha.projects.kotlinapplication

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.lang.reflect.Modifier

class RecyclerViewAdapter (movieList : ArrayList<MovieModel>) : RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder>()
{
    var movieList = ArrayList<MovieModel>()

    init {
        this.movieList = movieList
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.card_view,parent,false)
        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.movieTitle.text = movieList.get(position).movieTitle
        holder.movieDescription.text = movieList.get(position).movieDescription
    }

    override fun getItemCount(): Int {
        Log.d("SIZE",movieList.size.toString())
        return movieList.size
    }

    class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var movieTitle : TextView = itemView.findViewById(R.id.movieTitle_cardView)
        var movieDescription : TextView = itemView.findViewById(R.id.movieDescription_cardView)
        init {

        }

    }
}