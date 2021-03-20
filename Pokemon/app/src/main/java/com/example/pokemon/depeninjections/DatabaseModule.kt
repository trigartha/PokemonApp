package com.example.pokemon.depeninjections

import android.content.Context
import com.example.pokemon.data.MyPokemonDAO
import com.example.pokemon.data.PokemonDAO
import com.example.pokemon.data.PokemonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * scope the database and dao instances to the application
 */
@Module
@InstallIn(ApplicationComponent::class)
class DatabaseModule {

    /**
     * Provides a [PokemonDatabase] object as Singleton
     */
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appcontext : Context) : PokemonDatabase{
        return PokemonDatabase.getInstance(appcontext)
    }

    /**
     * Provides a [PokemonDAO] object
     */
    @Provides
    fun providePokemonDao(pokemonDatabase: PokemonDatabase): PokemonDAO {
        return pokemonDatabase.pokemonDAO()
    }

    /**
     * Provides a [MyPokemonDAO] object
     */
    @Provides
    fun provideMyPokemonDao(pokemonDatabase: PokemonDatabase): MyPokemonDAO {
        return pokemonDatabase.myPokemonDAO()
    }


}

