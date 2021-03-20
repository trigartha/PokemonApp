package com.example.pokemon.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.pokemon.R
import com.example.pokemon.databinding.FragmentSearchBinding
import com.example.pokemon.utilities.autoCleared
import com.example.pokemon.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search.*

/**
 * Creates a [SearchFragment]
 * Annotation because they need to be injected themselves
 * since we need the viewmodel in the fragment and the fragment inside the activity
 */
@AndroidEntryPoint
class SearchFragment: Fragment()  {

    private var binding : FragmentSearchBinding by autoCleared()
    private val searchViewModel : SearchViewModel by viewModels()

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
        binding= DataBindingUtil.inflate<FragmentSearchBinding>(inflater, R.layout.fragment_search, container,false)
        binding.apply {
            lifecycleOwner=viewLifecycleOwner
            viewmodel = searchViewModel
        }
        binding.setClickListener {
            searchViewModel.pokemonName?.let { pokemonName ->
                if(TextUtils.isEmpty(pokemonName.value)){
                    pokemon_name_search.error = getString(R.string.name_error)
                }
                else {
                    pokemon_name_search.error=null
                    navigateToPokemon(pokemonName.value!!, it)
                }
            }
        }
        return binding.root
    }

    /**
     * Navigates to a [PokemonFragment] with the data of a specific Pokémon
     * @param pokemonName the name of the Pokémon
     */
    private fun navigateToPokemon(pokemonName: String, view: View){
        val direction =
            ViewPagerFragmentDirections.actionViewPagerFragmentToPokemonFragment(
                pokemonName
            )
        view.findNavController().navigate(direction)
    }
}

