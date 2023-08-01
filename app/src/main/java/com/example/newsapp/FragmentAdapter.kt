package com.example.newsapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class FragmentAdapter(fm:FragmentManager,lifecycle: Lifecycle):FragmentStateAdapter(fm,lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0-> Tab1Fragment()
            1-> Tab2Fragment()
            else-> Tab1Fragment()
        }
    }

}