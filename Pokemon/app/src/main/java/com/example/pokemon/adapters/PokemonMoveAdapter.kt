package com.example.pokemon.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.databinding.ListItemMovesBinding
import com.example.pokemon.models.Move

/**
 * Adapter for the [RecyclerView] in [PokemonFragment].
 * */

class PokemonMoveAdapter : ListAdapter<Move, RecyclerView.ViewHolder>(PokemonMoveDiffCallback()) {

    /**
     * This method is called right when the adapter is created and is used to initialize your ViewHolder(s).
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PokemonMoveViewHolder(
            ListItemMovesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    /**
     * This method is called for each [PokemonMoveViewHolder] to bind it to the adapter. This is where we will pass our data to our ViewHolder.
     */
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val pokemonMove = getItem(position)
        (holder as PokemonMoveViewHolder).bind(pokemonMove)
    }
    /**
     * Object that represents each item in our collection and will be used to display it
     */
    class PokemonMoveViewHolder(
        private val binding: ListItemMovesBinding
    ): RecyclerView.ViewHolder(binding.root){

        /**
         * Binds the [Move] object to the viewHolder
         */
        fun bind(item:Move){
            binding.apply {
                pokemonMove = item
                executePendingBindings()
            }
        }
    }
}
/**
 * Callback that informs ArrayObjectAdapter how to compute list updates when using DiffUtil
 * in ArrayObjectAdapter.setItems(List, DiffCallback) method.
 */
private class PokemonMoveDiffCallback : DiffUtil.ItemCallback<Move>() {

    override fun areItemsTheSame(oldItem: Move, newItem: Move): Boolean {
        return oldItem.move.name == newItem.move.name
    }

    override fun areContentsTheSame(oldItem: Move, newItem: Move): Boolean {
        return oldItem == newItem
    }
}