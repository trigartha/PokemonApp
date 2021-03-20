package com.example.pokemon.models

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize
/**
 * A Move (object)
 *
 * This class contains all the details of a Move
 *
 * @property moveId | the id of this Move, is the primary key of the database
 *
 * @property move | a NamedApiResource with the name of this move
 * */
@Parcelize
@Entity (tableName = "move")
data class Move(
    @Embedded val move: NamedApiResource,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "moveId")
    var moveId: Int = 0
) :Parcelable
