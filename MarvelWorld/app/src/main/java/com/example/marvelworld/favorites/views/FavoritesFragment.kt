package com.example.marvelworld.favorites.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.example.marvelworld.R
import com.example.marvelworld.characterlist.views.CharacterListFragment
import com.example.marvelworld.comiclist.views.ComicListFragment
import com.example.marvelworld.creatorlist.views.CreatorListFragment
import com.example.marvelworld.eventlist.views.EventListFragment
import com.example.marvelworld.mainviewpager.views.MainViewPagerAdapter
import com.example.marvelworld.serieslist.views.SeriesListFragment
import com.example.marvelworld.storylist.views.StoryListFragment
import com.google.android.material.tabs.TabLayout

class FavoritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragments = listOf(
            CharacterListFragment(true),
            ComicListFragment(true),
            EventListFragment(true),
            SeriesListFragment(true),
            StoryListFragment(true),
            CreatorListFragment(true)
        )

        val titles = listOf("Characters", "Comics", "Events", "Series", "Stories", "Creators")

        val viewPager = view.findViewById<ViewPager>(R.id.view_pager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tab_layout)

        tabLayout.setupWithViewPager(viewPager)

        viewPager.adapter = MainViewPagerAdapter(fragments, titles, childFragmentManager)
    }
}