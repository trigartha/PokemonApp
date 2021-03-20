package com.example.pokemon.data

import com.example.pokemon.utilities.Resource
import retrofit2.Response

/**
 *  An abstract class cannot be instantiated (you cannot create objects of an abstract class). However, you can inherit subclasses from can them.
 */
abstract class BaseDataResult {

    /**
     * Encapsulates the Retrofit response in a [Resource] object for a call in the [PokemonRemote] class
     */
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }
}