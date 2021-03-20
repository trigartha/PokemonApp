package com.example.pokemon.models


import androidx.room.*

/**
 * This class captures the relationship between a [Pokemon] and a  [Move]
 *
 * @property pokemonName | the name of a [Pokemon] object
 *
 * @property moveId | the id of a [Move] object
 */
@Entity(tableName="pokemonMove", primaryKeys = ["pokemonName", "moveId"],
    indices = [Index(value = ["pokemonName"]), Index(value = ["moveId"])],
    foreignKeys = [
        ForeignKey(entity = Pokemon::class, parentColumns =["pokemonName"], childColumns = ["pokemonName"]),
        ForeignKey(entity = Move::class, parentColumns = ["moveId"], childColumns = ["moveId"])
        ])
data class PokemonMove (
    val pokemonName: String,
    val moveId:Int
)

/**
 * Contains a [Pokemon] object and a List of [Move] objects that belong to this [Pokemon]
 *
 * * @property pokemon | a [Pokemon] object
 *
 * @property moves | a List of [Move] objects
 */
data class PokemonWithMoves(
    @Embedded val pokemon: Pokemon,
    @Relation(  parentColumn = "pokemonName",
                entityColumn= "moveId",
                associateBy = Junction(PokemonMove::class)
    )
    val moves: List<Move>
)
