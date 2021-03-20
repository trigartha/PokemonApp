package com.example.pokemon.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.databinding.ListItemMypokemonBinding
import com.example.pokemon.fragments.ViewPagerFragmentDirections
import com.example.pokemon.models.MyPokemon

/**
 * Adapter for the [RecyclerView] in [FavouritesFragment].
 */
class MyPokemonAdapter: ListAdapter<MyPokemon, MyPokemonAdapter.MyPokemonViewHolder>(MyPokemonDiffCallback()) {

    /**
     * This method is called right when the adapter is created and is used to initialize your ViewHolder(s).
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPokemonViewHolder {
        return MyPokemonViewHolder(
            ListItemMypokemonBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    /**
     * This method is called for each [FavouritesFragment] to bind it to the adapter. This is where we will pass our data to our ViewHolder.
     */
    override fun onBindViewHolder(holder: MyPokemonViewHolder, position: Int) {
        val pokemon = getItem(position)
        if (pokemon != null) {
            holder.bind(pokemon)
        }
    }
    /**
     * Object that represents each item in our collection and will be used to display it
     */
    class MyPokemonViewHolder(
        private val binding: ListItemMypokemonBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.pokemon?.let{
                        pokemon-> navigateToPokemon(pokemon.pokemonName, it)
                }
            }
        }
        /**
         * Gets the direction for [FavouritesFragment] and navigates to it
         */
        private fun navigateToPokemon(pokemonName: String, view: View){
            val direction =
                ViewPagerFragmentDirections.actionViewPagerFragmentToPokemonFragment(
                    pokemonName
                )
            view.findNavController().navigate(direction)
        }
        /**
         * Binds the [MyPokemon] object to the viewHolder
         */
        fun bind(item: MyPokemon) {
            binding.apply {
                pokemon = item
                executePendingBindings()
            }
        }
    }
}
/**
 * Callback that informs ArrayObjectAdapter how to compute list updates when using DiffUtil
 * in ArrayObjectAdapter.setItems(List, DiffCallback) method.
 */
private class MyPokemonDiffCallback : DiffUtil.ItemCallback<MyPokemon>() {
    override fun areItemsTheSame(oldItem: MyPokemon, newItem: MyPokemon): Boolean {
        return oldItem.pokemonName == newItem.pokemonName
    }

    override fun areContentsTheSame(oldItem: MyPokemon, newItem: MyPokemon): Boolean {
        return oldItem == newItem
    }
}