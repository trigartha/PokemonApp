package com.example.pokemon.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokemon.models.MyPokemon

/**
 * Data acces object for the [MyPokemon] object, used in favourites
 * contains a function for each operation on the database
 *
 * suspend keyword can not be used with Dao function return type is LiveData
 */
@Dao
interface MyPokemonDAO {
    /**
     * Gets a LiveData List of [MyPokemon] objects
     */
    @Query("SELECT * FROM my_pokemon")
    fun getMyPokemons(): LiveData<List<MyPokemon>>

    /**
     * Gets a specific [MyPokemon] object on
     * @param pokemonName
     */
    @Query("SELECT * FROM my_pokemon WHERE pokemonName=(:pokemonName)")
    suspend fun getMyPokemon(pokemonName:String):MyPokemon

    /**
     * Inserts one [MyPokemon] object
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMyPokemon(myPokemon: MyPokemon)


}