
package com.example.pokemon.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.pokemon.data.MyPokemonRepository

/**
 * The ViewModel used in [FavouritesFragment].
 */
class FavouritesViewModel @ViewModelInject internal constructor(
    myPokemonRepository: MyPokemonRepository
) : ViewModel() {

    val pokemons = myPokemonRepository.getAllMyPokemons()
}