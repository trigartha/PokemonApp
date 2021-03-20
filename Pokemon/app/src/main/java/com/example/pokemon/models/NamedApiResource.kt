package com.example.pokemon.models

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
/**
 * A NamedApiResource (object)
 *
 * This class contains all the details of a NamedApiResource
 *
 * @property name | a string with the name of a value
 * */
@Parcelize
data class NamedApiResource(
      val name: String,
) : Parcelable{
      override fun toString(): String {
            return name
      }
}

