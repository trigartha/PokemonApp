package com.example.pokemon.api

import com.example.pokemon.models.Pokemon
import com.example.pokemon.models.NamedApiResourceList
import com.example.pokemon.models.PokemonSpecies
import com.example.pokemon.utilities.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Endpoints are defined inside of the interface using special retrofit annotations to encode details about the parameters and request method
 */
interface PokemonService {
    /**
     * Gets a [NamedApiResourceList] wrapped in a [Response]
     */
    @GET("pokemon/")
    suspend fun getPokemonList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): Response<NamedApiResourceList>

    /**
     * Gets a [Pokemon] wrapped in a [Response]
     */
    @GET("pokemon/{name}")
    suspend fun getPokemon (
        @Path("name") name: String
    ): Response<Pokemon>

    /**
     * Gets a [Pokemon]
     */
    @GET("pokemon/{name}")
    suspend fun getPokemonData (
        @Path("name") name: String
    ): Pokemon

    /**
     * Gets a [PokemonSpecies] wrapped in a [Response]
     */
    @GET("pokemon-species/{name}")
    suspend fun getPokemonSpecies (
        @Path("name") name:String
    ): Response<PokemonSpecies>

    /**
     * Companion object to create a [PokemonService]
     */
    companion object {
        fun create (): PokemonService {
            val logger = HttpLoggingInterceptor().apply{ level = HttpLoggingInterceptor.Level.BASIC}
            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            val moshi = Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
            val retrofit = Retrofit.Builder()
                .client(client)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(PokemonService::class.java)
        }
    }

}