package com.example.pokemon.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pokemon.R
import com.example.pokemon.adapters.ViewPagerAdapter
import com.example.pokemon.databinding.FragmentViewpagerBinding
import com.example.pokemon.utilities.FAVORITE_PAGE_INDEX
import com.example.pokemon.utilities.HOMEPAGE_INDEX
import com.example.pokemon.utilities.SEARCH_PAGE_INDEX
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


/**
 * Creates a [ViewPagerFragment]
 * Annotation because they need to be able to be injected
 * since we need the viewmodel in the fragment and the fragment inside the activity
 */
@AndroidEntryPoint
class ViewPagerFragment: Fragment() {

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
        val binding = FragmentViewpagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(setTabViewSelected(position))
        }.attach()


        return binding.root
    }

    /**
     * Sets the icon for each tab in the bottom navigation bar
     */
    private fun setTabViewSelected(position:Int): Int {
        return when (position) {
            HOMEPAGE_INDEX -> R.drawable.ic_home_black
            SEARCH_PAGE_INDEX -> R.drawable.ic_search_black
            FAVORITE_PAGE_INDEX-> R.drawable.ic_favorite_black
            else -> throw IndexOutOfBoundsException()
        }
    }

}