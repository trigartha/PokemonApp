package com.example.pokemon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.pokemon.adapters.GalleryAdapter
import com.example.pokemon.databinding.FragmentGalleryBinding
import com.example.pokemon.utilities.Resource
import com.example.pokemon.utilities.autoCleared
import com.example.pokemon.viewmodels.PokemonGalleryViewModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * Creates a [GalleryFragment]
 * Annotation because they need to be injected themselves
 * since we need the viewmodel in the fragment and the fragment inside the activity
 */
@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private var binding : FragmentGalleryBinding by autoCleared()
    private val viewModel: PokemonGalleryViewModel by viewModels()

    /**
     * to inflate fragment's view and to return inflated view to the hosting activity
     * @param inlfater | layoutinflater
     * @param container | ViewGroup
     * both are necessary to inflate the layout
     * wiring up widgets happens here
     * @return the inflated view with wired widgets
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         binding = FragmentGalleryBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = GalleryAdapter()
        binding.galleryList.adapter = adapter

        updateUi(adapter)
        return binding.root
    }

    /**
     * Updates the view when the observers notice a change in the data
     */
    private fun updateUi(adapter: GalleryAdapter) {
        viewModel.pokemons.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Resource.Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.submitList(ArrayList(it.data))
                }
                Resource.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

                Resource.Status.LOADING ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }
}
