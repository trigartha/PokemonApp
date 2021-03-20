package com.example.pokemon.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize
/**
 * A PokemonSprite (object)
 *
 * This class contains all the details of a PokemonSprite
 *
 * @property frontDefault | the url to the frontview default image of this pokemon
 */
@Parcelize
data class PokemonSprite (
    @Json(name="front_default")
    @ColumnInfo(name="front_default")
    val frontDefault: String?
) : Parcelable