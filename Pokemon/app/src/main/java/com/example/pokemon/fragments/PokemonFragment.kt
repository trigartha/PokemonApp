/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.pokemon.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.pokemon.R
import com.example.pokemon.adapters.PokemonMoveAdapter
import com.example.pokemon.databinding.FragmentPokemonBinding
import com.example.pokemon.models.Pokemon
import com.example.pokemon.models.PokemonSpecies
import com.example.pokemon.utilities.Resource
import com.example.pokemon.utilities.autoCleared
import com.example.pokemon.viewmodels.PokemonViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


/**
 * Creates a [PokemonFragment]
 * Annotation because they need to be injected themselves
 * since we need the viewmodel in the fragment and the fragment inside the activity
 */
@AndroidEntryPoint
class PokemonFragment : Fragment() {

    private val args: PokemonFragmentArgs by navArgs()
    private var binding: FragmentPokemonBinding by autoCleared()
    @Inject
    lateinit var pokemonViewModelFactory: PokemonViewModel.AssistedFactory

    private val pokemonViewModel : PokemonViewModel by viewModels {
        PokemonViewModel.provideFactory(
            pokemonViewModelFactory,
            args.pokemonName
        )
    }
    /**
     * to inflate fragment's view and to return inflated view to the hosting activity
     * @param inlfater | layoutinflater
     * @param container | ViewGroup
     * both are necessary to inflate the layout
     * wiring up widgets happens here
     * @return the inflated view with wired widgets
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate<FragmentPokemonBinding>(inflater, R.layout.fragment_pokemon, container,false)
        binding.apply {
            viewModel = pokemonViewModel
            lifecycleOwner=viewLifecycleOwner

        }
        binding.setClickListener{
            binding.pokemon?.let{
                pokemonViewModel.addPokemonToInventory()
            }
        }
        return binding.root
    }

    /**
     * to update the widgets when the fragment is loaded
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PokemonMoveAdapter()
        binding.movesList.adapter = adapter
        updateUi(adapter)
    }

    /**
     * Binds a [Pokemon] object to the view
     */
    private fun bindPokemon(pokemon:Pokemon?){
    binding.pokemon= pokemon
    }
    /**
     * Binds a [PokemonSpecies] object to the view
     */
    private fun bindSpecies(pokemon: PokemonSpecies?){
        binding.pokemonSpecies= pokemon
    }

    /**
     * Updates the view when the observers notice a change in the data
     */
    private fun updateUi(adapter: PokemonMoveAdapter) {
        pokemonViewModel.pokemon.observe(viewLifecycleOwner, Observer { pokemon ->
            Log.i("Pokemon", "Poke")
            when (pokemon.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    bindPokemon(pokemon.data)
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), pokemon.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
        pokemonViewModel.pokemonSpecies.observe(viewLifecycleOwner, Observer {
            Log.i("Pokemon", "Spec")
            bindSpecies(it.data)
        })
        pokemonViewModel.pokemonWithMoves.observe(viewLifecycleOwner, Observer {
            Log.i("Pokemon", "Move")
            if (!it.moves.isNullOrEmpty()){
                adapter.submitList(it.moves)
            }
        })
    }


}