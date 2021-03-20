package com.example.pokemon.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pokemon.fragments.FavouritesFragment
import com.example.pokemon.fragments.GalleryFragment
import com.example.pokemon.fragments.SearchFragment
import com.example.pokemon.utilities.FAVORITE_PAGE_INDEX
import com.example.pokemon.utilities.HOMEPAGE_INDEX
import com.example.pokemon.utilities.SEARCH_PAGE_INDEX

/**
 * Adapter for the custom navigation bar
 *
 */
class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * Defining which index maps to which fragment in an map (~like an array)
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        HOMEPAGE_INDEX to { GalleryFragment() },
        SEARCH_PAGE_INDEX to { SearchFragment() },
        FAVORITE_PAGE_INDEX to { FavouritesFragment() },
    )


    /**
     * calculates the mapped fragments in the viewpager
     */
    override fun getItemCount(): Int = tabFragmentsCreators.size

    /**
     * Selects and creates the right fragment according to the index
     * will throw an exception if selected fragment is indexed out of bounds of the map
     */
    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}


