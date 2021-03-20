package com.example.pokemon.depeninjections

import com.example.pokemon.api.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

/**
 * scope the api service instance to the application
 */
@InstallIn(ApplicationComponent::class)
@Module
class NetworkModule {

    /**
     * Provides a [PokemonService] object as Singleton
     */
    @Singleton
    @Provides
    fun providePokemonService(): PokemonService {
        return PokemonService.create()
    }
}
