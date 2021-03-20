package com.example.pokemon.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.adapters.PokemonMoveAdapter.PokemonMoveViewHolder
import com.example.pokemon.databinding.ListItemGalleryBinding
import com.example.pokemon.fragments.ViewPagerFragmentDirections
import com.example.pokemon.models.Pokemon


/**
 * Adapter for the [RecyclerView] in [PokemonListFragment].
 * You can connect an instance of LiveData<PagedList> to a PagedListAdapter
 */
class GalleryAdapter : ListAdapter<Pokemon, GalleryAdapter.GalleryViewHolder>(GalleryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        return GalleryViewHolder(
            ListItemGalleryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }
    /**
     * This method is called for each [GalleryViewHolder] to bind it to the adapter. This is where we will pass our data to our ViewHolder.
     */
    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        val pokemon = getItem(position)
        if (pokemon != null) {
            holder.bind(pokemon)
        }
    }
    /**
     * Object that represents each item in our collection and will be used to display it
     */
    class GalleryViewHolder(
        private val binding: ListItemGalleryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {
                binding.pokemon?.let{
                        pokemon-> navigateToPokemon(pokemon.pokemonName, it)
                }
            }
        }
        /**
         * Gets the direction for [GalleryFragmentDirections] and navigates to it
         */
        private fun navigateToPokemon(pokemonName: String, view: View){
            val direction =
                ViewPagerFragmentDirections.actionViewPagerFragmentToPokemonFragment(
                    pokemonName
                )
            view.findNavController().navigate(direction)
        }
        /**
         * Binds the [Pokemon] object to the viewHolder
         */
        fun bind(item: Pokemon) {
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
    private class GalleryDiffCallback : DiffUtil.ItemCallback<Pokemon>() {
        override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem.pokemonName == newItem.pokemonName
        }

        override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
            return oldItem == newItem
        }
    }