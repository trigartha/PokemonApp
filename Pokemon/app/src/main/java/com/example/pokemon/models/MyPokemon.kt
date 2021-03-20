package com.example.pokemon.models

import androidx.room.*
import java.util.*
/**
 * A MyPokemon (object)
 *
 * This class contains all the details of a MyPokemon
 *
 * @property pokemonName | the id of this MyPokemon, is the primary key of the database
 *
 * @property sprite | a string with the url of the image of this pokemon
 *
 * @property captureDate | the date this Pokémon is added to the favourite list
 * */
@Entity(
    tableName = "my_pokemon",
    foreignKeys = [
        ForeignKey(entity = Pokemon::class, parentColumns = ["pokemonName"], childColumns = ["pokemonName"])
    ],
    indices = [Index("pokemonName")]
)
data class MyPokemon (
    @PrimaryKey @ColumnInfo(name = "pokemonName") val pokemonName: String,
    val sprite: String?,
    /**
     * Indicates when the [Pokemon] was captured.
     */
    @ColumnInfo(name = "capture_date") val captureDate: Calendar

)