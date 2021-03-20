package com.example.pokemon.viewmodels


import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.example.pokemon.data.PokemonRepository


/**
 * The ViewModel for [GalleryFragment].
 * will inject the repository immediately when it's instantiated
 */
class PokemonGalleryViewModel  @ViewModelInject internal constructor(
    pokemonRepository: PokemonRepository
) : ViewModel() {

    val pokemons = pokemonRepository.getPokemons()
}



