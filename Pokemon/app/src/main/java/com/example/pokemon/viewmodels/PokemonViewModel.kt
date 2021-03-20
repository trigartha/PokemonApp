package com.example.pokemon.viewmodels

import androidx.lifecycle.*
import com.example.pokemon.data.MyPokemonRepository
import com.example.pokemon.data.PokemonRepository
import com.example.pokemon.models.Pokemon
import com.example.pokemon.utilities.Resource
import com.squareup.inject.assisted.Assisted
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.launch

/**
 * The ViewModel used in [PokemonFragment].
 */
class PokemonViewModel @AssistedInject constructor(
    pokemonRepository: PokemonRepository,
    private val myPokemonRepository: MyPokemonRepository,
    @Assisted private val pokemonName: String
) : ViewModel() {

    val pokemon : LiveData<Resource<Pokemon>> = pokemonRepository.getPokemon(pokemonName)
    val pokemonWithMoves = pokemonRepository.getPokemonWithMoves(pokemonName)
    val pokemonSpecies = pokemonRepository.getPokemonSpecies(pokemonName)

    /**
     * Adds a [MyPokemon] object to the database
     */
    fun addPokemonToInventory() {
        viewModelScope.launch {
            pokemon.value?.data?.let { myPokemonRepository.createMyPokemon(it) }
        }
    }

    /**
     * Interface for Factory that creates [PokemonViewModel]
     */
    @AssistedInject.Factory
    interface AssistedFactory {
        fun create(pokemonName: String): PokemonViewModel
    }

    /**
     * instantiates the [PokemonViewModel] by a Factory
     * set's the name of the current pokemon viewed
     */
    companion object {
        fun provideFactory(
            assistedFactory: AssistedFactory,
            pokemonName: String
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return assistedFactory.create(pokemonName) as T
            }
        }
    }
}