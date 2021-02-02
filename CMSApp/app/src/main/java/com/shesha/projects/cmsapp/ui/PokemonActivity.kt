package com.shesha.projects.cmsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.shesha.projects.cmsapp.R
import com.shesha.projects.cmsapp.adapters.PokemonHeaderFooterAdapter
import com.shesha.projects.cmsapp.adapters.PokemonRecyclerAdapter
import com.shesha.projects.cmsapp.databinding.ActivityMainBinding
import com.shesha.projects.cmsapp.databinding.ActivityPokemonBinding
import com.shesha.projects.cmsapp.viewmodel.EmployeeViewModel
import com.shesha.projects.cmsapp.viewmodel.PokemonViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PokemonActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPokemonBinding
    private lateinit var pokemonViewModel: PokemonViewModel
    private lateinit var pokemonRecyclerAdapter: PokemonRecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_pokemon)
        pokemonViewModel  = PokemonViewModel(this@PokemonActivity)
        binding.pokemonViewModel = pokemonViewModel
        binding.lifecycleOwner = this
        initializeUI()
    }

    private fun initializeUI() {
        pokemonRecyclerAdapter = PokemonRecyclerAdapter(this)
        binding.pokemonRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@PokemonActivity)
            adapter = pokemonRecyclerAdapter
            lifecycleScope.launch {
                pokemonViewModel.listData?.collect {
                    pokemonRecyclerAdapter.submitData(it)
                }
                adapter = pokemonRecyclerAdapter.withLoadStateFooter(PokemonHeaderFooterAdapter())
            }
        }
    }
}