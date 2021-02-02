package com.shesha.projects.cmsapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.shesha.projects.cmsapp.R
import com.shesha.projects.cmsapp.model.Pokemon
import com.shesha.projects.cmsapp.model.Traveller

class PokemonRecyclerAdapter(context: Context) : PagingDataAdapter<Pokemon.Result, RecyclerView.ViewHolder>(
    PokemonRecyclerAdapter.DataDifferentiator
) {

    class PokemonViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var pokemonNameView : TextView = view.findViewById(R.id.pokemon_name)
        var pokemonUrlView : TextView = view.findViewById(R.id.pokemon_url)
    }


    object DataDifferentiator : DiffUtil.ItemCallback<Pokemon.Result>() {
        override fun areItemsTheSame(oldItem: Pokemon.Result, newItem: Pokemon.Result): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Pokemon.Result, newItem: Pokemon.Result): Boolean {
            return oldItem == newItem
        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        var pokemonViewHolder : PokemonViewHolder = holder as PokemonViewHolder
        pokemonViewHolder.pokemonNameView.text = getItem(position)?.name ?: "Name"
        pokemonViewHolder.pokemonUrlView.text = getItem(position)?.url ?: "URL"

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PokemonViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.pokemon_card_item, parent, false)
        )
    }
}