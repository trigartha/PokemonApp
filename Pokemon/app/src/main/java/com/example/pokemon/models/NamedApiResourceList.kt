package com.example.pokemon.models


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
/**
 * A NamedApiResourceList (object)
 *
 * This class contains all the details of a NamedApiResourceList
 *
 * @property count | the amount of items in the results list
 *
 * @property results | a list of [NamedApiResource] objects
 * */
@Parcelize
data class NamedApiResourceList(
    val count: Int,
    val results: List<NamedApiResource>
) : Parcelable

