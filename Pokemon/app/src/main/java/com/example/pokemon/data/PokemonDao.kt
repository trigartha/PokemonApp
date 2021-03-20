package com.example.pokemon.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.pokemon.models.*


/**
 * Data acces object for a [Pokemon] object
 * contains a function for each operation on the database
 */
@Dao
interface PokemonDAO {
    /**
     * Gets a LiveData List of [Pokemon] objects
     */
    @Query("SELECT * FROM pokemon")
    fun getAllPokemons():LiveData<List<Pokemon>>

    /**
     * Gets a LiveData [Pokemon] object
     * @param pokemonName the name of the [Pokemon] object
     */
    @Query("SELECT * FROM pokemon WHERE pokemonName=(:pokemonName)")
    fun getPokemon(pokemonName:String):LiveData<Pokemon>

    /**
     * Gets a LiveData [PokemonSpecies] object
     * @param pokemonName the name of the [PokemonSpecies] object
     */
    @Query("SELECT * FROM pokemon_species WHERE pokemonName=(:pokemonName)")
    fun getPokemonSpecies(pokemonName: String):LiveData<PokemonSpecies>

    /**
     * Gets a [Move] object
     * @param moveName the name of the [Move] object
     */
    @Query("SELECT * FROM move WHERE name=(:moveName)")
    fun getMove(moveName:String):Move

    /**
     * Gets a LiveData [PokemonWithMoves] object
     * @param pokemonName the name of the [PokemonWithMoves] object
     */
    @Transaction
    @Query("SELECT * FROM pokemon WHERE pokemonName=(:pokemonName)")
    fun getPokemonWithMoves(pokemonName: String): LiveData<PokemonWithMoves>


    /**
     * on conflict says what to do if we try to insert an object with
     * the primary key and there is already a row in the database with that
     * primary key
     * Normally for a true insert that would fail
     * REPLACE keyword will overwrite what already is stored in the databse
     */

    /**
     * Insert a List of [Pokemon] objects
     * @param pokemons a List of [Pokemon] objects
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertAll(pokemons: List<Pokemon>)

    /**
     * Insert a [Pokemon] object
     * @param pokemon a [Pokemon] object
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(pokemon: Pokemon)

    /**
     * Insert a [PokemonSpecies] object
     * @param pokemon a [PokemonSpecies] object
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertSpecies (pokemon: PokemonSpecies)

    /**
     * Insert a [Move] object
     * @param move a [Move] object
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMove(move: Move)

    /**
     * Insert a [PokemonMove] object
     * @param pokemonMove a [PokemonMove] object
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPokemonMove(pokemonMove: PokemonMove)

    @Delete
    fun delete(pokemon: Pokemon)

    /**
     * Insert a List of [Move] objects
     * @param moves a List of [Move] objects
     */
    fun insertNewMoves(moves: List<Move>){
        for(move in moves){
                insertMove(move)
        }
    }

    /**
     * Insert a [Pokemon] object and it's List of [Move] objects
     * @param pokemon a [Pokemon] object
     */
    fun insertPokemon(pokemon: Pokemon){
            insertNewMoves(pokemon.moves)
            insert(pokemon)
                for(namedApiRss in pokemon.moves){
                    val move = getMove(namedApiRss.move.name)
                    val pokemonMove = PokemonMove(pokemon.pokemonName, move.moveId)
                    insertPokemonMove(pokemonMove)
                }
    }

}