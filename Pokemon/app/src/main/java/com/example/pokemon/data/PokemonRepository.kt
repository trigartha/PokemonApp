package com.example.pokemon.data


import androidx.lifecycle.LiveData
import com.example.pokemon.utilities.performGetOperation
import javax.inject.Inject
import javax.inject.Singleton
import com.example.pokemon.models.PokemonWithMoves

//repository pattern
//repository encapsulates the logic for accesing data from the source(s)
//determines how to fetch, store and where to store
//singleton: exists as long as app stays in memory (not for longterm data)
//two functions companion object: initialize and get repository
//private constructor to avoid rogue components
/**
 * Repository for all pokemons
 * Encapsulates the logic for accessing data from a single source or a set of sources
 * determines how to fetch and store a particular set of data (locally or remote server)
 * is a singleton
 * @property localPokemonDAO data access object
 * @property remotePokemonSource network acces object
 */
@Singleton
class PokemonRepository @Inject constructor(
    private val remotePokemonSource: PokemonRemote,
    private val localPokemonDAO:PokemonDAO
){

    /**
     * Add a corresponding function to resources for each function we need to perform
     */

    /**
     * Gets a List of [Pokemon] objects wrapped in a [Resource] object as [LiveData]
     */
    fun getPokemons() = performGetOperation(
        databaseQuery = {localPokemonDAO.getAllPokemons()},
        networkCall = { remotePokemonSource.getPokemons()},
        saveCallResult =  { localPokemonDAO.insertAll(it) }
    )

    /**
     * Gets a [Pokemon] object wrapped in a [Resource] object as [LiveData]
     * @param pokemonName the name of the [Pokemon] object
     */
    fun getPokemon(pokemonName: String) = performGetOperation(
        databaseQuery = {localPokemonDAO.getPokemon(pokemonName)},
        networkCall = {remotePokemonSource.getPokemon(pokemonName)},
        saveCallResult = { localPokemonDAO.insertPokemon(it)}
    )
    /**
     * Gets a [PokemonWithMoves] object as [LiveData]
     * @param pokemonName the name of the [Pokemon] object
     */
    fun getPokemonWithMoves(pokemonName: String): LiveData<PokemonWithMoves>{
        return localPokemonDAO.getPokemonWithMoves(pokemonName)
    }

    /**
     * Gets a [PokemonSpecies] object wrapped in a [Resource] object as [LiveData]
     * @param pokemonName the name of the [PokemonSpecies] object
     */
    fun getPokemonSpecies(pokemonName: String) = performGetOperation(
        databaseQuery = {localPokemonDAO.getPokemonSpecies(pokemonName)},
        networkCall = {remotePokemonSource.getPokemonSpecies(pokemonName)},
        saveCallResult = {localPokemonDAO.insertSpecies(it)}
    )
}