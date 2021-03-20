package com.example.pokemon.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokemon.models.*
import com.example.pokemon.utilities.DATABASE_NAME
/**
 * The application's Database
 *
 * Database annotation tells Room that this class represents a database in the app
 * list of entity classes, version of the database
 * Typeconverters annotation tells Room to use the converter's functions when converting data types
 * abstract means you can't make an instance of it instantly
 */
@Database(entities = [Pokemon::class, PokemonSpecies::class, Move::class, PokemonMove::class, MyPokemon::class],
    version = 3, exportSchema = false)
@TypeConverters(MyPokemonTypeConverters::class)
abstract class PokemonDatabase:RoomDatabase() {
    abstract fun pokemonDAO():  PokemonDAO
    abstract fun myPokemonDAO(): MyPokemonDAO

    companion object {
        /*
        Volatile
        Marks the JVM backing field of the annotated property as volatile,
        meaning that writes to this field are immediately made visible to other threads.
         */
        @Volatile
        private var instance: PokemonDatabase?=null
        /**
         * gets an instance of the PokemonDatabase
         * checks if the PokemonDatabase is initialized, will build the PokemonDatabase if there is none.
         * follows the convention of adding a companion object that contains a newInstance function to the PokemonDatabase class
         */
        fun getInstance(context: Context):PokemonDatabase{
            return instance ?: synchronized(this){
                instance ?: buildDatabase(context).also { instance=it }
            }
        }
        /**
         * Creates a concrete implementation of the abstract PokemonDatabase
         * @param context because it accesses the filesystem: app context since singleton will most likely live longer then activity classes
         * @param PokemonDatabase database class Room has to create
         * @param DATABASE_NAME name for database file
         */
        private fun buildDatabase(context: Context):PokemonDatabase{
            return Room.databaseBuilder(
                context.applicationContext,
                PokemonDatabase::class.java,
                DATABASE_NAME
            ).build()
        }
    }
}