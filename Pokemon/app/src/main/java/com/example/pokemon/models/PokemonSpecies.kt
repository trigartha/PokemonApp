package com.example.pokemon.models

import android.os.Parcelable
import androidx.room.*
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * A PokemonSpecies (object)
 *
 * This class contains all the details of a PokemonSpecies
 *
 * @property pokemonId | the id of a [PokemonSpecies] object
 *
 * @property name | the name of a [PokemonSpecies] object
 *
 * @property baseHappiness | the amount of happiness a specific Pokémon has at start
 *
 * @property habitat | The habitat this Pokémon species can be encountered in.
 *
 * @property color | The color of this Pokémon for Pokédex search.
 *
 * @property shape | The shape of this Pokémon for Pokédex search.
 */
@Parcelize
@Entity(tableName = "pokemon_species")
data class PokemonSpecies(
    @ColumnInfo(name="pokemonId") @Json(name="id")val pokemonId: Int,
    @PrimaryKey @ColumnInfo(name="pokemonName")
    val name: String,
    @Json(name="base_happiness")
    val baseHappiness: Int,
    @Embedded val habitat: NamedApiResource,
    @ColumnInfo(name="color")
    val color: NamedApiResource,
    @ColumnInfo(name="shape")
    val shape: NamedApiResource,

):Parcelable