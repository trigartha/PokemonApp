package com.example.pokemon.data

import com.example.pokemon.api.PokemonService
import com.example.pokemon.models.Pokemon
import com.example.pokemon.utilities.Resource
import javax.inject.Inject

/**
 * Creates an ApiRepository with an injected private value pokemonService
 * Dagger passes this object from the [NetworkModule] by the inject annotation
 * injected objects can not be modified and can only be objects of our own written classes
 */
class PokemonRemote @Inject constructor(
    private val pokemonService: PokemonService) : BaseDataResult(){

    /**
     * Calls the apiService and wraps the [NamedApiResourceList] in a [Response] object
     */
    suspend fun getNamedApiResourceList() = getResult{
         pokemonService.getPokemonList(0,100)}

    /**
     * Calls the apiService and wraps the [Pokemon] in a [Response] object
     */
    suspend fun getPokemon(pokemonName:String) = getResult{
        pokemonService.getPokemon(pokemonName) }

    /**
     * Calls the apiService and wraps the [PokemonSpecies] in a [Response] object
     */
    suspend fun getPokemonSpecies(pokemonName: String)=getResult {
        pokemonService.getPokemonSpecies(pokemonName) }

    /**
     * Loads all [Pokemon] objects by the [NamedApiResourceList]
     */
    suspend fun getPokemons():Resource<List<Pokemon>>{
        val pokemonNames = getNamedApiResourceList().data?.results
        try{
            var pokemons: List<Pokemon> = emptyList()
            if (pokemonNames != null) {
                for (item in pokemonNames){
                    if(item.name!=null || item.name !=""){
                        pokemons = pokemons +  pokemonService.getPokemonData(item.name)
                    }
                    else{
                        return error("no name at resourcelist")
                    }
                }
                return Resource.success(pokemons)
            }
            return error(" error loading pokemons")
        } catch (e: Exception) {
        return error(e.message ?: e.toString())
    }

    }
}
