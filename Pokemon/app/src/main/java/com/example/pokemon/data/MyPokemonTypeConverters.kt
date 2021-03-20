package com.example.pokemon.data

import androidx.room.TypeConverter
import com.example.pokemon.models.NamedApiResource
import java.util.*

/**
 * Converter class for database entries who are not convertable by room
 */
class MyPokemonTypeConverters {

    /**
     * Converts a [Calendar] object to a [Long] object
     */
    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    /**
     * Converts a [Long] object to a [Calendar] object
     */
    @TypeConverter
    fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }

    /**
     * Converts a [NamedApiResource] object to a [String] object
     */
    @TypeConverter
    fun namedApiResourceToString(namedApiResource: NamedApiResource): String = namedApiResource.name
    /**
     * Converts a [String] object to a [NamedApiResource] object
     */
    @TypeConverter
    fun stringToNamedApiResource(value: String): NamedApiResource =
        NamedApiResource(value)
}