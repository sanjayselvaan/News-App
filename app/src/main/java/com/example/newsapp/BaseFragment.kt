package com.example.newsapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabTitle= arrayListOf("Tab-1","Tab-2")
        val tabLayout:TabLayout=view.findViewById(R.id.tabLayout)
        val viewPager=view.findViewById<ViewPager2>(R.id.ViewPager)
        viewPager.adapter=FragmentAdapter(requireActivity().supportFragmentManager,requireActivity().lifecycle)
        TabLayoutMediator(tabLayout,viewPager){
                tab,position->
            tab.text=tabTitle[position]
        }.attach()
    }

}