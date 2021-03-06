package com.shesha.projects.cmsapp.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shesha.projects.cmsapp.R

class PokemonHeaderFooterAdapter() : LoadStateAdapter<PokemonHeaderFooterAdapter.LoadStateViewHolder>() {

    class LoadStateViewHolder(view: View) : RecyclerView.ViewHolder(view)
    {
        var linearLayout : LinearLayout = view.findViewById(R.id.user_progress_bar)
        var relativeLayout : RelativeLayout = view.findViewById(R.id.card_pokemon)
    }

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        Log.d("LOAD_STATE",loadState.toString())
        if (loadState is LoadState.Loading) {
            //show progress view
            holder.linearLayout.visibility = View.VISIBLE
            holder.relativeLayout.visibility = View.GONE
        } else //hide the view
        {
            holder.linearLayout.visibility = View.GONE
            holder.relativeLayout.visibility = View.VISIBLE

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        var view : View = LayoutInflater.from(parent.context).inflate(R.layout.pokemon_card_item,parent,false)
        return LoadStateViewHolder(
            view
        )
    }
}