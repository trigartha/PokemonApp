package com.example.pokemon.viewmodels

import android.text.Editable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel: ViewModel() {

      private var _pokemonName = MutableLiveData<String?>()
      var pokemonName: LiveData<String?>? = null
              get()=_pokemonName

      fun setPokemonName(string:Editable){
            _pokemonName.value = string.toString()
      }
}