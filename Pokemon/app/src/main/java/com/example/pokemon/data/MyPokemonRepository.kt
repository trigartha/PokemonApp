package com.example.pokemon.data

import androidx.lifecycle.LiveData
import com.example.pokemon.models.MyPokemon
import com.example.pokemon.models.Pokemon
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Repository for all [MyPokemon]
 * Encapsulates the logic for accessing data from a single source or a set of sources
 * determines how to fetch and store a particular set of data (locally or remote server)
 * is a singleton
 * @property myPokemonDao data access object
 */
@Singleton
class MyPokemonRepository @Inject constructor(
    private val myPokemonDao:MyPokemonDAO
){
    /**
     * Inserts a [MyPokemon] in the database by transforming [Pokemon]
     * @param pokemon [Pokemon] object
     */
    suspend fun createMyPokemon(pokemon: Pokemon) {
        val myPokemon = MyPokemon(pokemon.pokemonName, pokemon.sprites.frontDefault!!, Calendar.getInstance())
        myPokemonDao.insertMyPokemon(myPokemon)
    }

    /**
     * Gets all [MyPokemon] objects stored in a LiveData List
     */
    fun getAllMyPokemons():LiveData<List<MyPokemon>>{
        val myPokemons = myPokemonDao.getMyPokemons()
        return myPokemons
    }

    /**
     * Gets one [MyPokemon] object by name
     * @param pokemonName Name of the Pokémon
     */
    suspend fun getMyPokemon(pokemonName: String):MyPokemon{
        return myPokemonDao.getMyPokemon(pokemonName)
    }
}