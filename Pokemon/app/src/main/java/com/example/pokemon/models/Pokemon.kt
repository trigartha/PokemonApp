package com.example.pokemon.models

import android.os.Parcelable
import androidx.room.*
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * A Pokémon (object)
 *
 * This class contains all the details of a Pokémon
 *
 * @property pokemonId | the id of this Pokémon, is the primary key of the database
 *
 * @property name | the name of this Pokémon
 *
 * @property baseExperience | the base experience gained for defeating this Pokémon
 *
 * @property height | the height of this Pokémon in decimetres
 *
 * @property order | Order for sorting. Almost national order, except families are grouped together
 *
 * @property weight | The weight of this Pokémon in hectograms
 *
 * @property sprites | An [PokemonSprite] object that contains url for this Pokémon images
 *
 * @property moves | A List of [Move] objects
 *
 * @constructor | Creates a Pokémon
 */
@Parcelize
@Entity(tableName = "pokemon")
data class Pokemon constructor(
    @ColumnInfo(name="pokemonId") @Json(name="id")val pokemonId: Int,
    @PrimaryKey @ColumnInfo(name="pokemonName")@Json(name="name") val pokemonName: String,
    @Json(name="base_experience")
    val baseExperience: Int,
    val height: Int,
    val order: Int,
    val weight: Int,
    @Embedded val sprites: PokemonSprite
): Parcelable {
    @Ignore var moves: List<Move> = emptyList()

}