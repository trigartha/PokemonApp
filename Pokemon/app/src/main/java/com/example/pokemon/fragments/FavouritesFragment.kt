package com.example.pokemon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.pokemon.adapters.MyPokemonAdapter
import com.example.pokemon.databinding.FragmentMypokemonsBinding
import com.example.pokemon.utilities.autoCleared
import com.example.pokemon.viewmodels.FavouritesViewModel
import dagger.hilt.android.AndroidEntryPoint


/**
 * Creates a [FavouritesFragment]
 * Annotation because they need to be able to be injected
 * since we need the viewmodel in the fragment and the fragment inside the activity
 */
@AndroidEntryPoint
class FavouritesFragment: Fragment() {
    private var binding : FragmentMypokemonsBinding by autoCleared()
    private val viewModel: FavouritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypokemonsBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = MyPokemonAdapter()
        binding.mypokemonList.adapter = adapter

        updateUi(adapter)
        return binding.root
    }
    /**
     * Updates the view when the observers notice a change in the data
     */
    private fun updateUi(adapter: MyPokemonAdapter) {
        viewModel.pokemons.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) adapter.submitList(ArrayList(it))
        })
    }
}